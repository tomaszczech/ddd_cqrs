package com.czecht.dddcqrs.sales.readmodel;

import java.io.Serializable;

import org.joda.money.Money;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderedProductDto implements Serializable {

	private String name;

	private Money price;

	private int quantity;
}
