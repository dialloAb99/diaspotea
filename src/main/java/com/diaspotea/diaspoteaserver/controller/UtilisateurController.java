package com.diaspotea.diaspoteaserver.controller;

import com.diaspotea.diaspoteaserver.dto.ModiferFormAdresseLivraisonDto;
import com.diaspotea.diaspoteaserver.models.Client;
import com.diaspotea.diaspoteaserver.models.UserPrincipal;
import com.diaspotea.diaspoteaserver.models.Utilisateur;
import com.diaspotea.diaspoteaserver.services.UtilisateurService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.validation.Valid;

@Controller

public class UtilisateurController {
    private UtilisateurService utilisateurService;
    public UtilisateurController(UtilisateurService utilisateurService){
        this.utilisateurService=utilisateurService;
    }
    @PutMapping("/modifier-adresse")
    public String modifierAdresse(  @Valid ModiferFormAdresseLivraisonDto modiferFormAdresseLivraisonDto,BindingResult bindingResult,Authentication authentication ){
        UserPrincipal utilisateurConnecter=(UserPrincipal) authentication.getPrincipal();
        Client client = utilisateurService.recuperUtilisateurParType(utilisateurConnecter.getId(), Client.class);
        if (bindingResult.hasErrors()) {
            return "formulaireModifierAdresse";
        }
        client.setAdresse(modiferFormAdresseLivraisonDto.getAdresse());
        client.setCodePostale(modiferFormAdresseLivraisonDto.getCodePostale());
        client.setVille(modiferFormAdresseLivraisonDto.getVille());
        client.setEtage(modiferFormAdresseLivraisonDto.getEtage());
        utilisateurService.modifierUtilisateur(client);
        return "moncompte";
    }
    @GetMapping("/modifier-adresse")
    public  String modifierAdresser(Model model, Authentication authentication,ModiferFormAdresseLivraisonDto modiferFormAdresseDto){
        UserPrincipal utilisateurConnecter=(UserPrincipal) authentication.getPrincipal();
        Utilisateur client = utilisateurService.recupereUtilisateur(utilisateurConnecter.getId());
        modiferFormAdresseDto.setAdresse(client.getAdresse());
        modiferFormAdresseDto.setCodePostale(client.getCodePostale());
        modiferFormAdresseDto.setVille(client.getVille());
        modiferFormAdresseDto.setEtage(String.valueOf(client.getEtage()));
        modiferFormAdresseDto.setClientId(client.getId());
        modiferFormAdresseDto.setMemeAdresse(false);
        model.addAttribute("modiferFormAdresseLivraisonDto", modiferFormAdresseDto);
        return  "formulaireModifierAdresse";
    }
}
