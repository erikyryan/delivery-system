package br.com.delivery.pidao.enums;

public enum UserTypeEnum {

    MANAGER(0),
    DELIVERYMAN(1),
    CUSTOMER(2);

    int code;


    UserTypeEnum(int i) {
        code = i;
    }
}
