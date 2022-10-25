package br.com.delivery.pidao.entities.dto;

import br.com.delivery.pidao.entities.Adress;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdressDTO {

    private String publicPlace;
    private String number;
    private String zipCode;
    private String neighborhood;
    private String state;
    private String city;
    private String details;

    private String clientIdentifier;

    private String restarauntIdentifier;

    public Adress dtoToEntity(){
       Adress adress = new Adress();
       adress.setPublicPlace(publicPlace);
       adress.setNumber(number);
       adress.setZipCode(zipCode);
       adress.setNeighborhood(neighborhood);
       adress.setState(state);
       adress.setCity(city);
       adress.setDetails(details);
       adress.setClientIdentifier(clientIdentifier);
       adress.setRestarauntIdentifier(restarauntIdentifier);

       return adress;
    }
}
