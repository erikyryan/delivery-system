package br.com.delivery.pidao.controllers;

import br.com.delivery.pidao.dao.UserDAO;
import br.com.delivery.pidao.entities.Manager;
import br.com.delivery.pidao.entities.Restaurant;
import br.com.delivery.pidao.entities.dto.ItemDTO;
import br.com.delivery.pidao.entities.dto.ItemDescriptionDTO;
import br.com.delivery.pidao.entities.dto.UserDTO;
import br.com.delivery.pidao.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private ItemService itemService;

    private UserDAO userDAO;

    @PostMapping
    public String insertItem(@RequestBody ItemDTO itemDTO, @RequestHeader UserDTO userDTO){
        Optional<Manager> manager = userDAO.isManager(userDTO);
        if(manager.isPresent()) {
            Restaurant managerRestaurant = manager.get().getRestaurantManager();
            if(manager.isPresent()) {
                itemService.addItem(itemDTO, managerRestaurant);
                return "Item atualizado com sucesso!";
            }
            return "Restaurante inválido!";
        }
        return "Acesso não autorizado";
    }

    @PutMapping
    public String updateItem(@RequestBody ItemDTO itemDTO, @RequestHeader UserDTO userDTO){

        Optional<Manager> manager = userDAO.isManager(userDTO);
        if(manager.isPresent()) {
            Restaurant managerRestaurant = manager.get().getRestaurantManager();
            if(manager.isPresent()) {
                itemService.updateItem(itemDTO, managerRestaurant);
                return "Item atualizado com sucesso!";
            }
            return "Restaurante inválido!";
        }
        return "Acesso não autorizado!";
    }

    @DeleteMapping("/{idMenu}")
    public String deleteItem(@PathVariable Long idMenu,@RequestHeader UserDTO userDTO, @RequestBody ItemDescriptionDTO itemDescriptionDTO){
        Optional<Manager> manager = userDAO.isManager(userDTO);
        if(manager.isPresent()) {
            Restaurant managerRestaurant = manager.get().getRestaurantManager();
            if(manager.isPresent()) {
                itemService.deleteItem(itemDescriptionDTO, itemDescriptionDTO.getDescription(),idMenu);
                return "Deletado com sucesso!";
            }
            return "Restaurante inválido!";
        }
        return "Acesso não autorizado!";
    }



//    @PostMapping("/rating")
//    public Long insertRating(@RequestBody Long rating, @RequestHeader String email, @RequestHeader String senha){
//        UserDTO userDTO = new UserDTO(email,senha);
//        Optional<Client> client = userDAO.isClient(userDTO);
//        if(client.isPresent()) {
//            return itemService.addRating(rating, client.get());
//        }
//        return null;
//    }

}
