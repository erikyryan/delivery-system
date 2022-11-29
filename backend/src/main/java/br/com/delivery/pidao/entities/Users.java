package br.com.delivery.pidao.entities;

import com.sun.istack.NotNull;

import br.com.delivery.pidao.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Users implements Serializable {

    private String userIdentifier = UUID.randomUUID().toString();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;


    @NotNull
    private String email;

    @NotNull
    private String password;

    private String date;

    private String name;

    private String socialsSecurity;

    private String cellphone;

    private String adressIdentifier;

    private UserTypeEnum type;

    private Boolean isAdmin;

    @OneToOne
    private Transaction transaction;

    @OneToOne
    private Restaurant restaurant;

}
