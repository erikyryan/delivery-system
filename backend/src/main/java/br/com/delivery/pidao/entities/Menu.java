package br.com.delivery.pidao.entities;


import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@RequiredArgsConstructor
public class Menu {

    @Id
    private long Id;

    @Column(name = "menuidentifier")
    private String menuIdentifier = UUID.randomUUID().toString();

    @OneToOne
    private Restaurant restaurant;

    @OneToMany
    private List<Category> CategoryMenu;

}
