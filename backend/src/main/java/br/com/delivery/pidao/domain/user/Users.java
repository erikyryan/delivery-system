package br.com.delivery.pidao.domain.user;

import br.com.delivery.pidao.domain.restaurant.Restaurant;
import br.com.delivery.pidao.domain.order.Transaction;
import br.com.delivery.pidao.domain.address.dto.AddressDTO;
import com.sun.istack.NotNull;

import lombok.*;

import javax.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID uuid;


    @NotNull
    private String email;

    @NotNull
    private String password;

    private String date;

    private String name;

    private String socialsSecurity;

    private String cellphone;

    private UserTypeEnum type;

    private Boolean isAdmin;

    @OneToOne
    private Transaction transaction;

    @OneToOne
    private Restaurant restaurant;

    @Column(name = "address_id")
    private UUID addressUuid;

    @Getter
    @Setter
    public static class UsersDTO {

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
}
