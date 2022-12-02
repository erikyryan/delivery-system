package br.com.delivery.pidao.domain.order;

import br.com.delivery.pidao.domain.order.dto.OrderDTO;
import br.com.delivery.pidao.domain.order.entity.CustomerOrder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerOrderService {

    private CustomerOrderRepository customerOrderRepository;

    public List<OrderDTO> findAllByCustomerIdentifier(String customerIdentifier) {
        Optional<List<CustomerOrder>> customerOrders = customerOrderRepository.findAllByCustomerIdentifier(customerIdentifier);

        if(customerOrders.get().isEmpty() || !customerOrders.isPresent()) {
            throw new RuntimeException("Nenhum pedido foi encontrado!");
        }

        return customerOrders.get().stream().map(
                order -> order.orderToDto()).collect(Collectors.toList()
        );

    }

    public OrderDTO addCustomerOrder(OrderDTO orderDTO) {

        if(Objects.equals(orderDTO,null)){
            throw new RuntimeException("Pedido invalido");
        }

        CustomerOrder customerOrder = customerOrderRepository.save(orderDTO.dtoToEntity());
        return customerOrder.orderToDto();
    }

    public OrderDTO getClientOrderByCustomerIdentifier(String customerIdentifier) {
        UUID uuid = UUID.fromString(customerIdentifier);
        Optional<CustomerOrder> customerOrder = customerOrderRepository.findByUuid(uuid);

        if (!customerOrder.isPresent()) {
            throw new RuntimeException("Nenhum pedido foi encontrado com o identificador: " + customerIdentifier);
        }

        return customerOrder.get().orderToDto();
    }

    public Boolean removeCustomerOrder(String customerIdentifier) {
        UUID uuid = UUID.fromString(customerIdentifier);
        Optional<CustomerOrder> clientOrder = customerOrderRepository.findByUuid(uuid);

        if (!clientOrder.isPresent()) {
            throw new RuntimeException("Nenhum pedido foi encontrado com o identificador: " + customerIdentifier);
        }

        customerOrderRepository.delete(clientOrder.get());
        return true;
    }

    public OrderDTO updateCustomerOrder(final OrderDTO orderDTO, String orderIdentifier) {
        UUID uuid = UUID.fromString(orderIdentifier);
        Optional<CustomerOrder> clientOrder = customerOrderRepository.findByUuid(uuid);

        if (!clientOrder.isPresent()) {
            throw new RuntimeException("O pedido " + orderIdentifier + " não foi encontrado");
        }

        CustomerOrder clientOrderNew = clientOrder.get();

        if (!Objects.equals(orderDTO.getName(), null))
            clientOrderNew.setName(orderDTO.getName());

        if (!Objects.equals(orderDTO.getStatus(), null))
            clientOrderNew.setStatus(orderDTO.getStatus());

        if (!Objects.equals(orderDTO.getValue(), null))
            clientOrderNew.setValue(orderDTO.getValue());

        if (!Objects.equals(orderDTO.getComment(), null))
            clientOrderNew.setComment(orderDTO.getComment());

        if (!Objects.equals(orderDTO.getCustomerIdentifier(), null))
            clientOrderNew.setCustomerIdentifier(orderDTO.getCustomerIdentifier());

        customerOrderRepository.save(clientOrderNew);
        return orderDTO;
    }

}
