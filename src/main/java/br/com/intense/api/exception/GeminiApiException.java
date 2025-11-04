package br.com.intense.api.exception;

public class GeminiApiException extends RuntimeException {
    public GeminiApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
