package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Adress {

    public Adress(String publicPlace, String number, String zipCode, String neighborhood, String state, String city, String details) {
        PublicPlace = publicPlace;
        Number = number;
        ZipCode = zipCode;
        Neighborhood = neighborhood;
        State = state;
        City = city;
        Details = details;
    }

    @Id
    private long Id;

    private String PublicPlace;
    private String Number;
    private String ZipCode;
    private String Neighborhood;
    private String State;
    private String City;
    private String Details;


    @OneToOne
    private Client ClientAdress;
    @OneToOne
    private Restaurant RestaurantAdress;


}
