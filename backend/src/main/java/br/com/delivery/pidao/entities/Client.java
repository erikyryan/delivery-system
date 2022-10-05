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
public class Client extends User {

    @Id
    private Long id;

    @OneToOne
    private Adress AdressClient;

    @OneToMany
    private List<Order> OrderClient;


}
