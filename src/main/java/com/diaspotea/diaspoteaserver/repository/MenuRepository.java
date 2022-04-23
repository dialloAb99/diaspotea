package com.diaspotea.diaspoteaserver.repository;

import com.diaspotea.diaspoteaserver.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
}
