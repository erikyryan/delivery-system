package br.com.delivery.pidao.domain.menu.entity.dto;


import br.com.delivery.pidao.domain.menu.entity.Item;
import lombok.Data;


@Data
public class ItemDTO {

    private String name;

    private Double value;

    private String description;

    private String categoryUuid;

    public Item dtoToEntity(){
        Item item = new Item();
        item.setName(name);
        item.setValue(value);
        item.setDescription(description);
        return item;
    }
}
