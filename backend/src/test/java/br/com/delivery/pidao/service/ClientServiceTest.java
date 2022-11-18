package br.com.delivery.pidao.service;

import br.com.delivery.pidao.dao.UserDAO;
import br.com.delivery.pidao.entities.Client;
import br.com.delivery.pidao.entities.dto.*;
import br.com.delivery.pidao.repositories.AddressRepository;
import br.com.delivery.pidao.repositories.ClientRepository;
import br.com.delivery.pidao.repositories.DeliveryRepository;
import br.com.delivery.pidao.repositories.ManagerRepository;
import br.com.delivery.pidao.services.AddressService;
import br.com.delivery.pidao.services.ClientService;
import br.com.delivery.pidao.services.SessionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import validator.ValidatorEmail;
import validator.ValidatorTaxNumber;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ClientServiceTest {

    private ClientService clientService;

    @Mock
    private UserDAO userDAO;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ManagerRepository managerRepository;

    @Mock
    private DeliveryRepository deliveryRepository;

    @Mock
    private SessionService sessionService;

    @Mock
    private AddressService addressService;

    @Mock
    private AddressRepository addressRepository;

    @Before
    public void setUp(){
        clientService = new ClientService(userDAO,clientRepository,managerRepository,
                deliveryRepository,sessionService, addressService, addressRepository);
    }

    @Test
    public void shouldCreateUserClientWithSocialSecurityWrongThenReturnAException(){
        ClientDTO clientDTO = new ClientDTO();
        AddressClientDTO addressClientDTO = new AddressClientDTO("publicPlace","number","zipCode","neighborhood","state","city","details");
        clientDTO.setAddressDTO(addressClientDTO);
        clientDTO.setEmail("joseraimundo@gmail.com");
        clientDTO.setPassword("JoseKSGDFD@1723!2345");
        clientDTO.setSocialsSecurity("731485.580-30");
        AddressDTO addressDTO = addressClientDTO.dtoAndClientIdentifierToAddressDTO(UUID.randomUUID().toString());
        Client client = clientDTO.dtoToEntity();
        client.setUserIdentifier(UUID.randomUUID().toString());

        when(addressService.addAddress(addressDTO)).thenReturn(addressDTO);
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Assert.assertThrows(RuntimeException.class,() -> clientService.createUserClient(clientDTO));
    }

    @Test
    public void shouldCreateUserManagerThenReturnAManagerDTO(){
        ManagerDTO managerDTO = new ManagerDTO();
        AddressRestaurantDTO addressRestaurantDTO = new AddressRestaurantDTO("publicPlace","number","zipCode","neighborhood","state","city","details");
        managerDTO.setAddressDTO(addressRestaurantDTO);
        managerDTO.setEmail("joseraimundo@gmail.com");
        managerDTO.setPassword("JoseKSGDFD@1723!2345");
        managerDTO.setSocialsSecurity("731485.580-30");

        AddressDTO addressDTO = addressRestaurantDTO.dtoAndRestaurantIdentifierToAddressDTO(UUID.randomUUID().toString());

        when(addressService.addAddress(addressDTO)).thenReturn(addressDTO);
        when(managerRepository.save(managerDTO.dtoToEntity())).thenReturn(managerDTO.dtoToEntity());

        Assert.assertEquals(clientService.createUserManager(managerDTO),managerDTO);
    }

    @Test
    public void shouldWhenManagerEmailIsInvalidThenExpectException(){
        ManagerDTO managerDTO = new ManagerDTO();
        AddressRestaurantDTO addressRestaurantDTO = new AddressRestaurantDTO("publicPlace","number","zipCode","neighborhood","state","city","details");
        managerDTO.setAddressDTO(addressRestaurantDTO);
        managerDTO.setEmail("joseraimundogmail.com");
        managerDTO.setPassword("JoseKSGDFD@1723!2345");
        managerDTO.setSocialsSecurity("731.485.580-30");

        AddressDTO addressDTO = addressRestaurantDTO.dtoAndRestaurantIdentifierToAddressDTO(UUID.randomUUID().toString());

        when(addressService.addAddress(addressDTO)).thenReturn(addressDTO);
        when(managerRepository.save(managerDTO.dtoToEntity())).thenReturn(managerDTO.dtoToEntity());

        try{
            clientService.createUserManager(managerDTO);
        }catch (Exception e){
            Assert.assertEquals(e.getMessage(),"Email ínvalido");

        }
    }

    @Test
    public void shouldWhenManagerEmailIsSavedThenExpectException(){
        ManagerDTO managerDTO = new ManagerDTO();
        String email = "joseraimundo@gmail.com";
        AddressRestaurantDTO addressRestaurantDTO = new AddressRestaurantDTO("publicPlace","number","zipCode","neighborhood","state","city","details");
        managerDTO.setAddressDTO(addressRestaurantDTO);
        managerDTO.setEmail(email);
        managerDTO.setPassword("JoseKSGDFD@1723!2345");
        managerDTO.setSocialsSecurity("731.485.580-30");

        AddressDTO addressDTO = addressRestaurantDTO.dtoAndRestaurantIdentifierToAddressDTO(UUID.randomUUID().toString());

        when(addressService.addAddress(addressDTO)).thenReturn(addressDTO);
        when(managerRepository.findByEmail(email)).thenReturn(Optional.of(managerDTO.dtoToEntity()));
        when(managerRepository.save(managerDTO.dtoToEntity())).thenReturn(managerDTO.dtoToEntity());

        try{
            clientService.createUserManager(managerDTO);
        }catch (Exception e){
            Assert.assertEquals(e.getMessage(),"Email já Existente");
        }
    }


}
