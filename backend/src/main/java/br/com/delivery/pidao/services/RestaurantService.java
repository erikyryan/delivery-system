package br.com.delivery.pidao.services;

import br.com.delivery.pidao.entities.Restaurant;
import br.com.delivery.pidao.exceptions.RestaurantNotFound;
import br.com.delivery.pidao.repositories.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RestaurantService {

    private RestaurantRepository restaurantRepository;

    public String findByRestaurantIdentifier(String restaurantIdentifier) {

        Optional<Restaurant> restaurant = restaurantRepository.findByRestaurantIdentifier(restaurantIdentifier);

        if(restaurant.isPresent()){
            throw new RestaurantNotFound("Restaurante não existente");
        }

        return restaurant.get().getRestaurantIdentifier();
    }

    public String findMenuByIdentifier(String menuIdentifier) {
        Optional<UUID> menu = restaurantRepository.findMenuByMenuUuid(menuIdentifier);

        if(!menu.isPresent() || menu.isEmpty()){
            throw new RuntimeException("Menu não encontrado");
        }

        return menu.get().toString();
    }
}
