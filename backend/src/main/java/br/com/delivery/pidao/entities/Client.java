package br.com.delivery.pidao.entities;

import br.com.delivery.pidao.enums.Rating;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Client extends User {

    @Id
    @NotNull
    private Long id;

    @NotNull
    @OneToOne
    private Adress AdressClient;

    @OneToMany
    private List<Rating> ratings;

    @OneToMany
    private List<Order> OrderClient;


}
