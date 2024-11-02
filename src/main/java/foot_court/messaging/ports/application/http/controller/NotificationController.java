package foot_court.messaging.ports.application.http.controller;

import foot_court.messaging.domain.api.INotificationServicePort;
import foot_court.messaging.ports.application.http.dto.OrderReadyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class NotificationController {
    private final INotificationServicePort notificationServicePort;

    @PostMapping("/order-ready")
    public ResponseEntity<Void> notifyOrderReady(@RequestBody OrderReadyRequest request) {
        notificationServicePort.notifyOrderReady(request.getPhoneNumber());
        return ResponseEntity.ok().build();
    }
}
