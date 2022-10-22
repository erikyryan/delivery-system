package br.com.delivery.pidao.services;

import br.com.delivery.pidao.entities.Menu;
import br.com.delivery.pidao.entities.Restaurant;
import br.com.delivery.pidao.exceptions.MenuNotFound;
import br.com.delivery.pidao.repositories.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {

    private MenuRepository menuRepository;

    public Menu getMenu(String menuIdentifier){
        Optional<Menu> menu = menuRepository.findByMenuIdentifier(menuIdentifier);
        if(menu.isPresent()){
            return menu.get();
        }
        throw new MenuNotFound("Menu não encontrado");
    }

    public Restaurant getRestaurantFromIdentifier(String menuIdentifier){
        Optional<Menu> menu = menuRepository.findByMenuIdentifier(menuIdentifier);
        if(menu.isPresent()){
            return menu.get().getRestaurant();
        }
        throw new MenuNotFound("Menu não encontrado");
    }

}
