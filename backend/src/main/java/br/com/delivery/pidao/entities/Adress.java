package br.com.delivery.pidao.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@RequiredArgsConstructor
public class Adress implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String adressIdentifier = UUID.randomUUID().toString();

    private String publicPlace;
    private String number;
    private String zipCode;
    private String neighborhood;
    private String state;
    private String city;
    private String details;

    private String clientIdentifier;

    private String restarauntIdentifier;
}
