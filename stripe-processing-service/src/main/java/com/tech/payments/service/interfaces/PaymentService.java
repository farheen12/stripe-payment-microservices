package com.tech.payments.service.interfaces;

import com.tech.payments.pojo.CreatePaymentRequest;
import com.tech.payments.pojo.PaymentResponse;

public interface PaymentService {
	
	public PaymentResponse createPayment(CreatePaymentRequest createPaymentRequest);

	public PaymentResponse getPayment(String id);

	public PaymentResponse expirePayment(String id);

}
