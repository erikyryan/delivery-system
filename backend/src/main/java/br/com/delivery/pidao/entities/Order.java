package br.com.delivery.pidao.entities;

import br.com.delivery.pidao.enums.OrderStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Entity
public class Order implements Serializable {

    @Id
    private long Id;

    private String Name;
    private String Value;
    private OrderStatus Status;
    private String Comment;

    @ManyToOne
    private Client ClientOrder;

}
