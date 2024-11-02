package foot_court.messaging.domain;

public class MessaggeUtils {
    public static final String PIN_MESSAGE = "Your order is ready. Use this security PIN to collect it: ";

    public static final String PIN_FORMAT = "%06d";

    private MessaggeUtils() {
        throw new AssertionError("Cannot instantiate this class");
    }
}
