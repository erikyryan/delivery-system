package enums;

public enum RatingDish {
    PESSIMO(0),
    BOM(1),
    EXECELENTE(2),
    PERFEiTO(3);

    int code;

    RatingDish(int i) {
        code = i;
    }
}
