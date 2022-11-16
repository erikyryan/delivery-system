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
@RequiredArgsConstructor
@IdClass(Item.class)
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID uuid;

    @NotNull
    private String name;

//    @OneToMany
//    private List<Rating> ratings;

    @NotNull
    private Double value;

    @Nullable
    private String description;

    private String categoryIdentifier;

    public ItemDTO entityToDTO(){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setValue(value);
        itemDTO.setDescription(description);
        itemDTO.setName(name);
        itemDTO.setCategoryIdentifier(categoryIdentifier);
        return itemDTO;
    }

}
