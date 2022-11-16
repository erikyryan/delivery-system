package br.com.delivery.pidao.repositories;

import br.com.delivery.pidao.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByDetailsAndMenuIdentifier(String details,String menuIdentifier);

    Optional<Category> findByDetails(String details);

    Optional<Category> findByUuid(UUID uuid);

    List<Category> findByMenuIdentifier(String menuIdentifier);
}
