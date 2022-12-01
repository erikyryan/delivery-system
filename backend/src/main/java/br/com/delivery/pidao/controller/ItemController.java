package br.com.delivery.pidao.controller;

import br.com.delivery.pidao.domain.user.Users;
import br.com.delivery.pidao.domain.menu.entity.dto.ItemDTO;
import br.com.delivery.pidao.domain.menu.entity.dto.ItemDescriptionDTO;
import br.com.delivery.pidao.domain.menu.ItemService;
import br.com.delivery.pidao.domain.login.SessionService;
import br.com.delivery.pidao.domain.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private ItemService itemService;

    private SessionService sessionService;

    private UserService userService;

    @PostMapping
    public ResponseEntity<?> insertItem(@RequestBody ItemDTO itemDTO,@RequestHeader("token") final String token){
        try{
            sessionService.validateToken(token);
            Users.UsersDTO userDTO = sessionService.findUsersDTOByToken(token);
            userService.isManager(userDTO);
            return ResponseEntity.ok(itemService.addItem(itemDTO));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/{itemIdentifier}")
    public ResponseEntity<?> updateItem(@PathVariable String itemIdentifier, @RequestHeader("token") String token, @RequestBody ItemDTO itemDTO){
        try{
            sessionService.validateToken(token);
            Users.UsersDTO userDTO = sessionService.findUsersDTOByToken(token);
            userService.isManager(userDTO);
            return ResponseEntity.ok(itemService.updateItem(itemDTO,itemIdentifier));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{menuIdentifier}")
    public ResponseEntity<?> deleteItem(@PathVariable String itemIdentifier,@RequestHeader("token") final String token, @RequestBody ItemDescriptionDTO itemDescriptionDTO){
        try{
            sessionService.validateToken(token);
            Users.UsersDTO userDTO = sessionService.findUsersDTOByToken(token);
            userService.isManager(userDTO);
            itemService.deleteItem(itemIdentifier);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/public/{menuIdentifier}")
    public ResponseEntity<?> findAllByMenuIdentifier(@PathVariable String menuIdentifier,@RequestHeader("token") final String token){
        try{
            sessionService.validateToken(token);
            Users.UsersDTO userDTO = sessionService.findUsersDTOByToken(token);
            userService.isManager(userDTO);
            return ResponseEntity.ok(itemService.getItensFromMenuIdentifier(menuIdentifier));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/public/{categoryIdentifier}")
    public ResponseEntity<?> findAllByCategoryIdentifier(@PathVariable String categoryIdentifier,@RequestHeader("token") final String token){
        try{
            sessionService.validateToken(token);
            Users.UsersDTO userDTO = sessionService.findUsersDTOByToken(token);
            userService.isManager(userDTO);
            return ResponseEntity.ok(itemService.getItensFromCategoryIdentifier(categoryIdentifier));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("{itemIdentifier}")
    public ResponseEntity<?> findItemByIdentifier(@PathVariable String itemIdentifier, @RequestHeader("token") String token){
        try{
            sessionService.validateToken(token);
            return ResponseEntity.ok(itemService.getItemByIdentifier(itemIdentifier));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
