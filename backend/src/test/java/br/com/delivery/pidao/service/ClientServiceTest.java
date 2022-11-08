package br.com.delivery.pidao.service;

import br.com.delivery.pidao.dao.UserDAO;
import br.com.delivery.pidao.entities.Address;
import br.com.delivery.pidao.entities.Client;
import br.com.delivery.pidao.entities.dto.AddressDTO;
import br.com.delivery.pidao.entities.dto.ClientDTO;
import br.com.delivery.pidao.entities.dto.ManagerDTO;
import br.com.delivery.pidao.entities.dto.UserDTO;
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
import validator.ValidatorEmail;
import validator.ValidatorPassword;
import validator.ValidatorTaxNumber;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
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
        clientService = new ClientService(userDAO,clientRepository,managerRepository, deliveryRepository,sessionService,adressService,adressRepository);

    }

    @Test
    public void shouldCreateUserClientThenReturnAClientDTO(){
        ClientDTO clientDTO = new ClientDTO();
        AddressDTO addressDTO = new AddressDTO();
        clientDTO.setEmail("joseraimundo@gmail.com");
        clientDTO.setPassword("JoseKSGDFD@1723!2345");
        clientDTO.setSocialsSecurity("731.485.580-30");

//        when(clientService.validateEmailAndPasswordAndTaxNumber(clientDTO.getEmail(), clientDTO.getPassword(), clientDTO.getSocialsSecurity())).thenReturn(true);
//        when(clientRepository.save(clientDTO.dtoToEntity())).thenReturn(clientDTO.dtoToEntity());
//        when(clientDTO.getAddressDTO().dtoAndClientIdentifierToAdressDTO(anyString())).thenReturn(addressDTO);
//        when(adressService.addAdress(addressDTO)).thenReturn(addressDTO);
//        when(managerRepository.findByEmail(anyString())).thenReturn(any());
//        when(clientRepository.findByEmail(anyString())).thenReturn(any());
//        when(deliveryRepository.findByEmail(anyString())).thenReturn(any());

        Assert.assertEquals(clientService.createUserClient(clientDTO),clientDTO);

    }

    @Test
    public void shouldCreateUserManagerThenReturnAManagerDTO(){
        ManagerDTO managerDTO = new ManagerDTO();
        AddressDTO addressDTO = new AddressDTO();
        managerDTO.setEmail("joseraimundo@gmail.com");
        managerDTO.setPassword("JoseKSGDFD@1723!2345");
        managerDTO.setSocialsSecurity("731.485.580-30");

//        when(clientService.validateEmailAndPasswordAndTaxNumber(managerDTO.getEmail(), managerDTO.getPassword(), managerDTO.getSocialsSecurity())).thenReturn(true);
//        when(managerDTO.getAddressDTO().dtoAndRestaurantIdentifierToAdressDTO(anyString())).thenReturn(addressDTO);
//        when(adressService.addAdress(addressDTO)).thenReturn(addressDTO);
//        when(managerRepository.findByEmail(anyString())).thenReturn(any());

        Assert.assertEquals(clientService.createUserManager(managerDTO),managerDTO);
    }

    @Test
    public void shouldAddAdressThenReturnAManagerDTO(){
        ManagerDTO managerDTO = new ManagerDTO();
        AddressDTO addressDTO = new AddressDTO();
        managerDTO.setEmail("joseraimundo@gmail.com");
        managerDTO.setPassword("JoseKSGDFD@1723!2345");
        managerDTO.setSocialsSecurity("731.485.580-30");
//
//        when(clientService.validateEmailAndPasswordAndTaxNumber(managerDTO.getEmail(), managerDTO.getPassword(), managerDTO.getSocialsSecurity())).thenReturn(true);
//        when(managerDTO.getAddressDTO().dtoAndRestaurantIdentifierToAdressDTO(anyString())).thenReturn(addressDTO);
//        when(adressService.addAdress(addressDTO)).thenReturn(addressDTO);
//        when(managerRepository.findByEmail(anyString())).thenReturn(any());

        Assert.assertEquals(clientService.createUserManager(managerDTO),managerDTO);
    }

}
