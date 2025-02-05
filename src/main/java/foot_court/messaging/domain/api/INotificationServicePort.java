package foot_court.messaging.domain.api;

public interface INotificationServicePort {
    void notifyOrderReady(String phoneNumber);
    String getPin(String phoneNumber);
}
