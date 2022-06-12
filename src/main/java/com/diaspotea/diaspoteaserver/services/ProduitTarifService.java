package com.diaspotea.diaspoteaserver.services;

import com.diaspotea.diaspoteaserver.models.ProduitTarif;
import com.diaspotea.diaspoteaserver.models.ProduitTarifID;
import com.diaspotea.diaspoteaserver.repository.ProduitTarifRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ProduitTarifService {
    private final ProduitTarifRepository produitTarifRepository;
    @Autowired
    public ProduitTarifService(ProduitTarifRepository produitTarifRepository){
        {this.produitTarifRepository=produitTarifRepository;}
    }

    public ProduitTarif ajouterProduitTarif(ProduitTarif produitTarif) {
        return produitTarifRepository.save(produitTarif);
    }

    public ProduitTarif recupereProduitTarif(ProduitTarifID produitTarifId) {
        return produitTarifRepository.findById(produitTarifId).orElse(null);
    }

    public ProduitTarif modifierProduitTarif(ProduitTarif produitTarif) {
        return produitTarifRepository.save(produitTarif);
    }

    public void deleteProduitTarif(ProduitTarifID produitTarifID) {
        produitTarifRepository.deleteById(produitTarifID);
    }
}
