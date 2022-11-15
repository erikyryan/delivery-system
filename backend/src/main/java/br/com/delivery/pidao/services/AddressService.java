package br.com.delivery.pidao.services;


import br.com.delivery.pidao.entities.Address;
import br.com.delivery.pidao.entities.dto.AddressDTO;
import br.com.delivery.pidao.repositories.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressService {

    AddressRepository addressRepository;

    public AddressDTO addAddress(final AddressDTO adressDTO){
        Address newAdress = adressDTO.dtoToEntity();
        addressRepository.save(newAdress);
        return adressDTO;
        
    }

    public Address findByIdentifier(String adressIdentifier) {
        Optional<Address> adress = addressRepository.findByAddressIdentifier(adressIdentifier);
        if(adress.isPresent()){
            return adress.get();
        }
        throw new RuntimeException("Endereço não encontrado");

    }

    public Address isPresent(String adressIdentifier){
        Optional<Address> adress = addressRepository.findByAddressIdentifier(adressIdentifier);
        return adress.isPresent() ? adress.get() : null;
    }

}