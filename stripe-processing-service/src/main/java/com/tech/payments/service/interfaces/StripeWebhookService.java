package com.tech.payments.service.interfaces;

public interface StripeWebhookService {
	
	public String handleStripeWebhook(String sigHeader, String jsonPayload);

}
