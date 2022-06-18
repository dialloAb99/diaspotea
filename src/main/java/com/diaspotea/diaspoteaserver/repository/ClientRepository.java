package com.diaspotea.diaspoteaserver.repository;

import com.diaspotea.diaspoteaserver.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}