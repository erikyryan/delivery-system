package entities;


import enums.RatingDish;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class Dish {
    @Id
    private long Id;

    private String Name;
    private RatingDish Rating;
    private String Value;
    private String Ingredients;

    @ManyToOne
    private Category CategoryDish;
}
