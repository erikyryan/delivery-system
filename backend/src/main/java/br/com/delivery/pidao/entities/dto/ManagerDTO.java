package br.com.delivery.pidao.entities.dto;

import br.com.delivery.pidao.entities.Manager;
import br.com.delivery.pidao.entities.Restaurant;

public class ManagerDTO {

    private String email;
    private String password;
    private String date;
    private String name;
    private String socialsSecurity;
    private String cellphone;
    private String restaurantIdentifier;
    private String department;

    public Manager dtoToEntity(){
        Manager manager = new Manager();
        manager.setName(name);
        manager.setEmail(email);
        manager.setPassword(password);
        manager.setSocialsSecurity(socialsSecurity);
        manager.setCellphone(cellphone);
        manager.setDate(date);
        manager.setRestaurantIdentifier(restaurantIdentifier);
        manager.setDepartment(department);
        return manager;
    }
}


