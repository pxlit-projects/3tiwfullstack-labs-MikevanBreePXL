package be.pxl.services.service;

import be.pxl.services.domain.dto.NotificationRequest;
import be.pxl.services.domain.dto.NotificationResponse;

import java.util.List;

public interface INotificationService {
    List<NotificationResponse> getAllNotifications();
    NotificationResponse getNotificationById(Long id);
    void addNotification(NotificationRequest notification);
    void updateNotification(Long id, NotificationRequest notification);
    void deleteNotification(Long id);
}
