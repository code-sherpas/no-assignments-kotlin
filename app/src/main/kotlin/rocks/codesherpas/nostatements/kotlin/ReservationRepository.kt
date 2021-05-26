package rocks.codesherpas.nostatements.kotlin

import rocks.codesherpas.nostatements.java.Reservation


class ReservationRepository {
    fun findReservationById(reservationId: ReservationId): Reservation? {
        // findById(reservationId)
        return Reservation()
    }
}