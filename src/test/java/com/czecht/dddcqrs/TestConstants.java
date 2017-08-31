package com.czecht.dddcqrs;

import java.math.BigDecimal;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

/**
 * Created by czecht on 2017-06-21.
 */
public interface TestConstants {

	String ANY_NAME = "ANY_NAME";

	CurrencyUnit ANY_CURRENCY_UNIT = CurrencyUnit.EUR;

	Money MONEY_10 = Money.of(ANY_CURRENCY_UNIT, new BigDecimal("10"));
	Money MONEY_20 = Money.of(ANY_CURRENCY_UNIT, new BigDecimal("20"));
	Money MONEY_30 = Money.of(ANY_CURRENCY_UNIT, new BigDecimal("30"));
	Money MONEY_40 = Money.of(ANY_CURRENCY_UNIT, new BigDecimal("40"));
	Money MONEY_50 = Money.of(ANY_CURRENCY_UNIT, new BigDecimal("50"));
	Money MONEY_60 = Money.of(ANY_CURRENCY_UNIT, new BigDecimal("60"));
	Money MONEY_70 = Money.of(ANY_CURRENCY_UNIT, new BigDecimal("70"));
	Money MONEY_80 = Money.of(ANY_CURRENCY_UNIT, new BigDecimal("80"));
	Money MONEY_90 = Money.of(ANY_CURRENCY_UNIT, new BigDecimal("90"));
	Money MONEY_100 = Money.of(ANY_CURRENCY_UNIT, new BigDecimal("100"));
}
