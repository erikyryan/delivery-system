package br.com.delivery.pidao.services;


import br.com.delivery.pidao.dao.CategoryDAO;
import br.com.delivery.pidao.dao.ItemDAO;
import br.com.delivery.pidao.dao.UserDAO;
import br.com.delivery.pidao.entities.Category;
import br.com.delivery.pidao.entities.Restaurant;
import br.com.delivery.pidao.entities.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Service
@RequestMapping("item")
public class ItemService {

    private UserDAO userDAO;

    private CategoryDAO categoryDAO;

    private ItemDAO itemDAO;

    @PostMapping
    public ItemDTO addItem(final UserDTO userDTO, final ItemDTO itemDTO){
        Restaurant restaurant = userDAO.isManager(userDTO);
        if(!Objects.equals(restaurant,null)){
            Category category = categoryDAO.isPresent(itemDTO.getCategoryDetails());
            if(Objects.equals(category,null)){
               throw new RuntimeException("Categoria não existente!");
            }

            if(itemDAO.isPresent(itemDTO)){
                throw new RuntimeException("Item já existente!");
            }

        }

        return null;
    }

}
