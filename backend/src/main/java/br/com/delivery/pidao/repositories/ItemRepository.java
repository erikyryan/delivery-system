package br.com.delivery.pidao.repositories;

import br.com.delivery.pidao.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i FROM Item i JOIN i.category c WHERE i.name = :name and i.description = :description and c.details  = :details")
    Optional<Item> findByNameAndDescriptionAndCategory(String name, String description, String details);

    @Query("SELECT i FROM Item i WHERE i.itemIdentifier = :itemIdentifier")
    Optional<Item> findByItemIdentifier(String itemIdentifier);
}
