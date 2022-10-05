package br.com.delivery.pidao.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Getter
@Setter
@Entity
public class Menu {

    @Id
    private long Id;

    @OneToOne
    private Restaurant restaurant;

    @OneToMany
    private List<Category> CategoryMenu;

}
