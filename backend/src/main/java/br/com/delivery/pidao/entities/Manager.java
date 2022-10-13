package br.com.delivery.pidao.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;


@Getter
@Setter
@Entity
public class Manager extends User implements Serializable {

    @Id
    private Long id;

    private String Department;

    @NotNull
    @ManyToOne
    private Restaurant RestaurantManager;
}
