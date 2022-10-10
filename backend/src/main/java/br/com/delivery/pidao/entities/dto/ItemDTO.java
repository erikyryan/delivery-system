package br.com.delivery.pidao.entities.dto;


import br.com.delivery.pidao.entities.Item;
import lombok.Getter;


@Getter
public class ItemDTO {

    private String name;

    private Double value;

    private String description;

    private String categoryDetails;

    public Item dtoToEntity(){
        Item item = new Item();
        item.setName(name);
        item.setValue(value);
        item.setDescription(description);
        return item;
    }

}
