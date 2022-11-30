package br.com.delivery.pidao.domain.address;


import br.com.delivery.pidao.domain.address.dto.AddressDTO;
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