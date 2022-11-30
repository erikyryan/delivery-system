package br.com.delivery.pidao.domain.order.enums;

public enum OrderStatus {

    ANDAMENTO(0),
    PROCESSANDO(1),
    FINALIZADO(2),
    Entregue(3);

    int code;


    OrderStatus(int i) {
        code = i;
    }
}
