package com.czecht.dddcqrs.canonical.events;

import java.io.Serializable;

import com.czecht.dddcqrs.ddd.annotations.domain.event.Event;
import com.czecht.dddcqrs.ddd.canonicalmodel.publishedlanguage.AggregateId;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Event
public class OrderSubmittedEvent implements Serializable {

	private AggregateId orderId;

}
