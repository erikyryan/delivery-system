package br.com.delivery.pidao.entities;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Category {

    @Id
    private long Id;

    @Column(name = "categoryidentifier")
    private String categoryIdentifier = UUID.randomUUID().toString();

    @NotNull
    private String details;

    @Nullable
    @OneToMany
    private List<Item> items;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idmenu",referencedColumnName = "id")
    private Menu idMenu;
}
