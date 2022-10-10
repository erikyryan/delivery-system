package br.com.delivery.pidao.dao;

import br.com.delivery.pidao.entities.Client;
import br.com.delivery.pidao.entities.Delivery;
import br.com.delivery.pidao.entities.Manager;
import br.com.delivery.pidao.entities.User;
import br.com.delivery.pidao.entities.dto.UserDTO;
import br.com.delivery.pidao.repositories.ClientRepository;
import br.com.delivery.pidao.repositories.DeliveryRepository;
import br.com.delivery.pidao.repositories.ManagerRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class UserDAO {

    ManagerRepository managerRepository;

    ClientRepository clientRepository;

    DeliveryRepository deliveryRepository;


    public Optional<Manager> isManager(UserDTO userDTO){
        return managerRepository.findByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());
    }

    public Optional<Client> isClient(UserDTO userDTO){
        return clientRepository.findByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());
    }

    public boolean isDeliveryman(UserDTO userDTO){
        Optional<Delivery> deliveryman = deliveryRepository.findByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());
        if(deliveryman.isPresent()){
            return true;
        }
        return false;
    }

    public Optional<Client> IsPresent(String socialSecurity){
        return clientRepository.findBySocialSecurity(socialSecurity);
    }

    public boolean checkEmailExist(UserDTO userDTO){
        Optional<Manager> manager = managerRepository.findEmail(userDTO.getEmail());
        Optional<Client> client = clientRepository.findEmail(userDTO.getEmail());
        Optional<Delivery> deliveryman = deliveryRepository.findEmail(userDTO.getEmail());

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








