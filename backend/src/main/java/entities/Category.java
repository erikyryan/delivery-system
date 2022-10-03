package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity

public class Category {

    @Id
    private long Id;

    private String Details;

    @OneToMany
    private List<Dish> DishCategory;
    @ManyToOne
    private Menu MenuCategory;


}
