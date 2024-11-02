package foot_court.messaging.domain.api.usecase;

import foot_court.messaging.domain.api.INotificationServicePort;
import foot_court.messaging.domain.spi.IMessagePersistencePort;
import java.util.Random;

public class SendNotificationUseCase implements INotificationServicePort {
    private final IMessagePersistencePort messagePersistencePort;
    private final Random random;

    public SendNotificationUseCase(IMessagePersistencePort messagePersistencePort) {
        this.messagePersistencePort = messagePersistencePort;
        this.random = new Random();
    }

    @Override
    public void notifyOrderReady(String phoneNumber) {
        String pin = generatePin();
        String message = "Your order is ready. Use this security PIN to collect it: " + pin;
        messagePersistencePort.sendMessage(phoneNumber, message);
    }

    private String generatePin() {
        return String.format("%06d", random.nextInt(1_000_000));
    }
}