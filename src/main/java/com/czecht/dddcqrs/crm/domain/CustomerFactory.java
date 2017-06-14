package com.czecht.dddcqrs.crm.domain;

import org.springframework.beans.factory.annotation.Autowired;

import com.czecht.dddcqrs.ddd.annotations.domain.domain.DomainFactory;
import com.czecht.dddcqrs.ddd.canonicalmodel.publishedlanguage.AggregateId;
import com.czecht.dddcqrs.shared.generators.GeneratorService;
import com.czecht.dddcqrs.shared.generators.SequenceIdentifier;

@DomainFactory
public class CustomerFactory {

	@Autowired
	private GeneratorService generatorService;

	public Customer create(String name, Address address) {
		AggregateId aggregateId = createCustomerId();
		return new Customer(aggregateId, name, address);
	}

	private AggregateId createCustomerId() {
		return new AggregateId(generatorService.nextSequence(SequenceIdentifier.CUST).getValue());
	}
}
