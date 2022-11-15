package br.com.delivery.pidao.services;

import br.com.delivery.pidao.dao.UserDAO;
import br.com.delivery.pidao.entities.*;
import br.com.delivery.pidao.entities.dto.AddressDTO;
import br.com.delivery.pidao.entities.dto.ClientDTO;
import br.com.delivery.pidao.entities.dto.ManagerDTO;
import br.com.delivery.pidao.entities.dto.UserDTO;
import br.com.delivery.pidao.repositories.AddressRepository;
import br.com.delivery.pidao.repositories.ClientRepository;
import br.com.delivery.pidao.repositories.DeliveryRepository;
import br.com.delivery.pidao.repositories.ManagerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import validator.ValidatorEmail;
import validator.ValidatorTaxNumber;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {
    
    private final UserDAO userDAO;

    private ClientRepository clientRepository;

    private final ManagerRepository managerRepository;

    private final DeliveryRepository deliveryRepository;

    private SessionService sessionService;

    private AddressService addressService;

    private AddressRepository addressRepository;


    public boolean validateUserExistreate(UserDTO userDTO){
        Optional<Manager> manager = managerRepository.findByEmail(userDTO.getEmail());
        if(!manager.isEmpty()){
            throw new RuntimeException("Email já Existente");
        }
        
        Optional<Client> client = clientRepository.findByEmail(userDTO.getEmail());
        if(!client.isEmpty()){
            throw new  RuntimeException("Email já Existente");
        }

        Optional<Delivery> delivery = deliveryRepository.findByEmail(userDTO.getEmail());
        if(!delivery.isEmpty()){
            throw new  RuntimeException("Email já Existente");
        }
        
        return false;
    }

    public boolean validateEmailAndTaxNumber(String email, String taxNumber){

        boolean validatorEmail = new ValidatorEmail().emailIsValid(email);
        //boolean validatorPassword = new ValidatorPassword().passwordIsValid(password);
        boolean validatorTaxNumber = new ValidatorTaxNumber().taxNumberIsValid(taxNumber);

        if(validatorEmail == true){
            if(validatorTaxNumber == true){
                return true;
            }else{
                throw new  RuntimeException("CPF/CNPJ ínvalido");
            }
        }else{
            throw new  RuntimeException("Email ínvalido");
        } 
    }

    public ClientDTO createUserClient(ClientDTO clientDTO){
        this.validateUserExistreate(clientDTO.toUserDTO());
        this.validateEmailAndTaxNumber(clientDTO.getEmail(), clientDTO.getSocialsSecurity());

        Client newClient = clientDTO.dtoToEntity();
        Client client = clientRepository.save(newClient);
        AddressDTO addressDTO = clientDTO.getAddressDTO().dtoAndClientIdentifierToAddressDTO(client.getUserIdentifier());
        addressService.addAddress(addressDTO);


        return clientDTO;
    }

    public AddressDTO addAddress(AddressDTO addressDTO){
        Address newAdress = addressDTO.dtoToEntity();
        addressRepository.save(newAdress);

        return addressDTO;
    }

    public ManagerDTO createUserManager(ManagerDTO managerDTO){
        this.validateUserExistreate(managerDTO.toUserDTO());
        this.validateEmailAndTaxNumber(managerDTO.getEmail(), managerDTO.getSocialsSecurity());

        Manager newManager = managerDTO.dtoToEntity();
        Manager manager = managerRepository.save(newManager);
        AddressDTO addressDTO = managerDTO.getAddressDTO().dtoAndRestaurantIdentifierToAddressDTO(manager.getUserIdentifier());
        addressService.addAddress(addressDTO);

        return managerDTO;
    }

   public LoginSession logoutUser(String token) {
       try {
           LoginSession session = sessionService.logout(token);
           return session;
       } catch (Exception e) {
           throw new RuntimeException(e.getMessage());
       }
   }
}
