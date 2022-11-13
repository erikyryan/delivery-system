package br.com.delivery.pidao.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class AddressClientDTO {

    private String publicPlace;
    private String number;
    private String zipCode;
    private String neighborhood;
    private String state;
    private String city;
    private String details;

    public AddressClientDTO(){}

    public AddressDTO dtoAndClientIdentifierToAdressDTO(String clientIdentifier){
        AddressDTO address = new AddressDTO();
        address.setPublicPlace(publicPlace);
        address.setNumber(number);
        address.setZipCode(zipCode);
        address.setNeighborhood(neighborhood);
        address.setState(state);
        address.setCity(city);
        address.setDetails(details);
        address.setClientIdentifier(clientIdentifier);

        return address;
    }

}
