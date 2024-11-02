package foot_court.messaging.ports.persistency.mysql.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notification_pins")
@IdClass(NotificationPinId.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotificationPinEntity {

    @Id
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Id
    @Column(name = "pin", nullable = false)
    private String pin;
}
