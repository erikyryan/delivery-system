package br.com.delivery.pidao.controllers;

import br.com.delivery.pidao.entities.Menu;
import br.com.delivery.pidao.entities.dto.MenuDTO;
import br.com.delivery.pidao.entities.dto.UserDTO;
import br.com.delivery.pidao.services.MenuService;
import br.com.delivery.pidao.services.SessionService;
import br.com.delivery.pidao.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    private MenuService menuService;

    private SessionService sessionService;

    private UserService userService;

    @PostMapping
    public ResponseEntity<?> insertMenu(@RequestHeader String token, @RequestHeader UserDTO userDTO, @RequestBody MenuDTO menuDTO) {
        try {
            sessionService.validateToken(token);
            userService.isManager(userDTO);
            return ResponseEntity.ok(menuService.addMenu(menuDTO).getMenuIdentifier());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{menuIdentifier}")
    public ResponseEntity<?> findByIdentifier(@RequestHeader String token,@RequestHeader UserDTO userDTO, @PathVariable String menuIdentifier){
        try{
            sessionService.validateToken(token);
            userService.isManager(userDTO);
            return ResponseEntity.ok(menuService.findMenuByIdentifier(menuIdentifier));

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{menuIdentifier}")
    public ResponseEntity<?> deleteMenu(@RequestHeader String token, @RequestHeader UserDTO userDTO, @PathVariable String menuIdentifier) {
        try {
            sessionService.validateToken(token);
            userService.isManager(userDTO);
            return ResponseEntity.ok(menuService.deleteMenu(menuIdentifier));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
