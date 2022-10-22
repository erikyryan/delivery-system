package br.com.delivery.pidao.repositories;

import br.com.delivery.pidao.entities.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@NoRepositoryBean
public interface DeliveryRepository extends JpaRepository <Delivery,Long> {

    Optional<Delivery> findByEmailAndPassword(String email, String password);

    Optional<Delivery> findByEmail(String email);

    Optional<Delivery> findBySocialSecurity(String socialSecurity);
}
