package br.com.delivery.pidao.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.delivery.pidao.repositories.*;
import br.com.delivery.pidao.entities.*;
import br.com.delivery.pidao.entities.dto.*;

public class ClientDAO {

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    DeliveryRepository deliveryRepository;


    public Optional<Client> IsPresent(String socialSecurity){
        return clientRepository.findBySocialSecurity(socialSecurity);
    }

    public Optional<Client> getClientFromClientDTO(ClientDTO clientDTO){
        return clientRepository.findBySocialSecurity(clientDTO.getSocialsSecurity());
    }

    public boolean checkEmailExist(ClientDTO clientDTO){
        Optional<Manager> manager = managerRepository.findByEmail(clientDTO.getEmail());
        Optional<Client> client = clientRepository.findByEmail(clientDTO.getEmail());
        Optional<Delivery> deliveryman = deliveryRepository.findEmail(clientDTO.getEmail());
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
