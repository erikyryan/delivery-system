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

    private ClientService clientService;

    private  AdressService adressService;

    private UserService userService;

    @PostMapping("/addClient")
    public ResponseEntity<?> insertClient(@RequestBody ClientDTO clientDTO){
        try{
            return ResponseEntity.ok(clientService.createUserClient(clientDTO));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/addManager")
    public ResponseEntity<?> insertManager(@RequestBody ManagerDTO managerDTO){
        try{
            return ResponseEntity.ok(clientService.createUserManager(managerDTO));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/loginUser")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
    	String token = this.userService.login(userDTO);
    	return ResponseEntity.ok(token);
    }
   
   
    @PostMapping("/logoutUser")
    public ResponseEntity<?> logout(@RequestHeader("token") final String token){
    	this.clientService.logoutUser(token);	
    	return ResponseEntity.ok("");
    }
}

