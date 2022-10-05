package br.com.delivery.pidao.enums;

public enum Rating {
    PESSIMO(0),
    BOM(1),
    EXECELENTE(2),
    PERFEiTO(3);

    int code;

    Rating(int i) {
        code = i;
    }
}
