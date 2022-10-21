package br.com.delivery.pidao.services;

import br.com.delivery.pidao.entities.ClientOrder;
import br.com.delivery.pidao.entities.dto.OrderDTO;
import br.com.delivery.pidao.repositories.ClientOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientOrderService {

    private ClientOrderRepository clientOrderRepository;

    public List<OrderDTO> findAllByClientIdentifier(String clientIdentifier) {
        Optional<List<ClientOrder>> clientOrders = clientOrderRepository.findAllByClientIdentifier(clientIdentifier);

        if(clientOrders.isPresent()) {
            return clientOrders.get().stream().map(order ->
                order.orderToDto()
            ).collect(Collectors.toList());
        }

        throw new RuntimeException("Nenhum pedido foi encontrado!");
    }


}
