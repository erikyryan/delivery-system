package br.com.delivery.pidao.entities.dto;

import br.com.delivery.pidao.entities.Manager;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerDTO {

    private String email;
    private String password;
    private String date;
    private String name;
    private String socialsSecurity;
    private String cellphone;
    private String restaurantIdentifier;
    private String department;
    private AddressRestaurantDTO addressDTO;

    public UserDTO toUserDTO(){
        UserDTO user = new UserDTO(this.email, this.password);
        return user;
    }

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


