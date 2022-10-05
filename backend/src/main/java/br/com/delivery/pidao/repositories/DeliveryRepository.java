package br.com.delivery.pidao.repositories;

import br.com.delivery.pidao.entities.Delivery;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryRepository {

    Optional<Delivery> findByEmailAndPassword(String email, String password);
}
