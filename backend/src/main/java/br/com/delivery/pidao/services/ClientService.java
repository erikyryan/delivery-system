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
@AllArgsConstructor
public class ClientService {
    
    private UserDAO userDAO;

    private ClientDAO clientDAO;

    private ClientRepository clientRepository;

    public ClientService( ClientRepository clientRepository, UserDAO userDAO) {
        this.clientRepository = clientRepository;
        this.userDAO = userDAO;
    }

    private User checkUserExist(String socialSecurity){
        Optional<Client> user = userDAO.IsPresent(socialSecurity);   
        if(user.isPresent()){
            return user.get();
        }
        throw new RuntimeException("Usuario não existe");
    }

    private int checkTypeLogin(final UserDTO userDTO){
        Optional<Manager> userManeger = userDAO.isManager(userDTO);
        Optional<Client> userCustomer = userDAO.isClient(userDTO);
        boolean userDelivery = userDAO.isDeliveryman(userDTO);
        int result;
        if(userCustomer.isPresent()){
            result = ClientType.CUSTOMER.ordinal();
        }
        else if(userManeger.isPresent()){
            result = ClientType.MANAGER.ordinal();
        }
        else if(userDelivery == true){
            result = ClientType.DELIVERYMAN.ordinal();
        }else{
            throw new RuntimeException("Usuario incorreto!");
        }
        return result;
    }

    public User loginUser(final UserDTO userDTO, final User user){
        //retornar o token -> de acordo com o usuario 
        //retornar o refresh token 
        // retorna o nome do usuario
        checkUserExist(user.getSocialsSecurity());
        int result = checkTypeLogin(userDTO);
        if(result == ClientType.CUSTOMER.ordinal()){
            //tela de login do comprador
        }else if(result == ClientType.MANAGER.ordinal()){
            //tela de login do gerente
        }else if(result == ClientType.DELIVERYMAN.ordinal()){
            //tela de login do entregador 
        }
        return user;  
    }

    private void checkEmailExist(ClientDTO clientDTO){
        boolean emailExist = clientDAO.checkEmailExist(clientDTO);
        if(emailExist == true){
            throw new RuntimeException("Email já cadastrado!");
        }
    }


    public ClientDTO addUser(final ClientDTO clientDTO){
        Optional<Client> client = clientDAO.getClientFromClientDTO(clientDTO);
        if(client.isPresent()){
            throw new RuntimeException("Usuário já cadastrado");
        }else{
            checkEmailExist(clientDTO);
            Client newClient = clientDTO.dtoToEntity();
            clientRepository.save(newClient);
            return clientDTO;
        }
    }

}
