package br.com.delivery.pidao.entities.dto;

import javax.security.auth.message.callback.PrivateKeyCallback;

import br.com.delivery.pidao.*;

import br.com.delivery.pidao.entities.Address;
import br.com.delivery.pidao.entities.Client;
import lombok.Getter;
import lombok.Setter;
import validator.ValidatorEmail;

@Getter
@Setter
public class ClientDTO {

    private String email;
    private String password;
    private String date;
    private String name;
    private String socialsSecurity;
    private String cellphone;
    private AddressClientDTO addressDTO;


    public UserDTO toUserDTO(){
        UserDTO user = new UserDTO(this.email, this.password);
        return user;
    }

    public Client dtoToEntity(){
        Client client = new Client();
        client.setName(name);
        client.setEmail(email);
        client.setPassword(password);
        client.setSocialsSecurity(socialsSecurity);
        client.setCellphone(cellphone);
        client.setDate(date);
        return client;
    }
}
