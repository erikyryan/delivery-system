package br.com.delivery.pidao.dao;

import br.com.delivery.pidao.entities.Category;
import br.com.delivery.pidao.entities.Item;
import br.com.delivery.pidao.entities.dto.ItemDTO;
import br.com.delivery.pidao.entities.dto.ItemDescriptionDTO;
import br.com.delivery.pidao.repositories.ItemRepository;

import java.util.Optional;

public class ItemDAO {

    private ItemRepository itemRepository;

    public Optional<Item> getItemFromItemDTO(ItemDTO itemDTO){
        return itemRepository.findByNameAndDescriptionAndCategory(itemDTO.getName(), itemDTO.getDescription(), itemDTO.getCategoryDetails());
    }
    public Optional<Item> getItemFromItemDescriptionDTOAndCategory(ItemDescriptionDTO itemDescriptionDTO, Category category){
        return itemRepository.findByNameAndDescriptionAndCategory(itemDescriptionDTO.getName(), itemDescriptionDTO.getDescription(), category.getDetails());
    }
}
