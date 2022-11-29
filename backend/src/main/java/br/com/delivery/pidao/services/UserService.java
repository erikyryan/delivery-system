package br.com.delivery.pidao.services;


import br.com.delivery.pidao.entities.*;
import br.com.delivery.pidao.entities.dto.*;
import br.com.delivery.pidao.enums.UserTypeEnum;
import br.com.delivery.pidao.repositories.AdressRepository;
import br.com.delivery.pidao.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import validator.*;

import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UsersRepository UsersRepository;

    private SessionService loginSessionService;

    private AdressService adressService;

    private AdressRepository adressRepository;


    public String login(UsersDTO UsersDTO) {
        Users currentUsers = validateLogin(UsersDTO);
        return loginSessionService.generateSession(currentUsers);
    }

    public LoginSession logout(String token) {
        try {
            LoginSession session = loginSessionService.logout(token);
            return session;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private Users validateLogin(UsersDTO UsersS) {
        Optional<Users> Users = UsersRepository.findByEmail(UsersS.getEmail());
        if(!Users.isPresent()){
            throw new RuntimeException("Usuario não encontrado.");
        }else{
            if(!Objects.equals(UsersS.getPassword(), Users.get().getPassword())){
                throw new RuntimeException("Senha inválida!");
            }else{
                return Users.get();
            }
        }
    }

    public Users findByToken(String token) {
        Users Users = this.loginSessionService.findUsers(token);
        return Users;
    }

    public void validateUsersExist(UsersDTO UsersDTO){
        Optional<Users> Users = UsersRepository.findByEmail(UsersDTO.getEmail());
        if(!Users.isEmpty()){
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

    public UsersDTO createUsersCustomer(UsersDTO UsersDTO){
        //this.validateUsersExist(UsersDTO);
        this.validateEmailAndTaxNumber(UsersDTO.getEmail(), UsersDTO.getSocialSecurity());

        Users UsersCustomer = new Users();
        UsersCustomer.setType(UserTypeEnum.CUSTOMER);
        UsersCustomer.setIsAdmin(false);
        
        UsersCustomer = UsersDTO.dtoToEntity();
        Users customer = UsersRepository.save(UsersCustomer);

        AddressDTO addressDTO = UsersDTO.getAddressDTO().dtoAndCustomerIdentifierToAdressDTO(customer.getUserIdentifier());
        adressService.addAdress(addressDTO);

        return UsersDTO;
    }

    public AddressDTO addAdress(AddressDTO addressDTO){
        Address newAdress = addressDTO.dtoToEntity();
        adressRepository.save(newAdress);

        return addressDTO;
    }

    public UsersDTO createUsersManager(UsersDTO UsersDTO){
        this.validateEmailAndTaxNumber( UsersDTO.getEmail(), UsersDTO.getSocialSecurity());

        Users UsersManager = new Users();
        UsersManager.setType(UserTypeEnum.MANAGER);
        UsersManager.setIsAdmin(true);
        
        UsersManager = UsersDTO.dtoToEntity();
        Users manager = UsersRepository.save(UsersManager);

        AddressDTO addressDTO = UsersDTO.getAddressDTO().dtoAndRestaurantIdentifierToAdressDTO(manager.getUserIdentifier());
        adressService.addAdress(addressDTO);

        return UsersDTO;
    }
}
