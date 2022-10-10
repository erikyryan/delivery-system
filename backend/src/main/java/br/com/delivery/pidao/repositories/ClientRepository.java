package br.com.delivery.pidao.repositories;

import br.com.delivery.pidao.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByEmailAndPassword(String email, String password);

    Optional<Client> findBySocialSecurity(String socialSecurity);

    Optional<Client> findEmail(String email);

    Optional<Client> findSocialSecurity(String socialSecurity);

}
