package foot_court.messaging.ports.persistency.mysql.adapter;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import foot_court.messaging.domain.spi.IMessagePersistencePort;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageAdapter implements IMessagePersistencePort {

    @Value("${twilio.account-sid}")
    private String accountSid;

    @Value("${twilio.auth-token}")
    private String authToken;

    @Value("${twilio.phone-number}")
    private String twilioPhoneNumber;

    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
    }

    @Override
    public void sendMessage(String phoneNumber, String message) {
        Message.creator(
                new PhoneNumber(phoneNumber),
                new PhoneNumber(twilioPhoneNumber),
                message
        ).create();
    }
}