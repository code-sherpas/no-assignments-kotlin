package rocks.codesherpas.nostatements.java;

import java.util.Collections;
import java.util.List;

public class Reservation {
    private final ReservationId reservationId;

    public Reservation() {
        this.reservationId = new ReservationId();
    }

    public ReservationId getReservationId() {
        return reservationId;
    }

    public void cancel() {}

    public List<Event> pullEvents() {
        return Collections.emptyList();
    }

    public boolean isFinished() {
        return false;
    }
}
