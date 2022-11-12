package br.com.delivery.pidao.service;

import br.com.delivery.pidao.entities.Address;
import br.com.delivery.pidao.entities.dto.AddressDTO;
import br.com.delivery.pidao.repositories.AdressRepository;
import br.com.delivery.pidao.services.AdressService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class AddressServiceTest {

    AdressService adressService;

    @Mock
    private AdressRepository adressRepository;

    @Before
    public void setUp(){
        adressService = new AdressService(adressRepository);
    }

    @Test
    public void shouldAddAdressThenReturnAAddressDTO(){
        AddressDTO addressDTO = new AddressDTO();
        Address address = new Address();

        Assert.assertEquals(adressService.addAdress(addressDTO),addressDTO);
    }
}
