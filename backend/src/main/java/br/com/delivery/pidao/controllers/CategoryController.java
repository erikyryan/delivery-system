package br.com.delivery.pidao.controllers;

import br.com.delivery.pidao.entities.dto.CategoryDTO;
import br.com.delivery.pidao.entities.dto.UserDTO;
import br.com.delivery.pidao.services.CategoryService;
import br.com.delivery.pidao.services.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    private SessionService sessionService;

    @PostMapping
    public ResponseEntity<?> insertCategory(@RequestHeader final String token, @RequestBody final CategoryDTO categoryDTO){
        try {
            sessionService.validateToken(token);
            UserDTO userDTO = sessionService.findUserDTOByToken(token);
            return ResponseEntity.ok(categoryService.addCategory(categoryDTO, userDTO));
        }catch (Exception e ){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
