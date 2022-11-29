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

    private String customerIdentifier;

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
       address.setClientIdentifier(customerIdentifier);
       address.setRestarauntIdentifier(restarauntIdentifier);

       return address;
    }

    public AddressDTO dtoAndCustomerIdentifierToAdressDTO(String customerIdentifier){
        AddressDTO address = new AddressDTO();
        address.setPublicPlace(publicPlace);
        address.setNumber(number);
        address.setZipCode(zipCode);
        address.setNeighborhood(neighborhood);
        address.setState(state);
        address.setCity(city);
        address.setDetails(details);
        address.setCustomerIdentifier(customerIdentifier);

        return address;
    }

    public AddressDTO dtoAndRestaurantIdentifierToAdressDTO(String restaurantIdentifier){
        AddressDTO address = new AddressDTO();
        address.setPublicPlace(publicPlace);
        address.setNumber(number);
        address.setZipCode(zipCode);
        address.setNeighborhood(neighborhood);
        address.setState(state);
        address.setCity(city);
        address.setDetails(details);
        address.setRestarauntIdentifier(restaurantIdentifier);

        return address;
    }


}
