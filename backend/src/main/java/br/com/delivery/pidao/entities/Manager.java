package br.com.delivery.pidao.entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;


@Data
@Entity
public class Manager extends User implements Serializable {

    @Id
    private Long id;

    private String Department;

    @ManyToOne
    private Restaurant RestaurantManager;
}
