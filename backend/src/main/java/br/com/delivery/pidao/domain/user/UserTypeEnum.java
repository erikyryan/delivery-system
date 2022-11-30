package br.com.delivery.pidao.domain.user;

public enum UserTypeEnum {

    MANAGER(0),
    DELIVERYMAN(1),
    CUSTOMER(2);

    int code;

    UserTypeEnum(int i) {
        code = i;
    }
}
