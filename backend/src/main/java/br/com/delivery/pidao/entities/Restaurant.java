package br.com.delivery.pidao.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Restaurant extends User {

    @Id
    private Long id;

    @OneToOne
    private Adress adressRestaurant;

    @OneToOne
    private Restaurant restaurant;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Menu menu;

    @OneToMany
    private List<Manager> managerRestaurant;

    private String date;

    private String name;

    private String socialsSecurity;

    private String cellphone;


}
