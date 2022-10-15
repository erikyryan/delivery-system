package br.com.delivery.pidao.repositories;

import br.com.delivery.pidao.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.details = :details and c.menu.id = :idMenu")
    Optional<Category> findByDetailsAndIdmenu(String details,Long idMenu);

    Optional<Category> findByDetails(String details);

    Optional<Category> findByCategoryIdentifier(String identifier);
}
