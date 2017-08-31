package com.czecht.dddcqrs.sales.domain.order;

import com.czecht.dddcqrs.ddd.annotations.domain.domain.DomainFactory;
import com.czecht.dddcqrs.ddd.sharedkernel.exceptions.DomainOperationException;
import com.czecht.dddcqrs.sales.domain.client.Client;

@DomainFactory
public class OrderFactory {

	public Order create(Client client) {
		if(!canOrderForClient(client)) {
			throw new DomainOperationException(client.getAggregateId(), "Client can not create order.");
		}

		return new Order(client.getAggregateId());
	}

	private boolean canOrderForClient(Client client) {
		return true; // sprawdzić różne reguły biznesowe np. czy klient jest aktywny, jaki ma rabaty itd
	}
}
