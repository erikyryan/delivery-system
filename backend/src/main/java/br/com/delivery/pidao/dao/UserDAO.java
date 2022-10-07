package br.com.delivery.pidao.dao;

import br.com.delivery.pidao.entities.Client;
import br.com.delivery.pidao.entities.Delivery;
import br.com.delivery.pidao.entities.Manager;
import br.com.delivery.pidao.entities.Restaurant;
import br.com.delivery.pidao.entities.dto.UserDTO;
import br.com.delivery.pidao.repositories.ClientRepository;
import br.com.delivery.pidao.repositories.DeliveryRepository;
import br.com.delivery.pidao.repositories.ManagerRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@AllArgsConstructor
public class UserDAO {

    ManagerRepository managerRepository;

    ClientRepository clientRepository;

    DeliveryRepository deliveryRepository;

    public Restaurant isManager(UserDTO userDTO){
        Optional<Manager> manager = managerRepository.findByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());
        if(manager.isPresent()){
            return manager.get().getRestaurantManager();
        }
        return null;
    }

    public boolean isClient(UserDTO userDTO){
        Optional<Client> client = clientRepository.findByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());
        if(client.isPresent()){
            return true;
        }
        return false;
    }

    public boolean isDeliveryman(UserDTO userDTO){
        Optional<Delivery> deliveryman = deliveryRepository.findByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());
        if(deliveryman.isPresent()){
            return true;
        }
        return false;
    }


}
