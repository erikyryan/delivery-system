package br.com.delivery.pidao.entities;


import br.com.delivery.pidao.entities.dto.ItemDTO;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    private String itemIdentifier = UUID.randomUUID().toString();

    @NotNull
    private String name;

    @NotNull
    private Double value;

    @Nullable
    private String description;

    @ManyToOne
    private Category category;

    public ItemDTO entityToDTO(){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setValue(value);
        itemDTO.setDescription(description);
        itemDTO.setName(name);
        itemDTO.setCategoryUuid(category.getUuid().toString());
        return itemDTO;
    }

}
