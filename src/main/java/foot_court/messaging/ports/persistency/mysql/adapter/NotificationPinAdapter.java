package foot_court.messaging.ports.persistency.mysql.adapter;

import foot_court.messaging.domain.spi.INotificationPinPersistencePort;
import foot_court.messaging.ports.persistency.mysql.entity.NotificationPinEntity;
import foot_court.messaging.ports.persistency.mysql.repository.NotificationPinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NotificationPinAdapter implements INotificationPinPersistencePort {
    private final NotificationPinRepository notificationPinRepository;

    @Override
    public void savePin(String phoneNumber, String pin) {
        NotificationPinEntity notificationPinEntity = new NotificationPinEntity();
        notificationPinEntity.setPhoneNumber(phoneNumber);
        notificationPinEntity.setPin(pin);
        notificationPinRepository.save(notificationPinEntity);
    }

    @Override
    public void updatePin(String phoneNumber, String pin) {
        notificationPinRepository.findByPhoneNumber(phoneNumber).ifPresent(notificationPinRepository::delete);
        savePin(phoneNumber, pin);
    }

    @Override
    public Optional<String> findPinByPhoneNumber(String phoneNumber) {
        return notificationPinRepository.findByPhoneNumber(phoneNumber)
                .map(NotificationPinEntity::getPin);
    }
}
