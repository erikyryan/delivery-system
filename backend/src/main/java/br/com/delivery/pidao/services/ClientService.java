package br.com.delivery.pidao.services;

import java.lang.StackWalker.Option;
import java.util.Optional;

import javax.swing.InternalFrameFocusTraversalPolicy;

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
        Client client = clientRepository.save(newClient);
        AddressDTO addressDTO = clientDTO.getAddressDTO().dtoAndClientIdentifierToAdressDTO(client.getUserIdentifier());
        adressService.addAdress(addressDTO);


        return clientDTO;
    }

    public AddressDTO addAdress(AddressDTO addressDTO){
        Address newAdress = addressDTO.dtoToEntity();
        adressRepository.save(newAdress);

        return addressDTO;
    }

    public ManagerDTO createUserManager(ManagerDTO managerDTO){
        this.validateUserExistreate(managerDTO.toUserDTO());

        Manager newManager = managerDTO.dtoToEntity();
        Manager manager = managerRepository.save(newManager);
        AddressDTO addressDTO = managerDTO.getAddressDTO().dtoAndRestaurantIdentifierToAdressDTO(manager.getUserIdentifier());
        adressService.addAdress(addressDTO);

        return managerDTO;
    }

   public LoginSession logoutUser(String token) {
       try {
           LoginSession session = sessionService.logout(token);
           return session;
       } catch (Exception e) {
           throw new RuntimeException(e.getMessage());
       }
   }
}
