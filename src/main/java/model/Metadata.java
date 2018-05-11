package model;

/**
 * Created by sewwandiwi on 4/4/2018.
 */
public class Metadata {
    String intentId;
    Boolean webhookUsed;
    Boolean webhookForSlotFillingUsed;
    Integer webhookResponseTime;
    String intentName;

    public String getIntentId() {
        return intentId;
    }

    public void setIntentId(String intentId) {
        this.intentId = intentId;
    }

    public Boolean getWebhookUsed() {
        return webhookUsed;
    }

    public void setWebhookUsed(Boolean webhookUsed) {
        this.webhookUsed = webhookUsed;
    }

    public Boolean getWebhookForSlotFillingUsed() {
        return webhookForSlotFillingUsed;
    }

    public void setWebhookForSlotFillingUsed(Boolean webhookForSlotFillingUsed) {
        this.webhookForSlotFillingUsed = webhookForSlotFillingUsed;
    }

    public Integer getWebhookResponseTime() {
        return webhookResponseTime;
    }

    public void setWebhookResponseTime(Integer webhookResponseTime) {
        this.webhookResponseTime = webhookResponseTime;
    }

    public String getIntentName() {
        return intentName;
    }

    public void setIntentName(String intentName) {
        this.intentName = intentName;
    }
}
