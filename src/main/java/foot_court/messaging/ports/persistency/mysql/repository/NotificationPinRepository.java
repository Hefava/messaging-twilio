package foot_court.messaging.ports.persistency.mysql.repository;

import foot_court.messaging.ports.persistency.mysql.entity.NotificationPinEntity;
import foot_court.messaging.ports.persistency.mysql.entity.NotificationPinId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationPinRepository extends JpaRepository<NotificationPinEntity, NotificationPinId> {
    Optional<NotificationPinEntity> findByPhoneNumberAndPin(String phoneNumber, String pin);
    void deleteByPhoneNumberAndPin(String phoneNumber, String pin);
    Optional<NotificationPinEntity> findByPhoneNumber(String phoneNumber);
}