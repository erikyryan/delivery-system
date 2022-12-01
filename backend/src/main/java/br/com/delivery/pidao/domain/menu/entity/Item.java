package br.com.delivery.pidao.domain.menu.entity;


import br.com.delivery.pidao.domain.menu.entity.dto.ItemDTO;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID uuid;

    @NotNull
    private String name;

    @NotNull
    private Double value;

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
