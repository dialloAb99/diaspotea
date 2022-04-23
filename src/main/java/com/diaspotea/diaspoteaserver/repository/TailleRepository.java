package com.diaspotea.diaspoteaserver.repository;

import com.diaspotea.diaspoteaserver.models.Taille;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TailleRepository extends JpaRepository<Taille, Integer> {
}