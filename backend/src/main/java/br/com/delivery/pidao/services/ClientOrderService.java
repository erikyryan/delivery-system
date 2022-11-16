package br.com.delivery.pidao.services;

import br.com.delivery.pidao.entities.ClientOrder;
import br.com.delivery.pidao.entities.dto.OrderDTO;
import br.com.delivery.pidao.repositories.ClientOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
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

    public String addClientOrder(OrderDTO orderDTO) {
        ClientOrder clientOrder = clientOrderRepository.save(orderDTO.dtoToEntity());
        return  clientOrder.getUuid().toString();
    }

    public OrderDTO getClientOrderByClientIdentifier(String clientIdentifier) {
        UUID uuid = UUID.fromString(clientIdentifier);
        Optional<ClientOrder> clientOrder = clientOrderRepository.findByUuid(uuid);

        if (clientOrder.isPresent()) {
            return clientOrder.get().orderToDto();
        } else {
            throw new RuntimeException("Nenhum pedido foi encontrado com o identificador: " + clientIdentifier);
        }
    }

    public Boolean removeClientOrder(String clientIdentifier) {
        UUID uuid = UUID.fromString(clientIdentifier);
        Optional<ClientOrder> clientOrder = clientOrderRepository.findByUuid(uuid);

        if (clientOrder.isPresent()) {
            clientOrderRepository.delete(clientOrder.get());
            return true;
        } else {
            throw new RuntimeException("Nenhum pedido foi encontrado com o identificador: " + clientIdentifier);
        }
    }

    public OrderDTO updateClientOrder(final OrderDTO orderDTO, String orderIdentifier) {
        UUID uuid = UUID.fromString(orderIdentifier);
        Optional<ClientOrder> clientOrder = clientOrderRepository.findByUuid(uuid);

        if (clientOrder.isPresent()) {
            ClientOrder clientOrder1 = clientOrder.get();

            if (!Objects.equals(orderDTO.getName(), null)) {
                clientOrder1.setName(orderDTO.getName());
            }

            if (!Objects.equals(orderDTO.getStatus(), null)) {
                clientOrder1.setStatus(orderDTO.getStatus());
            }

            if (!Objects.equals(orderDTO.getValue(), null)) {
                clientOrder1.setValue(orderDTO.getValue());
            }

            if (!Objects.equals(orderDTO.getComment(), null)) {
                clientOrder1.setComment(orderDTO.getComment());
            }

            if (!Objects.equals(orderDTO.getClientIdentifier(), null)) {
                clientOrder1.setClientIdentifier(orderDTO.getClientIdentifier());
            }

            clientOrderRepository.save(clientOrder1);
            return orderDTO;
        } else {
            throw new RuntimeException("O pedido " + orderIdentifier + " não foi encontrado");
        }
    }


}
