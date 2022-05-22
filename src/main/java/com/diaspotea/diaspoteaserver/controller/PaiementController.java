package com.diaspotea.diaspoteaserver.controller;

import com.diaspotea.diaspoteaserver.dto.PaiementDto;
import com.diaspotea.diaspoteaserver.models.*;
import com.diaspotea.diaspoteaserver.services.*;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Controller

public class PaiementController {
    private final PanierService panierService;
    private final LivreurService livreurService;
    private final CommandeService commandeService;
    private  final ClientService clientService;

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    private final StripeService paymentsService;

    @Autowired
    public PaiementController(PanierService panierService, LivreurService livreurService, CommandeService commandeService, ClientService clientService, StripeService paymentsService) {
        this.panierService = panierService;
        this.livreurService = livreurService;
        this.commandeService = commandeService;
        this.clientService = clientService;
        this.paymentsService = paymentsService;
    }

    @GetMapping("/paiement/{panierId}")
    public String paiement(@PathVariable Integer panierId, Model model) {
        Panier panier = panierService.recuperePanier(panierId);

        Client client = panier.getClient();

        PaiementDto paiementDto = new PaiementDto(client.getId(), client.getAdresse(), false, client.getCodePostale(), client.getVille(), client.getEtage(), panierId, panier.getLigneDeCommandes(),0.0F);
        model.addAttribute("paiementDto", paiementDto);
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.EUR);
        return "payer";
    }


    @PostMapping("/paiement")
    public String paiement(PaiementDto paiementDto, ChargeRequest chargeRequest, Model model) {
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(ChargeRequest.Currency.EUR);
        chargeRequest.setAmount((int) (paiementDto.getPrixTotal() * 100));
        Charge charge = null;
        try {
            charge = paymentsService.charge(chargeRequest);
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        } catch (InvalidRequestException e) {
            throw new RuntimeException(e);
        } catch (APIConnectionException e) {
            throw new RuntimeException(e);
        } catch (CardException e) {
            throw new RuntimeException(e);
        } catch (APIException e) {
            throw new RuntimeException(e);
        }
        Panier panier=panierService.recuperePanier(paiementDto.getPanierId());
        Livreur livreur=livreurService.recupereLivreur(2);
        Client client=clientService.recupereClient(paiementDto.getClientId());
        Commande commande=new Commande();
        commande.setAdresse(paiementDto.getAdresse());
        commande.setCodePostale(paiementDto.getCodePostale());
        commande.setVille(paiementDto.getVille());
        commande.setEtage(paiementDto.getEtage());
        commande.setDateCommande(LocalDateTime.now());
        commande.setDateLivraison(LocalDateTime.now().plusHours(1));
        commande.setLivreur(livreur);
        commande.setClient(client);
        panier.getLigneDeCommandes().forEach((ligneDeCommande -> ligneDeCommande.setCommande(commande)));
        commande.setLigneDeCommandes(new ArrayList<>(panier.getLigneDeCommandes()));
        commandeService.ajouterCommande(commande);
        panier.setEtatPanier(false);
        panierService.modifierPanier(panier);
        model.addAttribute("dateCommande",commande.getDateCommande());
        model.addAttribute("dateLivraison",commande.getDateLivraison());
        model.addAttribute("montant",paiementDto.getPrixTotal()+" "+ChargeRequest.Currency.EUR.toString());
        return "result";
    }

}