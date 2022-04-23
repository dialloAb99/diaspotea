package com.diaspotea.diaspoteaserver.services;

import com.diaspotea.diaspoteaserver.models.Menu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest

public class MenuServiceTest {
    @Autowired
    private MenuService menuService;
    @Test
    @Transactional
    void ajouterMenu(){
        Menu menu=new Menu();
        Menu menuAjouter=menuService.ajouterMenu(menu);
        assertThat(menuAjouter).isEqualTo(menu);
    }
    @Test
    @Transactional
    void modifierMenu(){
        Menu menu=menuService.recupereMenu(1);
        menu.setNom("box midi");
        Menu menuModifier=menuService.modifierModifier(menu);
        assertThat(menuModifier).isEqualTo(menu);
    }
    @Test
    @Transactional
    void supprimerMenu(){
        menuService.deleteMenu(1);
        Menu menu=menuService.recupereMenu(1);
        assertThat(menu).isNull();
    }
}
