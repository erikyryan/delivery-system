package br.com.delivery.pidao.repositories;

import br.com.delivery.pidao.entities.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceOrderRepository extends JpaRepository<ClientOrder, Long> {

    static void save(Long id_pedido) {
    }
}
