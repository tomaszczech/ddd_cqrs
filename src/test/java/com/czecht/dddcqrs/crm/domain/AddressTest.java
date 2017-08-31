package com.czecht.dddcqrs.crm.domain;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import static org.assertj.core.api.Assertions.assertThat;

@Test
public class AddressTest {

	@DataProvider
	public Object[][] incorrectValues() {
		return new Object[][] {
				{ "", "ss", "33" }, { "cc", "", "44"}, { "cc", "ss", ""},
				{ null, "ss", "33" }, { "cc", null, "44"}, { "cc", "ss", null}
		};
	}


	@Test(expectedExceptions = IllegalArgumentException.class, dataProvider = "incorrectValues")
	public void shouldThrowExceptionForIllegalArguments(String city, String street, String postCode) {
		// given

		// when
		Address.of(city, street, postCode);

		// then
		// throw exception
	}

	@Test
	public void shouldGetMailAddress(){
		// given
		Address address = Address.of("Rzeszow", "Podkarpacka", "55-555");

		// when
		String result = address.getMailAddress();

		// then
		assertThat(result).isNotNull().isNotEmpty();
		assertThat(result).isEqualTo("Rzeszow\nPodkarpacka 55-555");
	}

}
