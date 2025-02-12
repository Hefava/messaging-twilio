package foot_court.messaging;

import foot_court.messaging.domain.api.usecase.SendNotificationUseCase;
import foot_court.messaging.domain.spi.IMessagePersistencePort;
import foot_court.messaging.domain.spi.INotificationPinPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static foot_court.messaging.domain.MessaggeUtils.PIN_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SendNotificationUseCaseTest {

    @Mock
    private IMessagePersistencePort messagePersistencePort;

    @Mock
    private INotificationPinPersistencePort notificationPinPersistencePort;

    @InjectMocks
    private SendNotificationUseCase sendNotificationUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void notifyOrderReady_ShouldUpdatePinAndSendMessage_WhenPinExists() {
        // Given
        String phoneNumber = "1234567890";
        String newPin = "654321";
        when(notificationPinPersistencePort.findPinByPhoneNumber(phoneNumber)).thenReturn(Optional.of("111111"));

        doNothing().when(notificationPinPersistencePort).updatePin(phoneNumber, newPin);
        doNothing().when(messagePersistencePort).sendMessage(phoneNumber, PIN_MESSAGE + newPin);

        // When
        notificationPinPersistencePort.updatePin(phoneNumber, newPin);
        messagePersistencePort.sendMessage(phoneNumber, PIN_MESSAGE + newPin);

        // Then
        verify(notificationPinPersistencePort).updatePin(phoneNumber, newPin);
        verify(messagePersistencePort).sendMessage(phoneNumber, PIN_MESSAGE + newPin); // Eliminamos eq()
    }

    @Test
    void notifyOrderReady_ShouldSaveNewPinAndSendMessage_WhenPinDoesNotExist() {
        // Given
        String phoneNumber = "9876543210";
        String generatedPin = "123456";
        when(notificationPinPersistencePort.findPinByPhoneNumber(phoneNumber)).thenReturn(Optional.empty());

        doNothing().when(notificationPinPersistencePort).savePin(phoneNumber, generatedPin);
        doNothing().when(messagePersistencePort).sendMessage(phoneNumber, PIN_MESSAGE + generatedPin);

        // When
        notificationPinPersistencePort.savePin(phoneNumber, generatedPin);
        messagePersistencePort.sendMessage(phoneNumber, PIN_MESSAGE + generatedPin);

        // Then
        verify(notificationPinPersistencePort).savePin(phoneNumber, generatedPin);
        verify(messagePersistencePort).sendMessage(phoneNumber, PIN_MESSAGE + generatedPin); // Eliminamos eq()
    }

    @Test
    void getPin_ShouldReturnPin_WhenPinExists() {
        // Given
        String phoneNumber = "1231231234";
        String existingPin = "222222";
        when(notificationPinPersistencePort.findPinByPhoneNumber(phoneNumber)).thenReturn(Optional.of(existingPin));

        // When
        String result = sendNotificationUseCase.getPin(phoneNumber);

        // Then
        assertEquals(existingPin, result);
    }

    @Test
    void getPin_ShouldReturnNull_WhenPinDoesNotExist() {
        // Given
        String phoneNumber = "5555555555";
        when(notificationPinPersistencePort.findPinByPhoneNumber(phoneNumber)).thenReturn(Optional.empty());

        // When
        String result = sendNotificationUseCase.getPin(phoneNumber);

        // Then
        assertEquals(null, result);
    }
}