package br.com.delivery.pidao.domain.order;

import br.com.delivery.pidao.domain.order.entity.CustomerOrder;
import br.com.delivery.pidao.domain.restaurant.Restaurant;
import br.com.delivery.pidao.domain.user.Users;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID uuid;

    @OneToOne
    private Users user;

    @OneToOne
    private Restaurant restaurant;

    @OneToOne
    private CustomerOrder customerOrder;


}
