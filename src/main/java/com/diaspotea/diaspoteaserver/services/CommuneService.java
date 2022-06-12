package com.diaspotea.diaspoteaserver.services;

import com.diaspotea.diaspoteaserver.models.Commune;
import com.diaspotea.diaspoteaserver.repository.CommuneRepository;
import org.springframework.stereotype.Service;

@Service

public class CommuneService {
    private final CommuneRepository communeRepository;
    private CommuneService(CommuneRepository communeRepository){
        {this.communeRepository=communeRepository;}
    }

    public Commune ajouterCommune(Commune commune) {
        return communeRepository.save(commune);
    }

    public Commune recupereCommune(int id) {
        return communeRepository.findById(id).orElse(null);
    }

    public Commune modifierCommune(Commune commune) {
        return communeRepository.save(commune);
    }

    public void deleteCommune(int id) {
        communeRepository.deleteById(id);
    }
}
