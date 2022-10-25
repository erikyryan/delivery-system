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
@RequestMapping("/Client")
@AllArgsConstructor
public class ClientController {

    ClientService clientService;

    @PostMapping("/addClient")
    public ResponseEntity<?> insertClient(@RequestBody ClientDTO clientDTO, @RequestBody UserDTO userDTO){
        try{
            return ResponseEntity.ok(clientService.addUserClient(userDTO,clientDTO));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/addManager")
    public ResponseEntity<?> insertManager(@RequestBody ManagerDTO managerDTO, @RequestBody UserDTO userDTO, @RequestBody AdressDTO adressDTO){
        try{
            return ResponseEntity.ok(clientService.addUserManager(userDTO, managerDTO,adressDTO));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("aaaaaaaaaaaaaaaaaaaaaaa");
        }
    }
    
    public ResponseEntity<?> insertAdress(@RequestBody AdressDTO adressDTO){
        try{
            return ResponseEntity.ok(clientService.addAdress(adressDTO));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("aaaaaaaaaaaaaaaaaaaaaaa");
        }
    }

    @PostMapping(value = "/loginUser")
    public ResponseEntity<?> login(@RequestBody final UserDTO userDTO, @RequestBody User user) {
    	String token = this.clientService.loginUser(userDTO, null);
    	return ResponseEntity.ok(token);
    }
    
    @PostMapping(value = "/logoutUser")
    public ResponseEntity<?> logout(@RequestHeader("token") final String token){
    	this.clientService.logoutUser(token);	
    	return ResponseEntity.ok("");
    }
  

   
        
}

