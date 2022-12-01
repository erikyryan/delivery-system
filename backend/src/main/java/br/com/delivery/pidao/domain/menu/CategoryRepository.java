package br.com.delivery.pidao.domain.menu;

import br.com.delivery.pidao.domain.menu.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByDetailsAndAndMenuUuid(String details, UUID menuUuid);

    Optional<Category> findByDetails(String details);

    Optional<Category> findByUuid(UUID uuid);

    List<Category> findByMenuUuid(UUID uuid);
}
