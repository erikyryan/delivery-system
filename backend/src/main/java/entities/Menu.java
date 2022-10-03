package entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class Menu {

    @Id
    private long Id;

    @OneToMany
    private List<Category> CategoryMenu;

}
