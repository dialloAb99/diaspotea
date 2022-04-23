package com.diaspotea.diaspoteaserver.controller;


import com.diaspotea.diaspoteaserver.dto.ProduitDTO;
import com.diaspotea.diaspoteaserver.models.*;
import com.diaspotea.diaspoteaserver.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.security.auth.kerberos.KerberosTicket;
import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

@Controller

public class PanierController {
    private final PanierService panierService;
    private final ProduitService produitService;
    private final ClientService clientService;
    private final ProduitTarifService produitTarifService;
    private final LigneDecommandeService ligneDecommandeService;
    private final UtilisateurService utilisateurService;
    @Autowired
    public PanierController(PanierService panierService, ProduitService produitService, ClientService clientService, ProduitTarifService produitTarifService, LigneDecommandeService ligneDecommandeService, UtilisateurService utilisateurService) {
        this.panierService = panierService;
        this.produitService = produitService;
        this.clientService=clientService;
        this.produitTarifService = produitTarifService;
        this.ligneDecommandeService = ligneDecommandeService;
        this.utilisateurService = utilisateurService;
    }
    //FIX ME n'ajoute pas de ligne de commmande
    @PostMapping(value = "/panier/ajouter")
    public String panierAjouter(Model model, @ModelAttribute ProduitDTO produitDTO, HttpServletRequest request){
        Produit produit=produitService.recupererProduit(produitDTO.getId());
        Client client=clientService.recupereClient(1);
        boolean panierEstActif= panierService.panierEstActif(client);
        if (panierEstActif){
            Panier panier=panierService.recuperePanierActif(client);
            ProduitTarif produitTarif=produitTarifService.recupereProduitTarif(new ProduitTarifID(produitDTO.getId(), produitDTO.getTailleID()));
            LigneDeCommande ligneDeCommande=new LigneDeCommande();
            if(panier.ligneDeCommandeExiste(produitDTO.getId(),produitDTO.getTailleID())){
                 ligneDeCommande=panier.recupererLigneDeCommande(produitDTO.getId(),produitDTO.getTailleID());
                int nouvellleQuantite = ligneDeCommande.getQuantiter() + produitDTO.getQuantite();
                ligneDeCommande.setQuantiter(nouvellleQuantite);
                ligneDeCommande.setPrix(produitTarif.getTarif()*nouvellleQuantite);
                //recuperer ligne de commande et modifier la quantite de la ligne de commande
            }else{
                ligneDeCommande.setPrix(produitTarif.getTarif());
                ligneDeCommande.setProduit(produit);
                ligneDeCommande.setTaille(produitTarif.getTaille());
                ligneDeCommande.setQuantiter(produitDTO.getQuantite());
                ligneDeCommande.setPanier(panier);
            }
            ligneDecommandeService.AjouterLigneDeCommande(ligneDeCommande);
            panier.ajouterLigneDeCommande(ligneDeCommande);
            panierService.ajouterPanier(panier);
            //ajoute ligne de commmande au panier
        }
        URL url= null;
        try {
            url = new URL(request.getHeader("referer"));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return "redirect:"+url.getPath() ;
    }
    @GetMapping("/monpanier/{id}")
    public String monPanier(Model model, @PathVariable("id") Integer idUtilisateur){
        Utilisateur utilisateur=utilisateurService.recupereUtilisateur(idUtilisateur);
        Client client=utilisateur.getClient();
        if(Objects.isNull(client)){
            return "panier";
            //throw new IllegalArgumentException(" a Client was expected");
        }
        boolean panierEstActif=panierService.panierEstActif(client.getId());
        if (panierEstActif){
            Panier panier=panierService.recuperePanierActif(client.getId());
            model.addAttribute("panier",panier);
        }
        else{
            model.addAttribute("panier",null);
        }
        model.addAttribute("client",utilisateur.getClient());
        return "panier";
    }
}
