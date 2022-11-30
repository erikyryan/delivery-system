package br.com.delivery.pidao.repositories;

import br.com.delivery.pidao.entities.Menu;
import br.com.delivery.pidao.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Optional<Restaurant> findByUuid(String restaurantIdentifier);

    @Query(value="select r.menuUuid  from Restaurant r where r.menuUuid = :uuid")
    Optional<UUID> findMenuByMenuUuid(String uuid);
}
