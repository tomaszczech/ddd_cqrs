package com.czecht.dddcqrs.sales.domain.client;

import javax.persistence.Entity;

import org.joda.money.Money;

import com.czecht.dddcqrs.ddd.annotations.domain.domain.AggregateRoot;
import com.czecht.dddcqrs.ddd.canonicalmodel.publishedlanguage.AggregateId;
import com.czecht.dddcqrs.ddd.support.domain.BaseAggregateRoot;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import static com.google.common.base.Preconditions.checkNotNull;

@Builder
@Entity
@AggregateRoot
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Client extends BaseAggregateRoot {

	private String name;

	private Client(AggregateId id, String name) {
		this.aggregateId = checkNotNull(id);
		this.name = checkNotNull(name);
	}

	public boolean canAfford(Money amount) {
		return true;//TODO np sprawdzenie czy klient przekroczył limit zamówień w miesiacu
	}

	public static Client create(AggregateId aggregateId, String name) {
		return new Client(aggregateId, name);
	}
}
