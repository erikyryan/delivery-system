package br.com.delivery.pidao.entities;


import br.com.delivery.pidao.enums.Rating;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
@NoArgsConstructor
public class Item {
    @Id
    private long Id;

    @NotNull
    private String name;

    @Nullable
    private Rating rating;

    @NotNull
    private Double value;

    @Nullable
    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(name="category", referencedColumnName = "details")
    private Category category;

}
