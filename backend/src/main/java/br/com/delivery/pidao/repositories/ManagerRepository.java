package br.com.delivery.pidao.repositories;

import br.com.delivery.pidao.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository <Manager, Long> {

    Optional<Manager> findByEmailAndPassword(String email, String password);

    Optional<Manager> findByEmail(String email);

    Optional<Manager> findBySocialsSecurity(String socialSecurity);

    Optional<Manager> findByUserIdentifier(String userIdentifier);
}
