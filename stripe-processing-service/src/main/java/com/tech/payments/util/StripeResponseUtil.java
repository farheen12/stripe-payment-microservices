package com.tech.payments.util;

import com.tech.payments.pojo.PaymentResponse;
import com.tech.payments.stripe.StripeResponse;

public class StripeResponseUtil {
	
	private StripeResponseUtil() {
	}
	
	public static PaymentResponse preparePaymentResponse(StripeResponse stripeResponse) {
		PaymentResponse paymentRes = new PaymentResponse();
		paymentRes.setId(stripeResponse.getId());
		paymentRes.setUrl(stripeResponse.getUrl());
		paymentRes.setSessionStatus(stripeResponse.getStatus());
		paymentRes.setPaymentStatus(stripeResponse.getPaymentStatus());
		return paymentRes;
	}

}
