package com.tech.payments.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.stripe.model.Event;
import com.stripe.net.Webhook;
import com.tech.payments.constant.ErrorCodeEnum;
import com.tech.payments.exception.StripeProviderException;
import com.tech.payments.service.ProcessStripeEventAsync;
import com.tech.payments.service.interfaces.StripeWebhookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class StripeWebhookServiceImpl implements StripeWebhookService {

	@Value("${stripe.webhook.secret}")
	private String endpointSecret;
	
	private final ProcessStripeEventAsync processStripeEventAsync;

	@Override
	public String handleStripeWebhook(String sigHeader, String jsonPayload) {
		log.info("Received Stripe Webhook|| jsonPayload: {}", jsonPayload);

		Event event = checkSignValid(sigHeader, jsonPayload);

		log.info("HmacSHA256 Signature is valid. Now processing Stripe Event: {}", 
				event.getType());
		
		processStripeEventAsync.processStripeEvent(event);
		
		log.info("Stripe Event processing initiated asynchronously for eventId:{}", event.getId());
		
		return "Stripe webhook processed";
	}

	

	/**
	 * This method returns valid Event object if signature is valid.
	 * If Signature invalid throws StripeProviderException
	 * @param sigHeader
	 * @param jsonPayload
	 * @return
	 */
	private Event checkSignValid(String sigHeader, String jsonPayload) {
		try {
			Event event = Webhook.constructEvent(
					jsonPayload, sigHeader, endpointSecret
					);
			log.info("Stripe Signature Valid for eventId:{} | eventType:{}", event.getId(), event.getType());
			return event;

		} catch (Exception e) {
			log.error("Webhook error while validating signature.", e);

			throw new StripeProviderException(
					ErrorCodeEnum.INVALID_STRIPE_SIGNATURE.getErrorCode(), 
					ErrorCodeEnum.INVALID_STRIPE_SIGNATURE.getErrorMessage(), 
					HttpStatus.BAD_REQUEST);
		}
	}

}
