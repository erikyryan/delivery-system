package br.com.delivery.pidao.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import br.com.delivery.pidao.dao.*;
import br.com.delivery.pidao.entities.dto.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import br.com.delivery.pidao.entities.*;
import br.com.delivery.pidao.enums.*;
import br.com.delivery.pidao.repositories.*;
@Service
@RequiredArgsConstructor
public class ClientService {
    
    private UserDAO userDAO;

    private ClientRepository clientRepository;

    private ManagerRepository managerRepository;

    private DeliveryRepository deliveryRepository;

    private SessionService sessionService;

    private AdressService adressService;

    private AdressRepository adressRepository;

    private Manager validateLoginManager(UserDTO userDTO) {
        Optional<Manager> manegerLogin = userDAO.isManager(userDTO);
		if (!manegerLogin.isPresent()) {
			throw new RuntimeException("Usuário administrador não encontrado");
		}
        Manager login = manegerLogin.get();

        if(!userDTO.getPassword().equals(login.getPassword())){
            throw new RuntimeException("Senha inválida");
        }

        return login;
	}

    private void validateLoginClient(UserDTO userDTO){
        Optional <Client> clientLogin = userDAO.isClient(userDTO);
        if(!clientLogin.isPresent()){
            throw new RuntimeException("E-mail inválido");
        }
        User login = clientLogin.get();
        
        if(!userDTO.getPassword().equals(login.getPassword())){
            throw new RuntimeException("Senha inválida");
        } 
    }

    private void validateLoginDelivery(UserDTO userDTO){
        Optional <Delivery> deliveryLogin = deliveryRepository.findByEmail(userDTO.getEmail());
        if(!deliveryLogin.isPresent()){
            throw new RuntimeException("E-mail inválido");
        }
        User login = deliveryLogin.get();
        
        if(!userDTO.getPassword().equals(login.getPassword())){
            throw new RuntimeException("Senha inválida");
        } 
    }

    public ClientDTO addUserClient(final UserDTO userDTO, ClientDTO clientDTO){
      
        Optional<Client> client = userDAO.isClient(userDTO);
        if(!client.isPresent()){
            Client newClient = clientDTO.dtoToEntity();
            clientRepository.save(newClient);
            return clientDTO;
        }else{
            throw new RuntimeException("Usuário já cadastrado");

        }
    }

    public AdressDTO addAdress(final AdressDTO adressDTO){
        Adress newAdress = adressDTO.dtoToEntity();
        adressRepository.save(newAdress);

        return adressDTO;
    }

    public ManagerDTO addUserManager(final UserDTO userDTO, final ManagerDTO managerDTO, final AdressDTO adressDTO){
        Optional<Manager> manager = userDAO.isManager(userDTO);
        if(!manager.isPresent()){
            Manager newmanager = managerDTO.dtoToEntity();
            managerRepository.save(newmanager);
            return managerDTO;
        }else{
            throw new RuntimeException("Gerente já cadastrado");

        }
    }


    public String loginUser(UserDTO userDTO, User user) {
        int typeUser = userDAO.typeUser(userDTO.getEmail());
        if(typeUser == 0){
            validateLoginClient(userDTO);
        }else if(typeUser == 1){
            validateLoginManager(userDTO);
        }else if(typeUser == 2){
            validateLoginDelivery(userDTO);
        }

        return sessionService.generateSession(user);  
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
