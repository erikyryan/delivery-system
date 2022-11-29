package br.com.delivery.pidao.entities.dto;

import javax.security.auth.message.callback.PrivateKeyCallback;

import br.com.delivery.pidao.*;

import br.com.delivery.pidao.entities.Address;
import br.com.delivery.pidao.entities.Customer;
import br.com.delivery.pidao.entities.Users;
import lombok.Getter;
import lombok.Setter;
import validator.ValidatorEmail;

@Getter
@Setter
public class ClientDTO {

    private Users users;

    public ClientDTO(){}


    public Customer dtoToEntity(){
        Customer customer = new Customer();
        customer.getUsers();
        return customer;
    }
}
