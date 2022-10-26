package br.com.delivery.pidao.repositories;

import br.com.delivery.pidao.entities.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository {

    Optional<Category> findByDetails(String details);

}
