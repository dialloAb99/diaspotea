package com.diaspotea.diaspoteaserver.repository;

import com.diaspotea.diaspoteaserver.dto.TailleDto;
import com.diaspotea.diaspoteaserver.models.Taille;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TailleRepository extends JpaRepository<Taille, Integer> {
    @Query("select new com.diaspotea.diaspoteaserver.dto.TailleDto(taille.id,taille.name) from Taille taille")
    List<TailleDto> recupereTailleDtos();
}