package rocks.codesherpas.nostatements;

import java.util.UUID;

class ReservationId {
    private final UUID value;

    public ReservationId() {
        this.value = UUID.randomUUID();
    }

    public UUID getValue() {
        return value;
    }
}