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

        if(clientOrders.get().isEmpty() || !clientOrders.isPresent()) {
            throw new RuntimeException("Nenhum pedido foi encontrado!");
        }

        return clientOrders.get().stream().map(
                order -> order.orderToDto()).collect(Collectors.toList()
        );

    }

    public OrderDTO addClientOrder(OrderDTO orderDTO) {

        if(Objects.equals(orderDTO,null)){
            throw new RuntimeException("Pedido invalido");
        }

        ClientOrder clientOrder = clientOrderRepository.save(orderDTO.dtoToEntity());
        return clientOrder.orderToDto();
    }

    public OrderDTO getClientOrderByClientIdentifier(String clientIdentifier) {
        UUID uuid = UUID.fromString(clientIdentifier);
        Optional<ClientOrder> clientOrder = clientOrderRepository.findByUuid(uuid);

        if (!clientOrder.isPresent()) {
            throw new RuntimeException("Nenhum pedido foi encontrado com o identificador: " + clientIdentifier);
        }

        return clientOrder.get().orderToDto();
    }

    public Boolean removeClientOrder(String clientIdentifier) {
        UUID uuid = UUID.fromString(clientIdentifier);
        Optional<ClientOrder> clientOrder = clientOrderRepository.findByUuid(uuid);

        if (!clientOrder.isPresent()) {
            throw new RuntimeException("Nenhum pedido foi encontrado com o identificador: " + clientIdentifier);
        }

        clientOrderRepository.delete(clientOrder.get());
        return true;
    }

    public OrderDTO updateClientOrder(final OrderDTO orderDTO, String orderIdentifier) {
        UUID uuid = UUID.fromString(orderIdentifier);
        Optional<ClientOrder> clientOrder = clientOrderRepository.findByUuid(uuid);

        if (!clientOrder.isPresent()) {
            throw new RuntimeException("O pedido " + orderIdentifier + " não foi encontrado");
        }

        ClientOrder clientOrderNew = clientOrder.get();

        if (!Objects.equals(orderDTO.getName(), null))
            clientOrderNew.setName(orderDTO.getName());

        if (!Objects.equals(orderDTO.getStatus(), null))
            clientOrderNew.setStatus(orderDTO.getStatus());

        if (!Objects.equals(orderDTO.getValue(), null))
            clientOrderNew.setValue(orderDTO.getValue());

        if (!Objects.equals(orderDTO.getComment(), null))
            clientOrderNew.setComment(orderDTO.getComment());

        if (!Objects.equals(orderDTO.getClientIdentifier(), null))
            clientOrderNew.setClientIdentifier(orderDTO.getClientIdentifier());

        clientOrderRepository.save(clientOrderNew);
        return orderDTO;
    }

}
