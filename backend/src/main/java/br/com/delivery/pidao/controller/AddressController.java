package br.com.delivery.pidao.controller;

import br.com.delivery.pidao.domain.address.dto.AddressDTO;
import br.com.delivery.pidao.domain.address.AddressService;
import br.com.delivery.pidao.domain.login.SessionService;
import br.com.delivery.pidao.domain.user.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

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
            Users.UsersDTO userDTO = sessionService.findUsersDTOByToken(token);
            addressService.findByIdentifier(token);
            return ResponseEntity.ok(addressService);
        }catch (Exception e ){
            return ResponseEntity.badRequest().body(e.getMessage());
        }   
    }
}
