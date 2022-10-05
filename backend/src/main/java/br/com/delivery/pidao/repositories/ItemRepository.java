package br.com.delivery.pidao.repositories;

import br.com.delivery.pidao.entities.Item;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository {

    Optional<Item> findByNameAndDescriptionAndCategory(String name, String description, String details);

}
