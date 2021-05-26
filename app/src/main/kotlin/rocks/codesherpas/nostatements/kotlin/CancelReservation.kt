package rocks.codesherpas.nostatements.kotlin

import rocks.codesherpas.nostatements.java.EventPublisher
import rocks.codesherpas.nostatements.java.ReservationSearcher

import java.util.logging.Logger

class CancelReservation(
    private val reservationRepository: ReservationRepository,
    private val reservationSearcher: ReservationSearcher,
    private val eventPublisher: EventPublisher
) {
    companion object {
        private val LOGGER = Logger.getLogger(CancelReservation::class.java.name)
    }

    fun invokeWith(reservationId: ReservationId, guestId: GuestId) {
        reservationSearcher.lastBy(guestId)
            ?.let {
                if (it.isFinished) {
                    reservationRepository.findReservationById(reservationId)
                        ?.apply { cancel() }
                        ?.also { eventPublisher.publish(it.pullEvents()) }
                        ?.apply {
                            LOGGER.info("Reservation with id: $reservationId was cancelled.")
                        } ?: throw InvalidCancelOperationException()
                }
            } ?: cancelWithPenalty(reservationId, guestId)
    }

    private fun cancelWithPenalty(reservationId: ReservationId, guestId: GuestId) {
        // apply some cancellation mechanism
    }
}
