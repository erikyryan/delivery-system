package br.com.delivery.pidao.services;


import br.com.delivery.pidao.entities.Address;
import br.com.delivery.pidao.entities.dto.AddressDTO;
import br.com.delivery.pidao.repositories.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AddressService {

    AddressRepository adressRepository;

    public AddressDTO addAddress(final AddressDTO adressDTO){
        Address newAdress = adressDTO.dtoToEntity();
        adressRepository.save(newAdress);
        return adressDTO;
        
    }

    public Address findByIdentifier(String adressIdentifier) {
        UUID uuid = UUID.fromString(adressIdentifier);
        Optional<Address> adress = adressRepository.findByUuid(uuid);
        if(adress.isPresent()){
            return adress.get();
        }
        throw new RuntimeException("Endereço não encontrado");

    }

}