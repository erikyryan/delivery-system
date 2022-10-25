package br.com.delivery.pidao.entities;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Client extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String adressIdentifier;

    @OneToMany
    private List<ClientOrder> OrderClient;

//    @OneToMany
//    private List<Rating> rating;

}
