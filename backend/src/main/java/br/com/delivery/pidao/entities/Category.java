package br.com.delivery.pidao.entities;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Category {

    @Id
    private long Id;

    @NotNull
    private String details;

    @Nullable
    @OneToMany
    private List<Item> itemCategory;

    @NotNull
    @ManyToOne
    private Menu menu;


}
