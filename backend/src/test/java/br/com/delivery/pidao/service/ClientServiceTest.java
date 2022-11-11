package br.com.delivery.pidao.service;

import br.com.delivery.pidao.dao.UserDAO;
import br.com.delivery.pidao.entities.dto.*;
import br.com.delivery.pidao.repositories.AdressRepository;
import br.com.delivery.pidao.repositories.ClientRepository;
import br.com.delivery.pidao.repositories.DeliveryRepository;
import br.com.delivery.pidao.repositories.ManagerRepository;
import br.com.delivery.pidao.services.AdressService;
import br.com.delivery.pidao.services.ClientService;
import br.com.delivery.pidao.services.SessionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.UUID;

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
    private AdressService adressService;

    @Mock
    private AdressRepository adressRepository;

    @Before
    public void setUp(){
        clientService = new ClientService(userDAO,clientRepository,managerRepository,
                deliveryRepository,sessionService,adressService,adressRepository);
    }

    @Test
    public void shouldCreateUserClientThenReturnAClientDTO(){
        ClientDTO clientDTO = new ClientDTO();
        AddressClientDTO addressClientDTO = new AddressClientDTO("publicPlace","number","zipCode","neighborhood","state","city","details");
        clientDTO.setAddressDTO(addressClientDTO);
        clientDTO.setEmail("joseraimundo@gmail.com");
        clientDTO.setPassword("JoseKSGDFD@1723!2345");
        clientDTO.setSocialsSecurity("731.485.580-30");

        AddressDTO addressDTO = addressClientDTO.dtoAndClientIdentifierToAdressDTO(UUID.randomUUID().toString());

        when(clientRepository.save(clientDTO.dtoToEntity())).thenReturn(clientDTO.dtoToEntity());
        when(adressService.addAdress(addressDTO)).thenReturn(addressDTO);
        when(clientRepository.save(clientDTO.dtoToEntity())).thenReturn(clientDTO.dtoToEntity());

        Assert.assertEquals(clientService.createUserClient(clientDTO),clientDTO);
    }

    @Test
    public void shouldCreateUserManagerThenReturnAManagerDTO(){
        ManagerDTO managerDTO = new ManagerDTO();
        AdressRestaurantDTO adressRestaurantDTO = new AdressRestaurantDTO("publicPlace","number","zipCode","neighborhood","state","city","details");
        managerDTO.setAddressDTO(adressRestaurantDTO);
        managerDTO.setEmail("joseraimundo@gmail.com");
        managerDTO.setPassword("JoseKSGDFD@1723!2345");
        managerDTO.setSocialsSecurity("731485.580-30");

        AddressDTO addressDTO = adressRestaurantDTO.dtoAndRestaurantIdentifierToAdressDTO(UUID.randomUUID().toString());

        when(adressService.addAdress(addressDTO)).thenReturn(addressDTO);
        when(managerRepository.save(managerDTO.dtoToEntity())).thenReturn(managerDTO.dtoToEntity());

        Assert.assertEquals(clientService.createUserManager(managerDTO),managerDTO);
    }

    @Test
    public void shouldWhenManagerEmailIsInvalidThenExpectException(){
        ManagerDTO managerDTO = new ManagerDTO();
        AdressRestaurantDTO adressRestaurantDTO = new AdressRestaurantDTO("publicPlace","number","zipCode","neighborhood","state","city","details");
        managerDTO.setAddressDTO(adressRestaurantDTO);
        managerDTO.setEmail("joseraimundo@gmail.com");
        managerDTO.setPassword("JoseKSGDFD@1723!2345");
        managerDTO.setSocialsSecurity("731.485.580-30");

        AddressDTO addressDTO = adressRestaurantDTO.dtoAndRestaurantIdentifierToAdressDTO(UUID.randomUUID().toString());

        when(adressService.addAdress(addressDTO)).thenReturn(addressDTO);
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
        AdressRestaurantDTO adressRestaurantDTO = new AdressRestaurantDTO("publicPlace","number","zipCode","neighborhood","state","city","details");
        managerDTO.setAddressDTO(adressRestaurantDTO);
        managerDTO.setEmail(email);
        managerDTO.setPassword("JoseKSGDFD@1723!2345");
        managerDTO.setSocialsSecurity("731.485.580-30");

        AddressDTO addressDTO = adressRestaurantDTO.dtoAndRestaurantIdentifierToAdressDTO(UUID.randomUUID().toString());

        when(adressService.addAdress(addressDTO)).thenReturn(addressDTO);
        when(managerRepository.findByEmail(email)).thenReturn(Optional.of(managerDTO.dtoToEntity()));
        when(managerRepository.save(managerDTO.dtoToEntity())).thenReturn(managerDTO.dtoToEntity());

        try{
            clientService.createUserManager(managerDTO);
        }catch (Exception e){
            Assert.assertEquals(e.getMessage(),"Email já Existente");
        }
    }
    
    @Test
	public void shouldWhenClientIsSavedThenExpectMessage() {
		ClientDTO clientDTO = new ClientDTO();
		String email = "joseraimundo@gmail.com";
		AdressRestaurantDTO adressRestaurantDTO = new AdressRestaurantDTO("publicPlace", "number", "zipCode",
				"neighborhood", "state", "city", "details");
		clientDTO.setAddressDTO(adressRestaurantDTO);
		clientDTO.setEmail(email);
		clientDTO.setPassword("JoseKSGDFD@1723!2345");
		clientDTO.setSocialsSecurity("731.485.580-30");

		AddressDTO addressDTO = adressRestaurantDTO.dtoAndRestaurantIdentifierToAdressDTO(UUID.randomUUID().toString());

		when(adressService.addAdress(addressDTO)).thenReturn(addressDTO);
		when(clientRepository.findByEmail(email)).thenReturn(Optional.of(clientDTO.dtoToEntity()));
		when(clientRepository.save(clientDTO.dtoToEntity())).thenReturn(clientDTO.dtoToEntity());

		try {
			clientService.createUserClient(clientDTO);
		} catch (Exception e) {
			Assert.assertEquals(e.getMessage(), "Email já Existente");
		}
	}

}
