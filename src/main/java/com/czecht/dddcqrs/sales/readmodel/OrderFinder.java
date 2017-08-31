package com.czecht.dddcqrs.sales.readmodel;

import java.util.List;

import com.czecht.dddcqrs.ddd.annotations.domain.application.Finder;
import com.czecht.dddcqrs.ddd.canonicalmodel.publishedlanguage.AggregateId;

@Finder
public interface OrderFinder {

	List<OrderDto> findByClient(String clientId);

}
