package br.com.delivery.pidao.controller;

import br.com.delivery.pidao.domain.user.UserService;
import br.com.delivery.pidao.domain.user.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/User")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/addCustomer")
    public ResponseEntity<?> insertCustomer(@RequestBody Users.UsersDTO userDTO){
        try{
            return ResponseEntity.ok(userService.createUsersCustomer(userDTO));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/addManager")
    public ResponseEntity<?> insertManager(@RequestBody Users.UsersDTO userDTO){
        try{
            return ResponseEntity.ok(userService.createUsersManager(userDTO));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/loginUser")
    public ResponseEntity<?> login(@RequestBody Users.UsersDTO userDTO) {
    	String token = this.userService.login(userDTO);
    	return ResponseEntity.ok(token);
    }
   
   
    @PostMapping("/logoutUser")
    public ResponseEntity<?> logout(@RequestHeader("token") final String token){
    	this.userService.logout(token);
    	return ResponseEntity.ok("");
    }
}

