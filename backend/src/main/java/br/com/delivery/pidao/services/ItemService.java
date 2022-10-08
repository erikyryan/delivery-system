package br.com.delivery.pidao.services;


import br.com.delivery.pidao.dao.CategoryDAO;
import br.com.delivery.pidao.dao.ItemDAO;
import br.com.delivery.pidao.dao.UserDAO;
import br.com.delivery.pidao.entities.Category;
import br.com.delivery.pidao.entities.Client;
import br.com.delivery.pidao.entities.Item;
import br.com.delivery.pidao.entities.Restaurant;
import br.com.delivery.pidao.entities.dto.*;
import br.com.delivery.pidao.repositories.ItemRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;
import java.util.Optional;

@Service
@NoArgsConstructor
public class ItemService {

    private CategoryDAO categoryDAO;

    private ItemDAO itemDAO;

    private ItemRepository itemRepository;

    private Category getCategoryFromRestaurant(Long idMenu, String details){
        Optional<Category> category = categoryDAO.isPresent(details, idMenu);
        if (category.isPresent()) {
            return category.get();
        }
        throw new RuntimeException("Categoria não existente!");
    }

    public ItemDTO addItem(final ItemDTO itemDTO, final Restaurant restaurant){
        Optional<Item> item = itemDAO.getItemFromItemDTO(itemDTO);
        if(item.isPresent()){
            throw new RuntimeException("Item já existente!");
        }else{
            Item newItem = itemDTO.dtoToEntity();
            Long idMenu = restaurant.getMenu().getId();
            Category category = getCategoryFromRestaurant(idMenu,itemDTO.getCategoryDetails());
            newItem.setCategory(category);
            itemRepository.save(newItem);
            return itemDTO;
        }
    }

    public ItemDTO updateItem(final ItemDTO itemDTO, final Restaurant restaurant){
        Optional<Item> item = itemDAO.getItemFromItemDTO(itemDTO);
        if(item.isPresent()){
            Item newItem = item.get();
            Long idMenu = restaurant.getMenu().getId();
            Category category = getCategoryFromRestaurant(idMenu,itemDTO.getCategoryDetails());
            newItem.setCategory(category);

            if(!Objects.equals(itemDTO.getName(),null))
                newItem.setName(itemDTO.getName());

            if(!Objects.equals(itemDTO.getValue(),null))
                newItem.setValue(itemDTO.getValue());

            if(!Objects.equals(itemDTO.getDescription(),null))
                newItem.setDescription(itemDTO.getDescription());

            itemRepository.save(newItem);
            return itemDTO;
        }else{
            throw new RuntimeException("Item não existente!");
        }
    }


    public Boolean deleteItem(ItemDescriptionDTO itemDescriptionDTO,String details,Long idMenu){
        Category category = getCategoryFromRestaurant(idMenu,details);
        Optional<Item> item = itemDAO.getItemFromItemDescriptionDTOAndCategory(itemDescriptionDTO,category);

        if(item.isPresent()){
            itemRepository.delete(item.get());
            return true;
        }else{
            throw new RuntimeException("Item não existente!");
        }
    }

//
//    public Long addRating(Long rating, Client client) {
//
//    }
}
