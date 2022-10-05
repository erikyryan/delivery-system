package br.com.delivery.pidao.entities;

import br.com.delivery.pidao.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class Order {

    @Id
    private long Id;

    private String Name;
    private String Value;
    private OrderStatus Status;
    private String Comment;

    @ManyToOne
    private Client ClientOrder;

}
