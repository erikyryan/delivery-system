package br.com.delivery.pidao.services;


import br.com.delivery.pidao.dao.UserDAO;
import br.com.delivery.pidao.entities.*;
import br.com.delivery.pidao.entities.dto.*;
import br.com.delivery.pidao.exceptions.ItemNotFound;
import br.com.delivery.pidao.exceptions.RestaurantNotFound;
import br.com.delivery.pidao.repositories.ItemRepository;
import br.com.delivery.pidao.repositories.RestaurantRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.AccessException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemService {

    private UserService userService;

    private MenuService menuService;

    private CategoryService categoryService;

    private ItemRepository itemRepository;

    private RestaurantRepository restaurantRepository;

    public ItemDTO addItem(final ItemDTO itemDTO){
        Optional<Item> item = itemRepository.findByNameAndDescriptionAndAndCategoryIdentifier(itemDTO.getName(), itemDTO.getDescription(), itemDTO.getCategoryIdentifier());
        if(item.isPresent()){
            throw new IllegalArgumentException("Item já existente");
        }else{
            Item newItem = itemDTO.dtoToEntity();
            itemRepository.save(newItem);
            return itemDTO;
        }
    }

    public ItemDTO updateItem(final ItemDTO itemDTO, String itemIdentifier){
        Optional<Item> item = itemRepository.findByItemIdentifier(itemIdentifier);
        if(item.isPresent()){
            Item newItem = item.get();

            if(!Objects.equals(itemDTO.getName(),null))
                newItem.setName(itemDTO.getName());

            if(!Objects.equals(itemDTO.getValue(),null))
                newItem.setValue(itemDTO.getValue());

            if(!Objects.equals(itemDTO.getDescription(),null))
                newItem.setDescription(itemDTO.getDescription());

            itemRepository.save(newItem);
            return itemDTO;
        }else{
            throw new ItemNotFound("Item não existente");
        }
    }


    public Boolean deleteItem(String itemIdentifier){
        Optional<Item> item = itemRepository.findByItemIdentifier(itemIdentifier);

        if(item.isPresent()){
            itemRepository.delete(item.get());
            return true;
        }else{
            throw new RuntimeException("Item não existente");
        }
    }

    public Restaurant getRestaurantIfTheUserIsAManagerFromUserDTO(String userIdentifier) throws AccessException {
        Manager manager = userService.isManager(userIdentifier);

        if (!Objects.equals(manager,null)) {
            Optional<Restaurant> managerRestaurant = restaurantRepository.findByRestaurantIdentifier(manager.getRestaurantIdentifier());
            if (managerRestaurant.isPresent()) {
                return managerRestaurant.get();
            }
            throw new RestaurantNotFound("Restaurante não encontrado");
        }
        throw new AccessException("Acesso não autorizado");
    }

    public List<ItemDTO> getItensFromMenuIdentifier(String menuIdentifier) {
        menuService.getRestaurantFromIdentifier(menuIdentifier);

        List<Category> categories = categoryService.findByMenuIdentifier(menuIdentifier);

        List<ItemDTO> itemsDTO = new ArrayList<>();
        List<List<Item>> itemsListsFromCategories = categories.stream().map(category ->  findByCategoryIdentifier(category.getCategoryIdentifier()) ).collect(Collectors.toList());
        for(List<Item> itemList : itemsListsFromCategories){
            List<ItemDTO> items = itemList.stream().map(item -> item.entityToDTO()).collect(Collectors.toList());
            itemsDTO.addAll(items);
        }

         return itemsDTO;
    }

    private List<Item> findByCategoryIdentifier(String categoryIdentifier){
        List<Item> items = itemRepository.findByCategoryIdentifier(categoryIdentifier);
        if(!items.isEmpty()){
            return items;

        }

        throw new ItemNotFound("Itens não encontrados");
    }

    public List<ItemDTO> getItensFromCategoryIdentifier(String categoryIdentifier) {
        List<ItemDTO> itemsDTO = findByCategoryIdentifier(categoryIdentifier).stream().map(item -> item.entityToDTO()).collect(Collectors.toList());
        return itemsDTO;
    }

    public  Item getItemByIdentifier(String itemIdentifier){
        Optional<Item> item = itemRepository.findByItemIdentifier(itemIdentifier);
        if(item.isPresent()){
            return item.get();
        }
        throw new ItemNotFound("Item não encontrado");
    }

}
