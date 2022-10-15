package br.com.delivery.pidao.repositories;

import br.com.delivery.pidao.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE c.email = :email and c.password = :password")
    Optional<Client> findByEmailAndPassword(String email, String password);

    @Query("SELECT c FROM Client c WHERE c.socialsSecurity = :socialSecurity")
    Optional<Client> findBySocialSecurity(String socialSecurity);

    @Query("SELECT c FROM Client c WHERE c.email = :email")
    Optional<Client> findByEmail(String email);

    Optional<Client> findByUserIdentifier(String userIdentifier);
}
