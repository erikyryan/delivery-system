package br.com.delivery.pidao.entities;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Client extends User {

    @Id
    @NotNull
    private Long id;

    private Long AdressClient;

    @OneToMany
    private List<ClientOrder> OrderClient;

//    @OneToMany
//    private List<Rating> rating;

}
