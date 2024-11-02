package foot_court.messaging.domain.api.usecase;

import foot_court.messaging.domain.api.INotificationServicePort;
import foot_court.messaging.domain.spi.IMessagePersistencePort;
import foot_court.messaging.domain.spi.INotificationPinPersistencePort;

import java.util.Random;

import static foot_court.messaging.domain.MessaggeUtils.PIN_FORMAT;
import static foot_court.messaging.domain.MessaggeUtils.PIN_MESSAGE;

public class SendNotificationUseCase implements INotificationServicePort {
    private final IMessagePersistencePort messagePersistencePort;
    private final INotificationPinPersistencePort notificationPinPersistencePort;
    private final Random random;

    public SendNotificationUseCase(IMessagePersistencePort messagePersistencePort, INotificationPinPersistencePort notificationPinPersistencePort) {
        this.messagePersistencePort = messagePersistencePort;
        this.notificationPinPersistencePort = notificationPinPersistencePort;
        this.random = new Random();
    }

    @Override
    public void notifyOrderReady(String phoneNumber) {
        String pin = generatePin();
        String message = PIN_MESSAGE + pin;

        if (notificationPinPersistencePort.findPinByPhoneNumber(phoneNumber).isPresent()) {
            notificationPinPersistencePort.updatePin(phoneNumber, pin);
        } else {
            notificationPinPersistencePort.savePin(phoneNumber, pin);
        }
        messagePersistencePort.sendMessage(phoneNumber, message);
    }

    private String generatePin() {
        return String.format(PIN_FORMAT, random.nextInt(1_000_000));
    }
}