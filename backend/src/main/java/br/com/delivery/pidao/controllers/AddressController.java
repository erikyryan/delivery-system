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

    AdressService adressService;

    SessionService sessionService;

    @PostMapping("/addAddress")
    public ResponseEntity<?> insertAdress(@RequestBody AddressDTO adressDTO){
        try{
            return ResponseEntity.ok(adressService.addAdress(adressDTO));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{addresIdentifier}")
    public ResponseEntity<?> updateAddress(@RequestHeader final String token,@RequestBody AddressDTO addressDTO){
        try {
            sessionService.validateToken(token);
            UserDTO userDTO = sessionService.findUserDTOByToken(token);
            adressService.findByIdentifier(token);
            return ResponseEntity.ok(adressService);
        }catch (Exception e ){
            return ResponseEntity.badRequest().body(e.getMessage());
        }   
    }
}
