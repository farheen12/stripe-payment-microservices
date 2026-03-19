package com.tech.payments.service.impl.processor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.tech.payments.dao.interfaces.TransactionDao;
import com.tech.payments.dto.TransactionDTO;
import com.tech.payments.entity.TransactionEntity;
import com.tech.payments.service.helper.PaymentProcessorHelper;
import com.tech.payments.service.interfaces.TxnStatusProcessor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class FailedStatusProcessor implements TxnStatusProcessor {
	
	private final TransactionDao transactionDao;
	
	private final ModelMapper modelMapper;
	
	private final PaymentProcessorHelper paymentProcessorHelper;

	@Override
	public TransactionDTO processStatus(TransactionDTO txnDto) {
		log.info("Processing FAILED status for txnDto: {}", txnDto);
		
		if(paymentProcessorHelper.isTxnInFinalState(txnDto)) {
			log.warn("Transaction is already in a final state. No update performed for txnReference: {}",
					txnDto.getTxnReference());
			return txnDto;
		}
		
		TransactionEntity txnEntity = modelMapper
				.map(txnDto, TransactionEntity.class);
		log.info("Mapped txnEntity: {}", txnEntity);
		
		transactionDao.updateTransactionStatusDetailsByTxnReference(
				txnEntity);
		
		log.info("Updated transaction status successfully for txnReference: {}", 
				txnDto.getTxnReference());
		
		return txnDto;
	}
	
}
