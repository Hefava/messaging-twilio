package foot_court.messaging.domain.api;

public interface INotificationServicePort {
    void notifyOrderReady(String phoneNumber);
}
