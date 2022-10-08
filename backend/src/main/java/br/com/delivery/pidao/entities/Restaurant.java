package br.com.delivery.pidao.entities;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Restaurant {

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
