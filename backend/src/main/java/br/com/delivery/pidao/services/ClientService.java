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

    private ClientDAO clientDAO;

    private ClientRepository clientRepository;

    private ManagerRepository managerRepository;

    private DeliveryRepository deliveryRepository;

    private SessionService sessionService;

    public ClientService( ClientRepository clientRepository, UserDAO userDAO) {
        this.clientRepository = clientRepository;
        this.userDAO = userDAO;
    }
  
    private Manager validateLoginManager(UserDTO userDTO) {
        Optional<Manager> manegerLogin = managerRepository.findByEmail(userDTO.getEmail());
		if (!managerRepository.findByEmail(userDTO.getEmail()).isPresent()) {
			throw new RuntimeException("Usuário administrador não encontrado");
		}
        Manager login = manegerLogin.get();

        if(!userDTO.getPassword().equals(login.getPassword())){
            throw new RuntimeException("Senha inválida");
        }

        return login;
	}

    private void validateLoginClient(UserDTO userDTO){
        Optional <Client> clientLogin = clientRepository.findByEmail(userDTO.getEmail());
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

    public ClientDTO addUserClient(final ClientDTO clientDTO){
        Optional<Client> client = clientDAO.getClientFromClientDTO(clientDTO);
        if(!client.isPresent()){
            Client newClient = clientDTO.dtoToEntity();
            clientRepository.save(newClient);
            return clientDTO;
        }else{
            throw new RuntimeException("Usuário já cadastrado");

        }
    }

    public ManagerDTO addUserManager(final UserDTO userDTO, final ManagerDTO managerDTO){
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
