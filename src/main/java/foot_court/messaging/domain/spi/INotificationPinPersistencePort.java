package foot_court.messaging.domain.spi;

import java.util.Optional;

public interface INotificationPinPersistencePort {
    void savePin(String phoneNumber, String pin);
    void updatePin(String phoneNumber, String pin);
    Optional<String> findPinByPhoneNumber(String phoneNumber);
}
