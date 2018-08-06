package don.geronimo.chadepanela.utils;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class MensagemErro {

    private final Long timestamp;
    private final int status;
    private final String error;
    private final String message;
    private final String exception;

    public MensagemErro(HttpStatus status, String mensagem, Exception exception) {
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.timestamp = new Date().getTime();
        this.message = mensagem;
        this.exception = exception.getClass().getCanonicalName();
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getException() {
        return exception;
    }

    public String getError() {
        return error;
    }

    public Long getTimestamp() {
        return timestamp;
    }
}

