package br.com.delivery.pidao.domain.user;


import br.com.delivery.pidao.domain.login.SessionService;
import br.com.delivery.pidao.domain.user.UserDAO;
import br.com.delivery.pidao.domain.address.AddressService;
import br.com.delivery.pidao.domain.login.LoginSession;
import br.com.delivery.pidao.domain.user.Users;
import br.com.delivery.pidao.domain.address.dto.AddressDTO;
import br.com.delivery.pidao.domain.user.UserTypeEnum;
import br.com.delivery.pidao.util.validator.ValidatorEmail;
import br.com.delivery.pidao.util.validator.ValidatorTaxNumber;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService {

    private UserDAO userDAO;

    private SessionService loginSessionService;

    private AddressService adressService;

    public String login(Users.UsersDTO UsersDTO) {
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

    private Users validateLogin(Users.UsersDTO UsersS) {
        Users user = userDAO.findByEmail(UsersS.getEmail());

        if(!Objects.equals(UsersS.getPassword(), user.getPassword())){
            throw new RuntimeException("Senha inválida!");
        }else{
            return user;
        }
    }

    public Users findByToken(String token) {
        Users user = this.loginSessionService.findUsers(token);
        return user;
    }

    public void validateUsersExist(Users.UsersDTO UsersDTO){
        Users user = userDAO.findByEmail(UsersDTO.getEmail());
        if(!Objects.equals(user.getEmail(),null)){
            throw new RuntimeException("Email já Existente");
        }
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

    public Users.UsersDTO createUsersCustomer(Users.UsersDTO UsersDTO){
        //this.validateUsersExist(UsersDTO);
        this.validateEmailAndTaxNumber(UsersDTO.getEmail(), UsersDTO.getSocialSecurity());

        Users UsersCustomer = new Users();
        UsersCustomer.setTypeUser(UserTypeEnum.CUSTOMER);
        UsersCustomer.setIsAdmin(false);
        
        UsersCustomer = UsersDTO.dtoToEntity();
        Users customer = userDAO.save(UsersCustomer);

        AddressDTO addressDTO = UsersDTO.getAddressDTO().dtoAndCustomerIdentifierToAdressDTO(customer.getUuid().toString());
        adressService.addAddress(addressDTO);

        return UsersDTO;
    }

    public Users.UsersDTO createUsersManager(Users.UsersDTO UsersDTO){
        this.validateEmailAndTaxNumber( UsersDTO.getEmail(), UsersDTO.getSocialSecurity());

        Users UsersManager = new Users();
        UsersManager.setTypeUser(UserTypeEnum.MANAGER);
        UsersManager.setIsAdmin(true);
        
        UsersManager = UsersDTO.dtoToEntity();
        Users manager = userDAO.save(UsersManager);

        AddressDTO addressDTO = UsersDTO.getAddressDTO().dtoAndRestaurantIdentifierToAdressDTO(manager.getUuid().toString());
        adressService.addAddress(addressDTO);

        return UsersDTO;
    }

    public Boolean isManager(Users.UsersDTO userDTO){
        return userDAO.isManager(userDTO);
    }

}
