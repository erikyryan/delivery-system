package br.com.delivery.pidao.services;

import java.lang.StackWalker.Option;
import java.util.Optional;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.delivery.pidao.dao.*;
import br.com.delivery.pidao.entities.dto.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import br.com.delivery.pidao.entities.*;
import br.com.delivery.pidao.enums.*;
import br.com.delivery.pidao.repositories.*;

@Service
@AllArgsConstructor
public class ClientService {
    
    private final UserDAO userDAO;

    private ClientRepository clientRepository;

    private final ManagerRepository managerRepository;

    private final DeliveryRepository deliveryRepository;

    private SessionService sessionService;

    private AdressService adressService;

    private AdressRepository adressRepository;

    private Optional<Client> findByEmail;

    public boolean validateUserExistreate(UserDTO userDTO){
        Optional<Manager> manager = managerRepository.findByEmail(userDTO.getEmail());
        if(!manager.isEmpty()){
            throw new RuntimeException("Email já Existente");
        }
        
        Optional<Client> client = clientRepository.findByEmail(userDTO.getEmail());
        if(!client.isEmpty()){
            throw new  RuntimeException("Email já Existente");
        }

        Optional<Delivery> delivery = deliveryRepository.findByEmail(userDTO.getEmail());
        if(!delivery.isEmpty()){
            throw new  RuntimeException("Email já Existente");
        }
        
        return false;
    }

    public ClientDTO createUserClient(ClientDTO clientDTO){

        this.validateUserExistreate(clientDTO.toUserDTO());
        
        Client newClient = clientDTO.dtoToEntity();
        clientRepository.save(newClient);
        return clientDTO; 
    }

    public AddressDTO addAdress(AddressDTO addressDTO){
        Address newAdress = addressDTO.dtoToEntity();
        adressRepository.save(newAdress);

        return addressDTO;
    }
/*
 * public ManagerDTO addUserManager(ManagerDTO managerDTO){
        //Optional<Manager> manager = userDAO.valitadeUserEmail(managerDTO.dtoToEntity().getEmail())
        if(!manager.isPresent()){
            Manager newmanager = managerDTO.dtoToEntity();
            managerRepository.save(newmanager);
            return managerDTO;
        }else{
            throw new RuntimeException("Gerente já cadastrado");

        }
    }
*/
    

/*
    public String loginUser(UserDTO userDTO) {
        UserTypeEnum typeUser = userDAO.typeUser(userDTO.getEmail());
        if(typeUser == UserTypeEnum.CUSTOMER){
            
        }else if(typeUser == UserTypeEnum.MANAGER){
           
        }else if(typeUser == UserTypeEnum.DELIVERYMAN){
            
        }

        return sessionService.generateSession();  
   } */
 
   
   public LoginSession logoutUser(String token) {
       try {
           LoginSession session = sessionService.logout(token);
           return session;
       } catch (Exception e) {
           throw new RuntimeException(e.getMessage());
       }
   }
}
