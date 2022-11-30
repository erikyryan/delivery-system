package br.com.delivery.pidao.services;


import br.com.delivery.pidao.entities.*;
import br.com.delivery.pidao.entities.dto.*;
import br.com.delivery.pidao.enums.UserTypeEnum;
import br.com.delivery.pidao.exceptions.ItemNotFound;
import br.com.delivery.pidao.exceptions.RestaurantNotFound;
import br.com.delivery.pidao.repositories.ItemRepository;
import br.com.delivery.pidao.repositories.RestaurantRepository;
import br.com.delivery.pidao.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemService {

    private UsersRepository usersRepository;

    private RestaurantService restaurantService;

    private CategoryService categoryService;

    private ItemRepository itemRepository;

    private RestaurantRepository restaurantRepository;

    public ItemDTO addItem(final ItemDTO itemDTO){
        UUID categoryUuid = UUID.fromString(itemDTO.getCategoryUuid());
        Optional<Item> item = itemRepository.findByNameAndDescriptionAndCategoryUuid(itemDTO.getName(), itemDTO.getDescription(), categoryUuid);
        if(item.isPresent()){
            throw new IllegalArgumentException("Item já existente");
        }else{
            Item newItem = itemDTO.dtoToEntity();
            itemRepository.save(newItem);
            return itemDTO;
        }
    }

    public ItemDTO updateItem(final ItemDTO itemDTO, String itemIdentifier){
        UUID uuid = UUID.fromString(itemIdentifier);
        Optional<Item> item = itemRepository.findByUuid(uuid);
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
        UUID uuid = UUID.fromString(itemIdentifier);
        Optional<Item> item = itemRepository.findByUuid(uuid);

        if(item.isPresent()){
            itemRepository.delete(item.get());
            return true;
        }else{
            throw new RuntimeException("Item não existente");
        }
    }

    public Restaurant getRestaurantIfTheUserIsAManagerFromUserDTO(UsersDTO userDTO) throws IOException {
        Optional<Users> usermanager = usersRepository.findByEmailAndType(userDTO.getEmail(), UserTypeEnum.MANAGER);

        if (usermanager.isPresent()) {
            Optional<Restaurant> restaurant = Optional.of(usermanager.get().getRestaurant());
            if (restaurant.isPresent()) {
                return restaurant.get();
            }
            throw new RestaurantNotFound("Restaurante não encontrado");
        }
        throw new IOException("Acesso não autorizado");
    }

    public List<ItemDTO> getItensFromMenuIdentifier(String menuIdentifier) {
        restaurantService.findMenuByIdentifier(menuIdentifier);

        List<Category> categories = categoryService.findByMenuIdentifier(menuIdentifier);

        List<ItemDTO> itemsDTO = new ArrayList<>();
        List<List<Item>> itemsListsFromCategories = categories.stream().map(category ->  findByCategoryIdentifier(category.getUuid().toString()) ).collect(Collectors.toList());
        for(List<Item> itemList : itemsListsFromCategories){
            List<ItemDTO> items = itemList.stream().map(item -> item.entityToDTO()).collect(Collectors.toList());
            itemsDTO.addAll(items);
        }

         return itemsDTO;
    }

    private List<Item> findByCategoryIdentifier(String categoryIdentifier){
        UUID categoryUuid = UUID.fromString(categoryIdentifier);
        List<Item> items = itemRepository.findByCategoryUuid(categoryUuid);
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
        UUID uuid = UUID.fromString(itemIdentifier);
        Optional<Item> item = itemRepository.findByUuid(uuid);
        if(item.isPresent()){
            return item.get();
        }
        throw new ItemNotFound("Item não encontrado");
    }

}
