package be.pxl.services.service;

import be.pxl.services.domain.dto.NotificationRequest;
import be.pxl.services.domain.dto.NotificationResponse;

import java.util.List;

public interface INotificationService {
    List<NotificationResponse> getAllNotifications();
    void addNotification(NotificationRequest notification);
}
