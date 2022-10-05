package br.com.delivery.pidao.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Adress {

    public Adress(String publicPlace, String number, String zipCode, String neighborhood, String state, String city, String details) {
        publicPlace = publicPlace;
        number = number;
        zipCode = zipCode;
        neighborhood = neighborhood;
        state = state;
        city = city;
        details = details;
    }

    @Id
    private long id;

    private String publicPlace;
    private String number;
    private String zipCode;
    private String neighborhood;
    private String state;
    private String city;
    private String details;


    @OneToOne
    private Client ClientAdress;
    @OneToOne
    private Restaurant RestaurantAdress;


}
