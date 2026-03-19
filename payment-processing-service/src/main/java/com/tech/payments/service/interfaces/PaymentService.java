package com.tech.payments.service.interfaces;

import com.tech.payments.pojo.CreateTxnRequest;
import com.tech.payments.pojo.InitiateTxnRequest;
import com.tech.payments.pojo.TxnResponse;

public interface PaymentService {
	
	public TxnResponse createTxn(CreateTxnRequest createTxnRequest);
	
	public TxnResponse initiateTxn(String id, InitiateTxnRequest initiateTxnRequest);

}
