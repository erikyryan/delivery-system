package br.com.delivery.pidao.repositories;

import br.com.delivery.pidao.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findByNameAndDescriptionAndCategory(String name, String description, String details);

}
