package com.diaspotea.diaspoteaserver.repository;

import com.diaspotea.diaspoteaserver.dto.TypeProduitDto;
import com.diaspotea.diaspoteaserver.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type, Integer> {

    @Query("select new com.diaspotea.diaspoteaserver.dto.TypeProduitDto(t.id,t.nom) from Type t")
    List<TypeProduitDto> findAllTypeProduitDto();

}