package model;

/**
 * Created by sewwandiwi on 4/4/2018.
 */
public class Status {
    String code;
    String errorType;
    String webhookTimedOut;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getWebhookTimedOut() {
        return webhookTimedOut;
    }

    public void setWebhookTimedOut(String webhookTimedOut) {
        this.webhookTimedOut = webhookTimedOut;
    }
}
