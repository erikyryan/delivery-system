package br.com.delivery.pidao.entities.dto;

import br.com.delivery.pidao.entities.Menu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MenuDTO {

    private String restaurantIdentifier;

    public Menu dtoToEntity() {
        Menu menu = new Menu();
        menu.setRestaurantIdentifier(restaurantIdentifier);
        return menu;
    }

}
