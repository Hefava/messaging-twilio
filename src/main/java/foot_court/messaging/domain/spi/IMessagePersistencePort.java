package foot_court.messaging.domain.spi;

public interface IMessagePersistencePort {
    void sendMessage(String phoneNumber, String message);
}
