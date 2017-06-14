package com.czecht.dddcqrs.sales.domain.order;

import org.springframework.stereotype.Repository;

import com.czecht.dddcqrs.ddd.canonicalmodel.publishedlanguage.AggregateId;

@Repository
public interface OrderRepository {

	void save(Order order);

	Order load(AggregateId orderId);

}
