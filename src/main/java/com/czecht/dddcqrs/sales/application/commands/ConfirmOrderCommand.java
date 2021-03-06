package com.czecht.dddcqrs.sales.application.commands;

import com.czecht.dddcqrs.cqrs.annotations.Command;
import com.czecht.dddcqrs.ddd.canonicalmodel.publishedlanguage.AggregateId;

import lombok.Data;

@Data
@Command
public class ConfirmOrderCommand {

	private AggregateId orderId;
}
