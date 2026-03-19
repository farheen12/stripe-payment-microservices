package com.tech.payments.service.interfaces;

import com.tech.payments.pojo.NotificationRequest;

public interface NotificationService {
	
	public void processNotification(NotificationRequest notificationRequest);

}
