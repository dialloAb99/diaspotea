package com.diaspotea.diaspoteaserver.services;

import com.diaspotea.diaspoteaserver.models.Livreur;
import com.diaspotea.diaspoteaserver.repository.LivreurRepository;
import org.springframework.stereotype.Service;

@Service

public class LivreurService {
    private LivreurRepository livreurRepository;
    public LivreurService(LivreurRepository livreurRepository){
        {this.livreurRepository=livreurRepository;}
    }

    public Livreur ajouterLivreur(Livreur livreur) {
        return livreurRepository.save(livreur);

    }

    public Livreur recupereLivreur(int id) {
        return livreurRepository.findById(id).orElse(null);
    }

    public Livreur modifierLivreur(Livreur livreur) {
        return livreurRepository.save(livreur);
    }

    public void deleteLivreur(int id) {
        livreurRepository.deleteById(id);
    }
}
