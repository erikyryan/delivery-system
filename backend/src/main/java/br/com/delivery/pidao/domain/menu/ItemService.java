package br.com.delivery.pidao.domain.menu;


import br.com.delivery.pidao.domain.menu.entity.Category;
import br.com.delivery.pidao.domain.menu.entity.Item;
import br.com.delivery.pidao.domain.menu.entity.dto.ItemDTO;
import br.com.delivery.pidao.domain.restaurant.RestaurantService;
import br.com.delivery.pidao.domain.user.UserService;
import br.com.delivery.pidao.exception.ItemNotFound;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemService {

    private CategoryService categoryService;

    private ItemRepository itemRepository;

    private RestaurantService restaurantService;

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
        UUID uuid = UUID.fromString(categoryIdentifier);
        List<Item> items = itemRepository.findByCategoryUuid(uuid);
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
