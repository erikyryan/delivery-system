package br.com.delivery.pidao.controllers;

import br.com.delivery.pidao.entities.Item;
import br.com.delivery.pidao.entities.dto.ItemDTO;
import br.com.delivery.pidao.entities.dto.UserDTO;
import br.com.delivery.pidao.services.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/item")
@AllArgsConstructor
public class ItemController {

    private ItemService itemService;

    @PostMapping
    public ItemDTO insertItem(@RequestBody ItemDTO itemDTO, @RequestHeader String email, @RequestHeader String senha){
        UserDTO userDTO = new UserDTO(email,senha);
        ItemDTO itemSaved = itemService.addItem(userDTO,itemDTO);
        return itemSaved;
    }

}
