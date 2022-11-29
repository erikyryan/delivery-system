package br.com.delivery.pidao.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class AddressCustomerDTO {

    private String publicPlace;
    private String number;
    private String zipCode;
    private String neighborhood;
    private String state;
    private String city;
    private String details;

    public AddressCustomerDTO(){}

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

}