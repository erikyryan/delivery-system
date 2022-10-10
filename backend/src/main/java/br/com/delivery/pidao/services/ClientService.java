package br.com.delivery.pidao.services;

import java.util.Optional;

import org.hibernate.jdbc.ReturningWork;
import org.springframework.stereotype.Service;

import br.com.delivery.pidao.dao.ClientDAO;
import br.com.delivery.pidao.dao.UserDAO;
import br.com.delivery.pidao.entities.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import br.com.delivery.pidao.entities.*;
import br.com.delivery.pidao.enums.*;

@Service
@RequiredArgsConstructor
public class ClientService {
    
    private UserDAO userDAO;

    //private ClientDAO clientDAO;

    //private ClientRepository clientRepository;

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

    private void checkEmailExist(UserDTO userDTO){
        boolean emailExist = userDAO.checkEmailExist(userDTO);
        if(emailExist == true){
            throw new RuntimeException("Email já cadastrado!");
        }
    }



    //metodo de criar endereço

    //metodo de criar cadastro 

    //verificação do email

    //verificação do cpf


}
