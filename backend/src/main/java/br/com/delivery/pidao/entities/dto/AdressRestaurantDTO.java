package br.com.delivery.pidao.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdressRestaurantDTO {
    
    private String publicPlace;
    private String number;
    private String zipCode;
    private String neighborhood;
    private String state;
    private String city;
    private String details;

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
