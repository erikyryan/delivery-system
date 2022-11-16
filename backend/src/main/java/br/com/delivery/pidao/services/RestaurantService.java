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

    public Restaurant findByRestaurantIdentifier(String restaurantIdentifier) {
        UUID uuid = UUID.fromString(restaurantIdentifier);
        Optional<Restaurant> restaurant = restaurantRepository.findByUuid(uuid);

        if(restaurant.isPresent()){
            return restaurant.get();
        }

        throw new RestaurantNotFound("Restaurante não existente");

    }
}
