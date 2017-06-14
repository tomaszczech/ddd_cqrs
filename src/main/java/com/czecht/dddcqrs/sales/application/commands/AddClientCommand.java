package com.czecht.dddcqrs.sales.application.commands;

import java.io.Serializable;

import com.czecht.dddcqrs.cqrs.annotations.Command;
import com.czecht.dddcqrs.ddd.canonicalmodel.publishedlanguage.AggregateId;

import lombok.Data;

@Command
@Data
public class AddClientCommand implements Serializable {

	private final AggregateId clientId;

	private final String name;
}
