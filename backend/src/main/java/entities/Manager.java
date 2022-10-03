package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Getter
@Setter
@Entity
public class Manager extends Information {
    private String Department;

    @ManyToOne
    private Restaurant RestaurantManager;
}
