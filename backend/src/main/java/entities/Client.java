package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Getter
@Setter
@Entity
public class Client extends Information {

    @OneToOne
    private Adress AdressClient;

    @OneToMany
    private List<Order> OrderClient;


}
