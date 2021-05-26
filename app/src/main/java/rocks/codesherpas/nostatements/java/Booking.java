package rocks.codesherpas.nostatements.java;

import java.util.logging.Logger;

public class Booking {

    private static final Logger LOGGER = Logger.getLogger(Booking.class.getName());

    public final Status status;

    private Booking(Status status) {
        this.status = status;
    }

    private Booking(Booking.Builder builder) {
        this.status = builder.status;
    }

    //let & nullability example
    public void foo() {
        String name = getStringOrNull();
        if (name != null) System.out.println(name);
    }

    private String getStringOrNull() {
        return null;
    }

    // also example
    public Booking disable() {
        final Booking xyz = new Booking(Status.DISABLED);
        LOGGER.info("Enabled");
        return xyz;
    }

    static class Builder {

        private Status status;

        public Builder status(Status status) {
            this.status = status;
            return this;
        }

        public Booking build() {
            return new Booking(this);
        }
    }
}

enum Status {
    ENABLED, DISABLED
}
