package br.com.delivery.pidao.entities.dto;

import javax.security.auth.message.callback.PrivateKeyCallback;

import br.com.delivery.pidao.entities.Adress;
import br.com.delivery.pidao.entities.Client;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDTO {

    private String email;
    private String password;
    private String date;
    private String name;
    private String socialsSecurity;
    private String cellphone;
    private Adress adressClient;

    //TODO: Resgatar o endereço a partir de um ID ou um IDENTIFIER
    public Client dtoToEntity(){
        Client client = new Client();
        client.setName(name);
        client.setEmail(email);
        client.setPassword(password);
        client.setSocialsSecurity(socialsSecurity);
        client.setCellphone(cellphone);
        client.setDate(date);
        //client.setAdressClient(adressClient);
        return client;
    }
}
