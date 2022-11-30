package br.com.delivery.pidao.exception;

public class CategoryNotFound extends RuntimeException {

    private static final long serialVersionUID = -285004475679602743L;

    public CategoryNotFound() {
        super();
    }

    public CategoryNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryNotFound(String message) {
        super(message);
    }
}
