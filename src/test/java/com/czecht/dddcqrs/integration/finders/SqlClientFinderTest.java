package com.czecht.dddcqrs.integration.finders;

import org.junit.Test;

import com.czecht.dddcqrs.TestConstants;
import com.czecht.dddcqrs.builders.ClientDomainBuilder;
import com.czecht.dddcqrs.sales.domain.client.Client;

import static org.assertj.core.api.Assertions.assertThat;

public class SqlClientFinderTest extends AbstractFinderTest {

	@Test
	public void shouldFindClients() {
		// given
		Client client = new ClientDomainBuilder().withName("xxx").buildAndStore(getEntityManager());

		// when
		boolean result = client.canAfford(TestConstants.MONEY_10);

		// then
		assertThat(result).isTrue();
	}
}
