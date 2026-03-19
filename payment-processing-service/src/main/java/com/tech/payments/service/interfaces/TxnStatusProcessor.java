package com.tech.payments.service.interfaces;

import com.tech.payments.dto.TransactionDTO;

public interface TxnStatusProcessor {
	
	public TransactionDTO processStatus(TransactionDTO txnDto);

}
