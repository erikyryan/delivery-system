package entities;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.List;

public class Restaurant extends Information {

    @OneToOne
    private Adress AdressRestaurant;
    @ManyToOne
    private List<Manager> ManagerRestaurant;
}
