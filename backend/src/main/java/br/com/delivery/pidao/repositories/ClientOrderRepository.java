package br.com.delivery.pidao.repositories;

import br.com.delivery.pidao.entities.Client;
import br.com.delivery.pidao.entities.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientOrderRepository extends JpaRepository<ClientOrder, Long> {

    Optional<List<ClientOrder>> findAllByClientIdentifier(String clientIdentifier);

    Optional<ClientOrder> findByClientOrderIdentifier(String clientIdentifier);
}
