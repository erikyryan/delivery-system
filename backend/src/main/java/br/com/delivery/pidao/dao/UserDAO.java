package br.com.delivery.pidao.dao;

import br.com.delivery.pidao.entities.Client;
import br.com.delivery.pidao.entities.Delivery;
import br.com.delivery.pidao.entities.Manager;
import br.com.delivery.pidao.entities.dto.UserDTO;
import br.com.delivery.pidao.enums.UserTypeEnum;
import br.com.delivery.pidao.repositories.ClientRepository;
import br.com.delivery.pidao.repositories.DeliveryRepository;
import br.com.delivery.pidao.repositories.ManagerRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.AccessException;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@AllArgsConstructor
public class UserDAO {

    private final ManagerRepository managerRepository;

    private final ClientRepository clientRepository;

    private final DeliveryRepository deliveryRepository;

    public Optional<Manager> isManager(String identifier) throws AccessException {
        return managerRepository.findByUserIdentifier(identifier);
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

    public Optional<Client> isPresent(String socialSecurity){
        return clientRepository.findBySocialsSecurity(socialSecurity);
    }

    public Object valitadeUserEmail(String email){
        Optional <Client> userClient = clientRepository.findByEmail(email);
        if(userClient.isPresent()){
            return userClient;
        }

        Optional <Manager> userManageer = managerRepository.findByEmail(email);
        if(userManageer.isPresent()){
            return userManageer;
        }

        Optional <Delivery> userDelivery = deliveryRepository.findByEmail(email);
        if(userDelivery.isPresent()){
            return userDelivery;
        }

        return null;
    }

    public UserTypeEnum typeUser(String email){
        Optional <Client> userClient = clientRepository.findByEmail(email);
        if(userClient.isPresent()){
            return UserTypeEnum.CUSTOMER;
        }

        Optional <Manager> userManageer = managerRepository.findByEmail(email);
        if(userManageer.isPresent()){
            return UserTypeEnum.MANAGER;
        }

        Optional <Delivery> userDelivery = deliveryRepository.findByEmail(email);
        if(userDelivery.isPresent()){
            return UserTypeEnum.DELIVERYMAN;
        }
        
        return null;
    }

}








