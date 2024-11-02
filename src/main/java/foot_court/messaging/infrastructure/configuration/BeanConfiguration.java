package foot_court.messaging.infrastructure.configuration;

import foot_court.messaging.domain.api.INotificationServicePort;
import foot_court.messaging.domain.api.usecase.SendNotificationUseCase;
import foot_court.messaging.domain.spi.IMessagePersistencePort;
import foot_court.messaging.domain.spi.INotificationPinPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IMessagePersistencePort messagePersistencePort;
    private final INotificationPinPersistencePort notificationPinPersistencePort;

    @Bean
    INotificationServicePort notificationServicePort() {
        return new SendNotificationUseCase(messagePersistencePort, notificationPinPersistencePort);
    }
}
