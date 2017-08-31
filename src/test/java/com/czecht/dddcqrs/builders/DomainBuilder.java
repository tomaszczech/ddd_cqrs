package com.czecht.dddcqrs.builders;

import javax.persistence.EntityManager;

public interface DomainBuilder<T> {
	T build();
	T buildAndStore(EntityManager entityManager);
}
