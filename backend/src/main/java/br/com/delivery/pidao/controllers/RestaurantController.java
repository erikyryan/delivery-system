package br.com.delivery.pidao.controllers;

import br.com.delivery.pidao.entities.dto.UsersDTO;
import br.com.delivery.pidao.services.RestaurantService;
import br.com.delivery.pidao.services.SessionService;
import br.com.delivery.pidao.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/restaurant")
public class RestaurantController {

    private SessionService sessionService;

    private UserService userService;

    private RestaurantService restaurantService;

    @GetMapping("menu/{menuIdentifier}")
    public ResponseEntity<?> findByIdentifier(@RequestHeader String token,@RequestHeader UsersDTO userDTO, @PathVariable String menuIdentifier){
        try{
            sessionService.validateToken(token);
            userService.isManager(userDTO);
            return ResponseEntity.ok(restaurantService.findByRestaurantIdentifier(menuIdentifier));

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
