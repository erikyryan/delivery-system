package br.com.delivery.pidao.domain.order;

import br.com.delivery.pidao.domain.order.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

    Optional<List<CustomerOrder>> findAllByCustomerIdentifier(String customerIdentifier);

    Optional<CustomerOrder> findByUuid(UUID uuid);
}
