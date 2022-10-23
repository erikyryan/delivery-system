package br.com.delivery.pidao.services;


import br.com.delivery.pidao.entities.Adress;
import br.com.delivery.pidao.entities.Category;
import br.com.delivery.pidao.entities.Menu;
import br.com.delivery.pidao.entities.dto.AdressDTO;
import br.com.delivery.pidao.entities.dto.CategoryDTO;
import br.com.delivery.pidao.exceptions.CategoryNotFound;
import br.com.delivery.pidao.repositories.AdressRepository;
import br.com.delivery.pidao.repositories.CategoryRepository;
import br.com.delivery.pidao.repositories.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdressService{

    AdressRepository adressRepository;    

    public AdressDTO addAdress(final AdressDTO adressDTO){
        Adress newAdress = adressDTO.dtoToEntity();
        adressRepository.save(newAdress);
        return adressDTO;
        
    }

    public Adress findByIdentifier(String adressIdentifier) {
        Optional<Adress> adress = adressRepository.findByAdressIdentifier(adressIdentifier);
        if(adress.isPresent()){
            return adress.get();
        }
        throw new RuntimeException("Endereço não encontrado");

    }

    public Adress isPresent(String adressIdentifier){
        Optional<Adress> adress = adressRepository.findByAdressIdentifier(adressIdentifier);
        return adress.isPresent() ? adress.get() : null;
    }

}