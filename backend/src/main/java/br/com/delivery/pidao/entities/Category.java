package br.com.delivery.pidao.entities;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@IdClass(Category.class)
public class Category implements Serializable {

    @Id
    private long id;

    @Column(name = "categoryidentifier")
    private String categoryIdentifier = UUID.randomUUID().toString();

    @NotNull
    private String details;

    @Nullable
    @OneToMany
    private List<Item> items;

    @MapsId
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMenu",referencedColumnName = "id")
    private Menu menu;
}
