package br.com.delivery.pidao.entities.dto;

import br.com.delivery.pidao.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private String publicPlace;
    private String number;
    private String zipCode;
    private String neighborhood;
    private String state;
    private String city;
    private String details;

    private String clientIdentifier;

    private String restarauntIdentifier;

    public Address dtoToEntity(){
       Address address = new Address();
       address.setPublicPlace(publicPlace);
       address.setNumber(number);
       address.setZipCode(zipCode);
       address.setNeighborhood(neighborhood);
       address.setState(state);
       address.setCity(city);
       address.setDetails(details);
       address.setClientIdentifier(clientIdentifier);
       address.setRestarauntIdentifier(restarauntIdentifier);

       return address;
    }


}
