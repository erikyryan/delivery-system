package br.com.delivery.pidao.repositories;

import br.com.delivery.pidao.entities.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceOrderRepository extends JpaRepository<ClientOrder, Long> {

}
