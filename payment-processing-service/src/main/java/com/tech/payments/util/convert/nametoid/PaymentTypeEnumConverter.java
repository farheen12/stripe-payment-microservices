package com.tech.payments.util.convert.nametoid;

import org.modelmapper.AbstractConverter;

import com.tech.payments.constant.PaymentTypeEnum;

public class PaymentTypeEnumConverter extends AbstractConverter<String, Integer> {
    @Override
    protected Integer convert(String source) {
        return PaymentTypeEnum.fromName(source).getId();
    }
}
