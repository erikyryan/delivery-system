package br.com.delivery.pidao.service;

import br.com.delivery.pidao.entities.Address;
import br.com.delivery.pidao.entities.dto.AddressDTO;
import br.com.delivery.pidao.repositories.AddressRepository;
import br.com.delivery.pidao.services.AddressService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class AddressServiceTest {

    private AddressService addressService;

    @Mock
    private AddressRepository addressRepository;

    @Before
    public void setUp(){
        addressService = new AddressService(addressRepository);
    }

    @Test
    public void shouldAddAddressThenReturnAddressDTO(){
        AddressDTO addressDTO = new AddressDTO();
        Address address = addressDTO.dtoToEntity();
        when(addressRepository.save(address)).thenReturn(any());
        Assert.assertEquals(addressService.addAddress(addressDTO),addressDTO);
    }

    @Test
    public void shouldFindAddressByIdentifierThenReturnAddressDTO(){
        String adressIdentifier = UUID.randomUUID().toString();
        Address address = new Address();
        address.setAddressIdentifier(adressIdentifier);
        when(addressRepository.findByAddressIdentifier(adressIdentifier)).thenReturn(Optional.of(address));
        Assert.assertEquals(addressService.findByIdentifier(adressIdentifier),address);
    }

}
