package be.pxl.services.service;

import be.pxl.services.domain.Notification;
import be.pxl.services.domain.dto.NotificationRequest;
import be.pxl.services.domain.dto.NotificationResponse;
import be.pxl.services.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService implements INotificationService {
    private final NotificationRepository notificationRepository;

    @Override
    public List<NotificationResponse> getAllNotifications() {
        return notificationRepository.findAll().stream().map(notification -> mapToNotificationResponse(notification)).toList();
    }

    @Override
    public NotificationResponse getNotificationById(Long id) {
        Notification entity = notificationRepository.getReferenceById(id);
        return mapToNotificationResponse(entity);
    }

    private NotificationResponse mapToNotificationResponse(Notification entity) {
        return NotificationResponse.builder()
                .from(entity.getFrom())
                .to(entity.getTo())
                .subject(entity.getSubject())
                .message(entity.getMessage())
                .build();
    }

    @Override
    public void addNotification(NotificationRequest notification) {
        Notification entity = Notification.builder()
                .from(notification.getFrom())
                .to(notification.getTo())
                .subject(notification.getSubject())
                .message(notification.getMessage())
                .build();
        notificationRepository.save(entity);
    }

    @Override
    public void updateNotification(Long id, NotificationRequest notification) {
        Notification entity = notificationRepository.getReferenceById(id);
        entity.setFrom(notification.getFrom());
        entity.setTo(notification.getTo());
        entity.setSubject(notification.getSubject());
        entity.setMessage(notification.getMessage());

        notificationRepository.save(entity);
    }

    @Override
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
        notificationRepository.flush();
    }
}
