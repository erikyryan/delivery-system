package br.com.delivery.pidao.entities;


import br.com.delivery.pidao.entities.dto.ItemDTO;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Id
    @NotNull
    private String name;

    @Nullable
    @OneToMany
    private List<Rating> ratings;

    @NotNull
    private Double value;

    @Nullable
    private String description;

    @Id
    @NotNull
    @ManyToOne
    @JoinColumn(name="category", referencedColumnName = "details")
    private Category category;

    public ItemDTO entityToDTO(){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setValue(value);
        itemDTO.setDescription(description);
        itemDTO.setName(name);
        itemDTO.setCategoryDetails(category.getDetails());
        return itemDTO;
    }

}
