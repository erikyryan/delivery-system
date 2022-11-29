package br.com.delivery.pidao.entities.dto;

import br.com.delivery.pidao.entities.Transaction;
import br.com.delivery.pidao.entities.Users;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDTO {

    private Users user;

    public ClientDTO(){}


    public Transaction dtoToEntity(){
        Transaction transaction = new Transaction();
        transaction.getUser();
        return transaction;
    }
}
