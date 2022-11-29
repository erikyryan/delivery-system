package br.com.delivery.pidao.entities.dto;

import org.hibernate.usertype.UserType;

import br.com.delivery.pidao.entities.Users;
import br.com.delivery.pidao.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersDTO {

    private String email;
    private String password;
    private UserTypeEnum typeUser;
    private String socialSecurity;
    private String date;
    private String name;
    private String cellphone;
    private boolean IsAdmin;
    private AddressDTO addressDTO;


    public UsersDTO(){}

    /*
     * public ClientDTO toCustomerDTO(){
     *   ClientDTO user = new ClientDTO(this.user.getEmail(), this.user.getPassword());
     *   return user;
    }
     */

    public Users dtoToEntity(){
        Users users = new Users();
        users.setName(name);
        users.setEmail(email);
        users.setPassword(password);
        users.setSocialsSecurity(socialSecurity);
        users.setCellphone(cellphone);
        users.setType(typeUser);
        users.setIsAdmin(IsAdmin);
        users.setDate(date);
        return users;
    }
}
