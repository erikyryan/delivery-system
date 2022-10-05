package br.com.delivery.pidao.repositories;

import br.com.delivery.pidao.entities.Client;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository {

    Optional<Client> findByEmailAndPassword(String email, String password);

}
