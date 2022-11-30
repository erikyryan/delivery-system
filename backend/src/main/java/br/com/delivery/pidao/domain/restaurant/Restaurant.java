package br.com.delivery.pidao.domain.restaurant;

import br.com.delivery.pidao.domain.order.Transaction;
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

    @OneToOne
    private Transaction transaction;

    @Column(name = "address_id")
    private UUID addressUuid;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="menu_id")
    private UUID menuUuid;

}
