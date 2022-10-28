package br.com.delivery.pidao.repositories;

import br.com.delivery.pidao.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByEmailAndPassword(String email, String password);

    Optional<Client> findBySocialsSecurity(String socialSecurity);

    Optional<Client> findByEmail(String email);

    Optional<Client> findByUserIdentifier(String userIdentifier);
}
