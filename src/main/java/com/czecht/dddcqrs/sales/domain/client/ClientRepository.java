package com.czecht.dddcqrs.sales.domain.client;

import org.springframework.stereotype.Repository;

import com.czecht.dddcqrs.ddd.canonicalmodel.publishedlanguage.AggregateId;

@Repository
public interface ClientRepository {

	void save(Client order);

	Client load(AggregateId clientId);

	boolean exists(AggregateId aggregateId);
}
