package com.czecht.dddcqrs.builders;

import javax.persistence.EntityManager;

import com.czecht.dddcqrs.TestConstants;
import com.czecht.dddcqrs.sales.domain.client.Client;

public class ClientDomainBuilder implements DomainBuilder<Client> {

	private Client.ClientBuilder builder = Client.builder();

	public ClientDomainBuilder() {
		builder.name(TestConstants.ANY_NAME);
	}

	public ClientDomainBuilder withName(String name) {
		builder.name(name);
		return this;
	}

	@Override
	public Client build() {
		return builder.build();
	}

	@Override
	public Client buildAndStore(EntityManager entityManager) {
		Client client = build();
		entityManager.persist(client);
		return client;
	}
}
