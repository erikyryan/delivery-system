package br.com.delivery.pidao.controller;

import br.com.delivery.pidao.domain.user.Users;
import br.com.delivery.pidao.domain.restaurant.RestaurantService;
import br.com.delivery.pidao.domain.login.SessionService;
import br.com.delivery.pidao.domain.user.UserService;
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
    public ResponseEntity<?> findByIdentifier(@RequestHeader String token, @RequestHeader Users.UsersDTO userDTO, @PathVariable String menuIdentifier){
        try{
            sessionService.validateToken(token);
            userService.isManager(userDTO);
            return ResponseEntity.ok(restaurantService.findByRestaurantIdentifier(menuIdentifier));

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
