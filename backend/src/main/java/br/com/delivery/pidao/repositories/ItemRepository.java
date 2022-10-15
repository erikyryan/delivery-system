package br.com.delivery.pidao.repositories;

import br.com.delivery.pidao.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

    @Query("SELECT i FROM Item i WHERE i.name = :name and i.description = :description and i.category.details = :details")
    Optional<Item> findByNameAndDescriptionAndCategory(String name, String description, String details);

    Optional<Item> findByItemIdentifier(String itemIdentifier);
}
