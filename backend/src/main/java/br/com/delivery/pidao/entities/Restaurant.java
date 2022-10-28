package br.com.delivery.pidao.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Restaurant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String adressRestaurant;

    private String restaurantIdentifier = UUID.randomUUID().toString();

    private String menuIdentifier;

    private String managerIdentifier;

    private String date;

    private String name;

    private String socialsSecurity;

    private String cellphone;

}
