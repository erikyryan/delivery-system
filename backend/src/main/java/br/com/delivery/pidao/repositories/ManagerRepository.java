package br.com.delivery.pidao.repositories;

import br.com.delivery.pidao.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository <Manager, Long> {

    @Query("SELECT m FROM Manager m WHERE m.email = :email and m.password = :password")
    Optional<Manager> findByEmailAndPassword(String email, String password);

    @Query("SELECT m FROM Manager m WHERE m.email = :email")
    Optional<Manager> findByEmail(String email);

    @Query("SELECT m FROM Manager m WHERE m.socialsSecurity = :socialSecurity")
    Optional<Manager> findBySocialSecurity(String socialSecurity);

}
