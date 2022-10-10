package br.com.delivery.pidao.enums;

public enum ClientType {

    MANAGER(0),
    DELIVERYMAN(1),
    CUSTOMER(2);

    int code;


    ClientType(int i) {
        code = i;
    }
}
