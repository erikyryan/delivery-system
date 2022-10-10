package br.com.delivery.pidao.repositories;

import br.com.delivery.pidao.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository <Manager, Long> {

    Optional<Manager> findByEmailAndPassword(String email, String password);

    Optional<Manager> findEmail(String email);

    Optional<Manager> findSocialSecurity(String socialSecurity);

}
