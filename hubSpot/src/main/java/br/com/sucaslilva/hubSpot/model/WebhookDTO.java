package br.com.sucaslilva.hubSpot.model;

import lombok.Data;

@Data
public class WebhookDTO {
    private String objectId;
    private String eventId;
    private String eventType;
    private String occurredAt;
}
