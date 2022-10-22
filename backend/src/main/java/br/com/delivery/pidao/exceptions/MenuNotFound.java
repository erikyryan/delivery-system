package br.com.delivery.pidao.exceptions;

public class MenuNotFound extends RuntimeException {

    private static final long serialVersionUID = -285004475679602743L;

    public MenuNotFound() {
        super();
    }

    public MenuNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public MenuNotFound(String message) {
        super(message);
    }

}
