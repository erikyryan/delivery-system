package br.com.delivery.pidao.entities;

import br.com.delivery.pidao.enums.OrderStatus;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Delivery implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Users users;

    private OrderStatus Status;
}
