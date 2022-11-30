package br.com.delivery.pidao.domain.address.dto;

import br.com.delivery.pidao.domain.address.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

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
       address.setUserUuid(UUID.fromString(customerIdentifier));
       address.setRestarauntUuid(UUID.fromString(customerIdentifier));
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
