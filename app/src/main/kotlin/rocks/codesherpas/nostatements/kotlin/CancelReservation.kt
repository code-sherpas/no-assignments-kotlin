package rocks.codesherpas.nostatements.kotlin

import rocks.codesherpas.nostatements.java.EventPublisher
import rocks.codesherpas.nostatements.java.ReservationRepository
import rocks.codesherpas.nostatements.java.ReservationSearcher
import java.time.DayOfWeek
import java.time.DayOfWeek.SUNDAY
import java.time.ZonedDateTime
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
            ?.let { lastReservation ->
            if (today() != SUNDAY && lastReservation.isFinished) {
                reservationRepository.findReservationById(reservationId)
                    ?.apply { this.cancel() }
                    ?.also { eventPublisher.publish(it.pullEvents()) }
                    ?.also {
                        LOGGER.info("Reservation with id: ${it.reservationId} was cancelled.")
                    } ?: throw InvalidCancelOperationException()
            }
        } ?: cancelWithPenalty(reservationId, guestId)
    }

    private fun cancelWithPenalty(reservationId: ReservationId, guestId: GuestId) {
        // apply some cancellation mechanism
    }

    private fun today(): DayOfWeek = ZonedDateTime.now().dayOfWeek
}
