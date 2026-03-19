package com.tech.payments.service.interfaces;

import com.tech.payments.dto.TransactionDTO;

public interface PaymentStatusService {
	
	public TransactionDTO processStatus(TransactionDTO txnDto);

}
