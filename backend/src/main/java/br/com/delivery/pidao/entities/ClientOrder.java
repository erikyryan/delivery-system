package br.com.delivery.pidao.entities;

import br.com.delivery.pidao.entities.dto.OrderDTO;
import br.com.delivery.pidao.enums.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ClientOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID uuid;

    private String name;
    private String value;
    private OrderStatus status;
    private String comment;

    private String clientIdentifier;

    public OrderDTO orderToDto() {

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setName(name);
        orderDTO.setValue(value);
        orderDTO.setStatus(status);
        orderDTO.setComment(comment);

        return orderDTO;
    }
}
