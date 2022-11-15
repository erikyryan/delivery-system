package br.com.delivery.pidao.controllers;

import br.com.delivery.pidao.entities.dto.CategoryDTO;
import br.com.delivery.pidao.entities.dto.UserDTO;
import br.com.delivery.pidao.services.CategoryService;
import br.com.delivery.pidao.services.ItemService;
import br.com.delivery.pidao.services.SessionService;
import br.com.delivery.pidao.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    private SessionService sessionService;

    private ItemService itemService;

    private UserService userService;

    @PostMapping
    public ResponseEntity<?> insertCategory(@RequestHeader final String token, @RequestBody final CategoryDTO categoryDTO){
        try {
            String userIdentifier = sessionService.validateToken(token);
            userService.isManager(userIdentifier);
            return ResponseEntity.ok(categoryService.addCategory(categoryDTO));
        }catch (Exception e ){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{categoryIdentifier}")
    public ResponseEntity<?> findCategoryByIdentifier(@RequestHeader final String token, @RequestHeader String categoryIdentifier){
        try {
            String userIdentifier = sessionService.validateToken(token);
            userService.isManager(userIdentifier);
            return ResponseEntity.ok(categoryService.findByIdentifier(categoryIdentifier));
        }catch (Exception e ){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateCategory(@RequestHeader final String token,@RequestBody CategoryDTO categoryDTO){
        try {
            String userIdentifier = sessionService.validateToken(token);
            userService.isManager(userIdentifier);
            return ResponseEntity.ok(categoryService.updateCategory(categoryDTO));
        }catch (Exception e ){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{categoryIdentifier}")
    public ResponseEntity<?> updateCategory(@RequestHeader final String token,@PathVariable String categoryIdentifier){
        try {
            String userIdentifier = sessionService.validateToken(token);
            userService.isManager(userIdentifier);
            categoryService.deleteCategory(categoryIdentifier);
            return ResponseEntity.ok().build();
        }catch (Exception e ){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
