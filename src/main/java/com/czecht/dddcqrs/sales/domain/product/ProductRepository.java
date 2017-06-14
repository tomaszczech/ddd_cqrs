package com.czecht.dddcqrs.sales.domain.product;

import org.springframework.stereotype.Repository;

import com.czecht.dddcqrs.ddd.canonicalmodel.publishedlanguage.AggregateId;

@Repository
public interface ProductRepository {

	Product load(AggregateId productId);

}
