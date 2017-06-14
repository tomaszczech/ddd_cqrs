package com.czecht.dddcqrs.sales.domain.order;

import com.czecht.dddcqrs.ddd.annotations.domain.domain.DomainFactory;
import com.czecht.dddcqrs.ddd.canonicalmodel.publishedlanguage.AggregateId;

@DomainFactory
public class OrderFactory {

	public Order create(AggregateId clientId) {
		return new Order(clientId);
	}
}
