package com.diaspotea.diaspoteaserver.services;

import com.diaspotea.diaspoteaserver.dto.UtilisateurInscriptionDto;
import com.diaspotea.diaspoteaserver.models.Admin;
import com.diaspotea.diaspoteaserver.models.Client;
import com.diaspotea.diaspoteaserver.models.Livreur;
import com.diaspotea.diaspoteaserver.models.Utilisateur;
import com.diaspotea.diaspoteaserver.repository.UtilisateurRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;

    private final PasswordEncoder passwordEncoder;
    public UtilisateurService(UtilisateurRepository utilisateurRepository,PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
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

    public void ajouterUtilisateur(UtilisateurInscriptionDto utilisateurInscriptionDto,String role) {
        Utilisateur utilisateur=null;
        switch (role.toLowerCase()){
            case "client":
                utilisateur=new Client();
                break;
            case "admin":
                utilisateur=new Admin();
                break;
            case "livreur":
                utilisateur=new Livreur();
                break;
            default:
                throw  new IllegalArgumentException("type d'utilisateur inconnu");

        }

        utilisateur.setEmail(utilisateurInscriptionDto.getEmail());
        utilisateur.setNom(utilisateurInscriptionDto.getNom());
        utilisateur.setPrenom(utilisateurInscriptionDto.getPrenom());
        utilisateur.setModePasse(passwordEncoder.encode(utilisateurInscriptionDto.getPassword()));
        utilisateurRepository.save(utilisateur);


    }

    public boolean exist(String email) {
        return  utilisateurRepository.existsByEmail(email);
    }

    public  <T extends Utilisateur> T recuperUtilisateurParType(Integer clientId,Class<T> type) {
        return utilisateurRepository.findById(clientId, type);
    }
}
