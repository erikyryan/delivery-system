package br.com.delivery.pidao.entities.dto;

import br.com.delivery.pidao.enums.OrderStatus;
import lombok.Data;

@Data
public class OrderDTO {

    private String name;
    private String value;
    private OrderStatus status;
    private String comment;

}
