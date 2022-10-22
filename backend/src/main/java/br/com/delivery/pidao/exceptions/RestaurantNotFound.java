package br.com.delivery.pidao.exceptions;

public class RestaurantNotFound extends RuntimeException {

    private static final long serialVersionUID = -285004475679602743L;

    public RestaurantNotFound() {
        super();
    }

    public RestaurantNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public RestaurantNotFound(String message) {
        super(message);
    }
}
