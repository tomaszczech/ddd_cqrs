package com.czecht.dddcqrs.crm.webui;

import java.io.Serializable;

import lombok.Data;

@Data
public class NewCustomerDto implements Serializable {

	private String name;
	private String city;
	private String street;
	private String postCode;
}
