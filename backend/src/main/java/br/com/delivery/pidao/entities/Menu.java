package br.com.delivery.pidao.entities;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@IdClass(Menu.class)
public class Menu implements Serializable {

    @Id
    private long Id;

    @Column(name = "menuidentifier")
    private String menuIdentifier = UUID.randomUUID().toString();

    @OneToOne
    private Restaurant restaurant;

    @OneToMany(mappedBy = "idCategory",cascade=CascadeType.ALL)
    private List<Category> CategoryMenu;

}
