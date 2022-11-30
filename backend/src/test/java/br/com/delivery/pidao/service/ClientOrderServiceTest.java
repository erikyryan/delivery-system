package br.com.delivery.pidao.service;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ClientOrderServiceTest {
//
//    private ClientOrderService clientOrderService;
//
//    @Mock
//    private ClientOrderRepository clientOrderRepository;
//
//    @Before
//    public void setUp(){
//        this.clientOrderService = new ClientOrderService(clientOrderRepository);
//    }
//
//
//    @Test
//    public void shouldCreateClientOrderThenReturnIdentifierClientOrder(){
//        OrderDTO orderDTO =  mock(OrderDTO.class);
//        orderDTO.setClientIdentifier(UUID.randomUUID().toString());
//        orderDTO.setName("Hambuguer");
//        orderDTO.setComment("Sem verdura");
//        orderDTO.setStatus(OrderStatus.ANDAMENTO);
//        orderDTO.setValue("40.00");
//
//        ClientOrder clientOrder = new ClientOrder();
//        clientOrder.setClientOrderIdentifier(UUID.randomUUID().toString());
//
//        when(orderDTO.dtoToEntity()).thenReturn(clientOrder);
//        when(clientOrderRepository.save(clientOrder)).thenReturn(clientOrder);
//
//        Assert.assertEquals(clientOrderService.addClientOrder(orderDTO), clientOrder.getClientOrderIdentifier());
//    }
}
