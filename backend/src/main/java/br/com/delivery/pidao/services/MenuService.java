package br.com.delivery.pidao.services;

import br.com.delivery.pidao.entities.Menu;
import br.com.delivery.pidao.entities.Restaurant;
import br.com.delivery.pidao.exceptions.MenuNotFound;
import br.com.delivery.pidao.repositories.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MenuService {

    private MenuRepository menuRepository;

    private RestaurantService restaurantService;

    public Menu addMenu(final String restaurantIdentifier) {
        Menu newMenu = new Menu(restaurantIdentifier);
        menuRepository.save(newMenu);
        return newMenu;
    }

    public Menu findMenuByIdentifier(String menuIdentifier){
        UUID uuid = UUID.fromString(menuIdentifier);
        Optional<Menu> menu = menuRepository.findByUuid(uuid);
        if(menu.isPresent()){
            return menu.get();
        }
        throw new MenuNotFound("Menu não encontrado");
    }

    public Restaurant getRestaurantFromIdentifier(String menuIdentifier){
        UUID uuid = UUID.fromString(menuIdentifier);
        Optional<Menu> menu = menuRepository.findByUuid(uuid);
        if(menu.isPresent()) {
            return restaurantService.findByRestaurantIdentifier(menu.get().getRestaurantIdentifier());
        }

        throw new MenuNotFound("Menu não encontrado");
    }

    public Boolean deleteMenu(String menuIdentifier) {
        UUID uuid = UUID.fromString(menuIdentifier);
        Optional<Menu> menu = menuRepository.findByUuid(uuid);

        if(menu.isPresent()){
            menuRepository.delete(menu.get());
            return true;
        }else{
            throw new MenuNotFound("Menu não existente");
        }
    }

}
