package br.com.delivery.pidao.dao;

import br.com.delivery.pidao.entities.Item;
import br.com.delivery.pidao.entities.dto.ItemDTO;
import br.com.delivery.pidao.repositories.ItemRepository;

import java.util.Optional;

public class ItemDAO {

    private ItemRepository itemRepository;

    public Boolean isPresent(ItemDTO itemDTO){
        Optional<Item> item = itemRepository.findByNameAndDescriptionAndCategory(itemDTO.getName(), itemDTO.getDescription(), itemDTO.getCategoryDetails());
        return item.isPresent();
    }
}
