package br.com.delivery.pidao.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@RequiredArgsConstructor
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID uuid;

    private String publicPlace;
    private String number;
    private String zipCode;
    private String neighborhood;
    private String state;
    private String city;
    private String details;

    @Column(name = "user_id")
    private UUID userUuid;

    @Column(name = "restaraunt_id")
    private UUID restarauntUuid;
}
