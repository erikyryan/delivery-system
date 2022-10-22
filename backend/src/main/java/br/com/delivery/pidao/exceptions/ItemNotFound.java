package br.com.delivery.pidao.exceptions;

public class ItemNotFound extends RuntimeException {

    private static final long serialVersionUID = -285004475679602743L;

    public ItemNotFound() {
        super();
    }

    public ItemNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemNotFound(String message) {
        super(message);
    }

}
