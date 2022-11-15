package br.com.delivery.pidao.controllers;

import br.com.delivery.pidao.services.MenuService;
import br.com.delivery.pidao.services.SessionService;
import br.com.delivery.pidao.services.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    private MenuService menuService;

    private SessionService sessionService;

    private UserService userService;

    @PostMapping("/{restaurantIdentifier}")
    public ResponseEntity<?> insertMenu(@RequestHeader String token, @PathVariable String restaurantIdentifier) {
        try {
            String userIdentifier = sessionService.validateToken(token);
            userService.isManager(userIdentifier);
            return ResponseEntity.ok(menuService.addMenu(restaurantIdentifier).getMenuIdentifier());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{menuIdentifier}")
    public ResponseEntity<?> findByIdentifier(@RequestHeader String token, @PathVariable String menuIdentifier){
        try{
            String userIdentifier = sessionService.validateToken(token);
            userService.isManager(userIdentifier);
            return ResponseEntity.ok(menuService.findMenuByIdentifier(menuIdentifier));

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{menuIdentifier}")
    public ResponseEntity<?> deleteMenu(@RequestHeader String token, @PathVariable String menuIdentifier) {
        try {
            String userIdentifier = sessionService.validateToken(token);
            userService.isManager(userIdentifier);
            return ResponseEntity.ok(menuService.deleteMenu(menuIdentifier));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
