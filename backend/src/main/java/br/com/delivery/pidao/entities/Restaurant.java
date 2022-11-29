package br.com.delivery.pidao.entities;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Restaurant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    private String adressRestaurant;

    private String restaurantIdentifier = UUID.randomUUID().toString();

    private String menuIdentifier;

    private String managerIdentifier;

    private String date;

    private String name;

    private String socialsSecurity;

    private String cellphone;

    @OneToMany(targetEntity=ClientOrder.class)
    private List<ClientOrder> clientOrder;

    @OneToOne
    private Transaction transaction;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID menuUuid;

}
