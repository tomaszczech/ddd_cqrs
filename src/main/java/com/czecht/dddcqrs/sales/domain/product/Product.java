package com.czecht.dddcqrs.sales.domain.product;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import com.czecht.dddcqrs.ddd.annotations.domain.domain.AggregateRoot;
import com.czecht.dddcqrs.ddd.canonicalmodel.publishedlanguage.AggregateId;
import com.czecht.dddcqrs.ddd.support.domain.BaseAggregateRoot;

@Entity
@AggregateRoot
public class Product extends BaseAggregateRoot {

	private BigDecimal priceAmount;

	private String priceCurrency;

	@Transient
	private Money price;

	private String name;

	@Enumerated(EnumType.STRING)
	private ProductType productType;

	@SuppressWarnings("unused")
	private Product() {
	}

	Product(AggregateId aggregateId, Money price, String name, ProductType productType) {
		this.aggregateId = aggregateId;
		this.price = price;
		this.name = name;
		this.productType = productType;
	}

	public boolean isAvailabe() {
		return !isRemoved();//TODO dodać więcej reguł domenowych
	}

	public Money getPrice() {
		return Money.of(CurrencyUnit.getInstance(priceCurrency), priceAmount);
	}

	public String getName() {
		return name;
	}

	public ProductType getProductType() {
		return productType;
	}

	//	public ProductData generateSnapshot(){
	//		return new ProductData(getAggregateId(), price, name, productType, new Date());
	//	}

}
