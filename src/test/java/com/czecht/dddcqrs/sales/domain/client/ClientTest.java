package com.czecht.dddcqrs.sales.domain.client;

import org.junit.Test;

import com.czecht.dddcqrs.TestConstants;
import com.czecht.dddcqrs.builders.ClientDomainBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public class ClientTest {

	@Test
	public void shouldCanAfforClient() {
		// given
		Client client = new ClientDomainBuilder().withName("xxx").build();

		// when
		boolean result = client.canAfford(TestConstants.MONEY_10);

		// then
		assertThat(result).isTrue();
	}

}
