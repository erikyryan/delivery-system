package br.com.delivery.pidao.domain.order.dto;

import br.com.delivery.pidao.domain.order.entity.CustomerOrder;
import br.com.delivery.pidao.domain.order.enums.OrderStatus;
import lombok.Data;

@Data
public class OrderDTO {

    private String name;
    private String value;
    private OrderStatus status;
    private String comment;
    private String customerIdentifier;

    public CustomerOrder dtoToEntity() {

        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setName(name);
        customerOrder.setValue(value);
        customerOrder.setStatus(status);
        customerOrder.setComment(comment);
        customerOrder.setCustomerIdentifier(customerIdentifier);

        return customerOrder;
    }
}
