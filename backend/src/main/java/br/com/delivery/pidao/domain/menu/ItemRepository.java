package br.com.delivery.pidao.domain.menu;

import br.com.delivery.pidao.domain.menu.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findByNameAndDescriptionAndCategoryUuid(String name, String description, UUID uuid);

    Optional<Item> findByUuid(UUID uuid);

    List<Item> findByCategoryUuid(UUID uuid);
}
