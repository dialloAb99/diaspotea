package com.diaspotea.diaspoteaserver.services;

import com.diaspotea.diaspoteaserver.models.Menu;
import com.diaspotea.diaspoteaserver.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class MenuService {
    private MenuRepository menuRepository;
    public MenuService(MenuRepository menuRepository){
        {this.menuRepository=menuRepository;}
    }

    public Menu ajouterMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public Menu recupereMenu(int id) {
        return menuRepository.findById(id).orElse(null);
    }

    public Menu modifierMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public void deleteMenu(int id) {
        menuRepository.deleteById(id);
    }

    public List<Menu> recupereToutMenu() {
        return  menuRepository.findAll();
    }

    public void deleteMenu(Menu menu) {
        menuRepository.delete(menu);
    }
}
