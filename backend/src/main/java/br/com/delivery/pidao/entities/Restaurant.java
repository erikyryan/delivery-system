package br.com.delivery.pidao.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Restaurant implements Serializable {

    @Id
    private Long id;

    @OneToOne
    private Adress adressRestaurant;

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
