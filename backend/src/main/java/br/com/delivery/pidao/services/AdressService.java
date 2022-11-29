package br.com.delivery.pidao.services;


import br.com.delivery.pidao.entities.Address;
import br.com.delivery.pidao.entities.dto.AddressDTO;
import br.com.delivery.pidao.repositories.AdressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AdressService{

    AdressRepository adressRepository;    

    public AddressDTO addAdress(final AddressDTO adressDTO){
        Address newAdress = adressDTO.dtoToEntity();
        adressRepository.save(newAdress);
        return adressDTO;
        
    }

    public Address findByIdentifier(String adressIdentifier) {
        Optional<Address> adress = adressRepository.findByAdressIdentifier(adressIdentifier);
        if(adress.isPresent()){
            return adress.get();
        }
        throw new RuntimeException("Endereço não encontrado");

    }

    public Address isPresent(String adressIdentifier){
        Optional<Address> adress = adressRepository.findByAdressIdentifier(adressIdentifier);
        return adress.isPresent() ? adress.get() : null;
    }

}