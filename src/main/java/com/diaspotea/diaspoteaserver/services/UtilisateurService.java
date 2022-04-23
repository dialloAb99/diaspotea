package com.diaspotea.diaspoteaserver.services;

import com.diaspotea.diaspoteaserver.models.Utilisateur;
import com.diaspotea.diaspoteaserver.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

@Service

public class UtilisateurService {
    private UtilisateurRepository utilisateurRepository;
    public UtilisateurService(UtilisateurRepository utilisateurRepository){
        {this.utilisateurRepository=utilisateurRepository;}
    }


    public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur recupereUtilisateur(int id) {
        return utilisateurRepository.findById(id).orElse(null);
    }

    public Utilisateur modifierUtilisateur(Utilisateur utilisateur) {
        return  utilisateurRepository.save(utilisateur);
    }

    public void deleteUtilisateur(int id) {
        utilisateurRepository.deleteById(id);
    }
}
