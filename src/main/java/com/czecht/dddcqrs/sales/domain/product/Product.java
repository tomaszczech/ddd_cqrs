package com.czecht.dddcqrs.sales.domain.product;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.joda.money.Money;

import com.czecht.dddcqrs.ddd.annotations.domain.domain.AggregateRoot;
import com.czecht.dddcqrs.ddd.canonicalmodel.publishedlanguage.AggregateId;
import com.czecht.dddcqrs.ddd.support.domain.BaseAggregateRoot;

@Entity
@AggregateRoot
public class Product extends BaseAggregateRoot {

	@Embedded
	private Money price;

	private String name;

	@Enumerated(EnumType.STRING)
	private ProductType productType;

	@SuppressWarnings("unused")
	private Product(){}

	Product(AggregateId aggregateId, Money price, String name, ProductType productType){
		this.aggregateId = aggregateId;
		this.price = price;
		this.name = name;
		this.productType = productType;
	}

	public boolean isAvailabe(){
		return ! isRemoved();//TODO dodać więcej reguł domenowych
	}

	public Money getPrice() {
		return price;
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
