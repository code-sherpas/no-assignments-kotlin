package rocks.codesherpas.nostatements.java;

import rocks.codesherpas.nostatements.kotlin.GuestId;

public class ReservationSearcher {
    private final ReservationRepository reservationRepository;

   public ReservationSearcher(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation lastBy(GuestId guestId) {
       //search last reservation by guest Id
       return new Reservation();
    }

}
