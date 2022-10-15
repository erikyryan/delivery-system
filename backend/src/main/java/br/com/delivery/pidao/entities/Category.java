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
@RequiredArgsConstructor
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "categoryidentifier")
    private String categoryIdentifier = UUID.randomUUID().toString();

    @NotNull
    private String details;

    @OneToMany
    private List<Item> items;

    @ManyToOne
    private Menu menu;
}
