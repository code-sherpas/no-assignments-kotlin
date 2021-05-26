package rocks.codesherpas.nostatements.kotlin
import mu.KLogger
import mu.KotlinLogging

private val logger: KLogger = KotlinLogging.logger {}

data class Room(val status: Status = Status.IN_USE) {


    //let & nullability example
    fun foo() = getStringOrNull()?.let { println(it) }

    private fun getStringOrNull(): String? = null

    // also example
    fun disable(): Room =
        this.copy(status = Status.EMPTY)
            .also { logger.info { "Enabled" } }
}

enum class Status {
    IN_USE, EMPTY, OUT_OF_SERVICE
}