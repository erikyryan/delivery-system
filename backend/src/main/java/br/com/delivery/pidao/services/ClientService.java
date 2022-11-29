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
import validator.ValidatorEmail;
import validator.ValidatorPassword;
import validator.ValidatorTaxNumber;
import br.com.delivery.pidao.entities.*;
import br.com.delivery.pidao.enums.*;
import br.com.delivery.pidao.repositories.*;

@Service
@AllArgsConstructor
public class ClientService {
    
    private final UserDAO userDAO;

    private UsersRepository usersRepository;

    private SessionService sessionService;

    private AdressService adressService;

    private AdressRepository adressRepository;


    public void validateUserExist(UsersDTO userDTO){
        Optional<Users> users = usersRepository.findByEmail(userDTO.getEmail());
        if(!users.isEmpty()){
            throw new RuntimeException("Email já Existente");
        }else{}
    }

    public boolean validateEmailAndTaxNumber(String email, String taxNumber){
        boolean validatorEmail = new ValidatorEmail().emailIsValid(email);
        boolean validatorTaxNumber = new ValidatorTaxNumber().taxNumberIsValid(taxNumber);
        if(validatorEmail == true){
            if(validatorTaxNumber == true){
                return true;
            }else{
                throw new  RuntimeException("CPF/CNPJ ínvalido");
            }
        }else{
            throw new  RuntimeException("Email ínvalido");
        } 
    }

    public UsersDTO createUserCustomer(UsersDTO userDTO){
        this.validateUserExist(userDTO);
        this.validateEmailAndTaxNumber(userDTO.getEmail(), userDTO.getSocialSecurity());

        Users userCustomer = new Users();
        userCustomer.setType(UserTypeEnum.CUSTOMER);
        userCustomer.setIsAdmin(false);
        
        userCustomer = userDTO.dtoToEntity();
        Users customer = usersRepository.save(userCustomer);

        AddressDTO addressDTO = userDTO.getAddressDTO().dtoAndCustomerIdentifierToAdressDTO(customer.getUserIdentifier());
        adressService.addAdress(addressDTO);

        return userDTO;
    }
    
    public UsersDTO createUserManager(UsersDTO userDTO){
        this.validateUserExist(userDTO);
        this.validateEmailAndTaxNumber(userDTO.getEmail(), userDTO.getSocialSecurity());

        Users userManager = new Users();
        userManager.setType(UserTypeEnum.CUSTOMER);
        userManager.setIsAdmin(false);
        
        userManager = userDTO.dtoToEntity();
        Users manager = usersRepository.save(userManager);

        AddressDTO addressDTO = userDTO.getAddressDTO().dtoAndCustomerIdentifierToAdressDTO(manager.getUserIdentifier());
        adressService.addAdress(addressDTO);

        return userDTO;
    }

    public AddressDTO addAdress(AddressDTO addressDTO){
        Address newAdress = addressDTO.dtoToEntity();
        adressRepository.save(newAdress);

        return addressDTO;
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
