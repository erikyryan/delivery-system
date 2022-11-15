package br.com.delivery.pidao.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import br.com.delivery.pidao.entities.dto.*;
import br.com.delivery.pidao.services.*;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AddressController {

    AddressService addressService;

    SessionService sessionService;

    @PostMapping("/addAddress")
    public ResponseEntity<?> insertAddress(@RequestBody AddressDTO adressDTO){
        try{
            return ResponseEntity.ok(addressService.addAddress(adressDTO));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{addresIdentifier}")
    public ResponseEntity<?> updateAddress(@RequestHeader final String token,@RequestBody AddressDTO addressDTO){
        try {
            sessionService.validateToken(token);
            UserDTO userDTO = sessionService.findUserDTOByToken(token);
            addressService.findByIdentifier(token);
            return ResponseEntity.ok(addressService);
        }catch (Exception e ){
            return ResponseEntity.badRequest().body(e.getMessage());
        }   
    }
}
