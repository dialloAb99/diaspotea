package com.diaspotea.diaspoteaserver.repository;

import com.diaspotea.diaspoteaserver.models.ProduitTarif;
import com.diaspotea.diaspoteaserver.models.ProduitTarifID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitTarifRepository extends JpaRepository<ProduitTarif, ProduitTarifID> {
}