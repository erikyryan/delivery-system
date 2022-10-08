package br.com.delivery.pidao.entities;


import br.com.delivery.pidao.enums.Rating;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
