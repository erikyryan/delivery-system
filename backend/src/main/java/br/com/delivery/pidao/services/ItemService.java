package br.com.delivery.pidao.services;


import br.com.delivery.pidao.dao.UserDAO;
import br.com.delivery.pidao.entities.*;
import br.com.delivery.pidao.entities.dto.*;
import br.com.delivery.pidao.exceptions.ItemNotFound;
import br.com.delivery.pidao.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private MenuService menuService;

    private UserService userService;

    private ItemRepository itemRepository;

    private CategoryService categoryService;

    public ItemDTO addItem(final ItemDTO itemDTO){
        Optional<Item> item = itemRepository.findByNameAndDescriptionAndCategory(itemDTO.getName(), itemDTO.getDescription(), itemDTO.getCategoryDetails());
        if(item.isPresent()){
            throw new RuntimeException("Item já existente!");
        }else{
            Item newItem = itemDTO.dtoToEntity();

            Category category = categoryService.getCategoryByIdentifier(itemDTO.getCategoryIdentifier());
            newItem.setCategory(category);
            itemRepository.save(newItem);
            return itemDTO;
        }
    }

    public ItemDTO updateItem(final ItemDTO itemDTO, String itemIdentifier){
        Optional<Item> item = itemRepository.findByItemIdentifier(itemIdentifier);
        if(item.isPresent()){
            Item newItem = item.get();
            Category category = categoryService.getCategoryByIdentifier(itemDTO.getCategoryIdentifier());
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
            throw new ItemNotFound("Item não existente!");
        }
    }


    public Boolean deleteItem(String itemIdentifier){
        Optional<Item> item = itemRepository.findByItemIdentifier(itemIdentifier);

        if(item.isPresent()){
            itemRepository.delete(item.get());
            return true;
        }else{
            throw new RuntimeException("Item não existente!");
        }
    }

    public Restaurant getRestaurantIfTheUserIsAManagerFromUserDTO(UserDTO userDTO) {
        Optional<Manager> manager = userService.isManager(userDTO);

        if (manager.isPresent()) {
            Restaurant managerRestaurant = manager.get().getRestaurantManager();
            if (!Objects.equals(managerRestaurant, null)) {
                return managerRestaurant;
            }
            throw new RuntimeException("Restaurante inválido!");
        }
        throw new RuntimeException("Acesso não autorizado");
    }

    public List<ItemDTO> getItensFromMenuIdentifier(String menuIdentifier) {
        Menu menu = menuService.getMenu(menuIdentifier);
        Restaurant restaurant = menuService.getRestaurantFromIdentifier(menuIdentifier);
        List<Category> categories = menu.getCategoryMenu().stream().map( category ->
                categoryService.getCategoryByIdentifier(category.getCategoryIdentifier()))
                .collect(Collectors.toList());

         List<List<Item>> itemsListsFromCategories = categories.stream().map( category -> { return category.getItems(); }).collect(Collectors.toList());

         List<ItemDTO> itemsDTO = new ArrayList<>();

         for(List<Item> itemList : itemsListsFromCategories){
             List<ItemDTO> items = itemList.stream().map(item -> {
                 return item.entityToDTO();
             }).collect(Collectors.toList());
             itemsDTO.addAll(items);
         }

         return itemsDTO;
    }

    public List<ItemDTO> getItensFromCategoryIdentifier(String categoryIdentifier) {
        Category category = categoryService.getCategoryByIdentifier(categoryIdentifier);

        List<ItemDTO> itemsDTO = category.getItems().stream().map(item -> {
                return item.entityToDTO();
            }
        ).collect(Collectors.toList());

        return itemsDTO;
    }

    public  Item getItemByIdentifier(String itemIdentifier){
        Optional<Item> item = itemRepository.findByItemIdentifier(itemIdentifier);
        if(item.isPresent()){
            return item.get();
        }
        throw new RuntimeException("Item inválido");
    }

}
