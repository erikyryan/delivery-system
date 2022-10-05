package br.com.delivery.pidao.entities;

import br.com.delivery.pidao.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Delivery extends User {

    @Id
    private Long id;

    private OrderStatus Status;

    //Arquitetar melhor o relacinamento de endereço com entregador e cliente
    //@ManyToOne
    //List<Adress> AdressCLient;
}
