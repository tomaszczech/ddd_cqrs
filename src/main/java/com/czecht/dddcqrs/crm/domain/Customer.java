package com.czecht.dddcqrs.crm.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.czecht.dddcqrs.ddd.annotations.domain.domain.AggregateRoot;
import com.czecht.dddcqrs.ddd.canonicalmodel.publishedlanguage.AggregateId;
import com.czecht.dddcqrs.ddd.support.domain.BaseAggregateRoot;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AggregateRoot
@Getter
@Setter(AccessLevel.NONE)
public class Customer extends BaseAggregateRoot {

	public enum CustomerActivity {
		ACTIVE, INACTINVE, IN_DEACTIVATION
	}

	private Customer(){}

	@Enumerated(EnumType.STRING)
	private CustomerActivity activity = CustomerActivity.ACTIVE;

	public Customer(AggregateId customerId, String name, Address address) {
		this.aggregateId = customerId;
		this.name = name;
		this.address = address;
	}

	private String name;

	private Address address;
}
