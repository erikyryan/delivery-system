package br.com.delivery.pidao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.delivery.pidao.entities.dto.*;
import br.com.delivery.pidao.services.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/Client")
public class ClientController {

    ClientService clientService;

    @PostMapping("/addClient")
    public ResponseEntity<?> insertClient(@RequestBody ClientDTO clientDTO){
        try{
            return ResponseEntity.ok(clientService.addUser(clientDTO));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("aaaaaaaaaaaaaaaaaaaaaaa");
        }

    }
        
}

