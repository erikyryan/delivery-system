package br.com.delivery.pidao.controllers;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microsoft.azure.documentdb.User;

import br.com.delivery.pidao.entities.dto.*;     
import br.com.delivery.pidao.services.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

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
            UsersDTO userDTO = sessionService.findUsersDTOByToken(token);
            addressService.findByIdentifier(token);
            return ResponseEntity.ok(addressService);
        }catch (Exception e ){
            return ResponseEntity.badRequest().body(e.getMessage());
        }   
    }
}
