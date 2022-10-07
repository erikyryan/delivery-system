package br.com.delivery.pidao.repositories;

import br.com.delivery.pidao.entities.Category;
import br.com.delivery.pidao.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByEmailAndPassword(String email, String password);

}
