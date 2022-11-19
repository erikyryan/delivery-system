package br.com.delivery.pidao.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.delivery.pidao.repositories.ClientOrderRepository;
import br.com.delivery.pidao.services.ClientOrderService;
import br.com.delivery.pidao.entities.ClientOrder;
import br.com.delivery.pidao.entities.dto.*;
import br.com.delivery.pidao.enums.OrderStatus;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ClientOrderServiceTest {
    
    private ClientOrderService clientOrderService;

    @Mock
    private ClientOrderRepository clientOrderRepository;

    @Before
    public void setUp(){
        this.clientOrderService = new ClientOrderService(clientOrderRepository);
    }

    
    @Test
    public void shouldCreateClientOrderThenReturnIdentifierClientOrder(){
        OrderDTO orderDTO =  mock(OrderDTO.class);
        orderDTO.setClientIdentifier(UUID.randomUUID().toString());
        orderDTO.setName("Hambuguer");
        orderDTO.setComment("Sem verdura");
        orderDTO.setStatus(OrderStatus.ANDAMENTO);
        orderDTO.setValue("40.00");

        ClientOrder clientOrder = new ClientOrder();
        clientOrder.setClientOrderIdentifier(UUID.randomUUID().toString());

        when(orderDTO.dtoToEntity()).thenReturn(clientOrder);
        when(clientOrderRepository.save(clientOrder)).thenReturn(clientOrder);

        Assert.assertEquals(clientOrderService.addClientOrder(orderDTO), clientOrder.getClientOrderIdentifier());
    }
}
