package foot_court.messaging.ports.persistency.mysql.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotificationPinId implements Serializable {

    private String phoneNumber;
    private String pin;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotificationPinId)) return false;
        NotificationPinId that = (NotificationPinId) o;
        return phoneNumber.equals(that.phoneNumber) && pin.equals(that.pin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber, pin);
    }
}