package br.com.delivery.pidao.repositories;

import br.com.delivery.pidao.entities.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientOrderRepository extends JpaRepository<ClientOrder, Long> {

    Optional<List<ClientOrder>> findAllByClientIdentifier(String clientIdentifier);

    Optional<ClientOrder> findByUuid(UUID uuid);
}
