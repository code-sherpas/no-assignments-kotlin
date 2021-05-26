package rocks.codesherpas.nostatements.java;

import rocks.codesherpas.nostatements.kotlin.GuestId;

import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import java.util.logging.Logger;

import static java.time.DayOfWeek.SUNDAY;

public class CancelReservation {
    private final ReservationRepository reservationRepository;
    private final ReservationSearcher reservationSearcher;
    private final EventPublisher eventPublisher;

    private static final Logger LOGGER = Logger.getLogger(CancelReservation.class.getName());

    public CancelReservation(ReservationRepository reservationRepository, ReservationSearcher reservationSearcher, EventPublisher eventPublisher) {
        this.reservationRepository = reservationRepository;
        this.reservationSearcher = reservationSearcher;
        this.eventPublisher = eventPublisher;
    }

    public void invokeWith(ReservationId reservationId, GuestId guestId) {
        Reservation reservation = reservationSearcher.lastBy(guestId);

        if (reservation != null) {
            if (reservation.isFinished()) {
                Reservation reservation1 = reservationRepository.findReservationById(reservationId);
                reservation1.cancel();

                eventPublisher.publish(reservation.pullEvents());
                LOGGER.info(formatLogMessage(reservation1));
            }
        } else {
            cancelWithPenalty(reservationId, guestId);
        }
    }

    private void cancelWithPenalty(ReservationId reservationId, GuestId guestId) {
        // apply some cancellation mechanism
    }

    private String formatLogMessage(Reservation reservation1) {
        return "Reservation with id: %s was cancelled.".formatted(reservation1.getReservationId());
    }

    private DayOfWeek today() {
        return ZonedDateTime.now().getDayOfWeek();
    }
}
