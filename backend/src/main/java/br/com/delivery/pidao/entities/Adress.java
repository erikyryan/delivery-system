package br.com.delivery.pidao.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@IdClass(Adress.class)
@RequiredArgsConstructor
public class Adress implements Serializable {

    @Id
    private long id;

    private String publicPlace;
    private String number;
    private String zipCode;
    private String neighborhood;
    private String state;
    private String city;
    private String details;


    private Long ClientAdress;

    private Long RestaurantAdress;


}
