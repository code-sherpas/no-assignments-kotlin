package rocks.codesherpas.nostatements.java;

import java.util.UUID;

public class ReservationId {
    private final UUID value;

    public ReservationId() {
        this.value = UUID.randomUUID();
    }

    public UUID getValue() {
        return value;
    }
}