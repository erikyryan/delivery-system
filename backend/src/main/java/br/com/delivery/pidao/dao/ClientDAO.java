package br.com.delivery.pidao.dao;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.delivery.pidao.repositories.*;
import br.com.delivery.pidao.entities.*;
import br.com.delivery.pidao.entities.dto.*;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ClientDAO {

    ManagerRepository managerRepository;

    ClientRepository clientRepository;

    DeliveryRepository deliveryRepository;

    public Optional<Client> IsPresent(String socialSecurity){
        return clientRepository.findBySocialsSecurity(socialSecurity);
    }

    public Optional<Client> getClientFromClientDTO(ClientDTO clientDTO){
        return clientRepository.findBySocialsSecurity(clientDTO.getSocialsSecurity());
    }

    public boolean checkEmailExist(ClientDTO clientDTO){
        Optional<Manager> manager = managerRepository.findByEmail(clientDTO.getEmail());
        Optional<Client> client = clientRepository.findByEmail(clientDTO.getEmail());
        Optional<Delivery> deliveryman = deliveryRepository.findByEmail(clientDTO.getEmail());
        if(manager.isPresent()){
            return true;
        }else if(client.isPresent()){
            return true;
        }else if(deliveryman.isPresent()){
            return true;
        }
        return false;
    }
    
}
