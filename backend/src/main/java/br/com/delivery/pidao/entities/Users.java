package br.com.delivery.pidao.entities;

import com.sun.istack.NotNull;

import br.com.delivery.pidao.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
public class Users implements Serializable {

    public Users() {}

    private String userIdentifier = UUID.randomUUID().toString();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


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
    private Customer customer;

    @JoinColumn(name = "restauranteIdentifier")
    private String restauranteIdentifier;

}
