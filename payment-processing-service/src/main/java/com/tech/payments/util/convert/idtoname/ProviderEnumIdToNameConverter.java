package com.tech.payments.util.convert.idtoname;

import org.modelmapper.AbstractConverter;

import com.tech.payments.constant.ProviderEnum;

public class ProviderEnumIdToNameConverter extends AbstractConverter<Integer, String> {
    @Override
    protected String convert(Integer source) {
        return ProviderEnum.fromId(source).getName();
    }
}
