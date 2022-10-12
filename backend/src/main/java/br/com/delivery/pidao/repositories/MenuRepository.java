package br.com.delivery.pidao.repositories;

import br.com.delivery.pidao.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    Optional<Menu> findByMenuIdentifier(String menuIdentifier);

}
