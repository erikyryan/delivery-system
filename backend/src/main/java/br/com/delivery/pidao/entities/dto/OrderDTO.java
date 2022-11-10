package br.com.delivery.pidao.entities.dto;

import br.com.delivery.pidao.entities.ClientOrder;
import br.com.delivery.pidao.enums.OrderStatus;
import lombok.Data;

@Data
public class OrderDTO {

    private String name;
    private String value;
    private OrderStatus status;
    private String comment;
    private String clientIdentifier;

    public ClientOrder dtoToEntity() {

        ClientOrder clientOrder = new ClientOrder();
        clientOrder.setName(name);
        clientOrder.setValue(value);
        clientOrder.setStatus(status);
        clientOrder.setComment(comment);
        clientOrder.setClientIdentifier(clientIdentifier);

        return clientOrder;
    }
}
