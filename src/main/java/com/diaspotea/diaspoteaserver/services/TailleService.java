package com.diaspotea.diaspoteaserver.services;

import com.diaspotea.diaspoteaserver.models.Taille;
import com.diaspotea.diaspoteaserver.repository.TailleRepository;
import org.springframework.stereotype.Service;

@Service

public class TailleService {
    private TailleRepository tailleRepository;
    public TailleService(TailleRepository tailleRepository){
        {this.tailleRepository=tailleRepository;}
    }

    public Taille ajouterUneTaille(Taille taille) {
        return tailleRepository.save(taille);
    }

    public Taille recupererTaille(int id) {
        return tailleRepository.findById(id).orElse(null);
    }

    public Taille modifierUneTaille(Taille taille) {
        return tailleRepository.save(taille);
    }

    public void deleteTaille(int id) {
        tailleRepository.deleteById(id);
    }


}
