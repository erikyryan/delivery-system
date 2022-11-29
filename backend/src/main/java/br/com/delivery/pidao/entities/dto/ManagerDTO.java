package br.com.delivery.pidao.entities.dto;

import br.com.delivery.pidao.entities.Manager;
import br.com.delivery.pidao.entities.Restaurant;
import br.com.delivery.pidao.entities.Users;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerDTO {

    private Users user;



    public Manager dtoToEntity(){
        Manager manager = new Manager();
        manager.getUsers();
        return manager;
    }
}


