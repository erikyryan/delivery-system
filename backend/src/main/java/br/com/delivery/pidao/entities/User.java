package br.com.delivery.pidao.entities;

import com.sun.istack.NotNull;

import br.com.delivery.pidao.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.IdClass;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(User.class)
public class User {

    @NotNull
    private String email;

    @NotNull
    private String password;

    private String userIdentifier = UUID.randomUUID().toString();

    private String date;

    private String name;

    private String socialsSecurity;

    private String cellphone;

    private String adressIdentifier;

    private UserTypeEnum type; 

    private Boolean isAdmin;

}
