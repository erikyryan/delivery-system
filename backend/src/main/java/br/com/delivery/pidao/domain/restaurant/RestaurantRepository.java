package br.com.delivery.pidao.domain.restaurant;

import br.com.delivery.pidao.domain.restaurant.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Optional<Restaurant> findByUuid(UUID uuid);

    @Query(value="select r.menuUuid  from Restaurant r where r.menuUuid = :uuid")
    Optional<UUID> findMenuByMenuUuid(String uuid);
}
