package br.com.delivery.pidao.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
public class Restaurant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID uuid;

    private String addressRestaurant;

    private String menuIdentifier;

    private String managerIdentifier;

    private String date;

    private String name;

    private String socialsSecurity;

    private String cellphone;

}
