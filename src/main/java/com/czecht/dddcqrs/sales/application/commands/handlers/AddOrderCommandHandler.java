package com.czecht.dddcqrs.sales.application.commands.handlers;

import org.springframework.beans.factory.annotation.Autowired;

import com.czecht.dddcqrs.cqrs.annotations.CommandHandlerAnnotation;
import com.czecht.dddcqrs.cqrs.command.handler.CommandHandler;
import com.czecht.dddcqrs.ddd.canonicalmodel.publishedlanguage.AggregateId;
import com.czecht.dddcqrs.sales.application.commands.AddOrderCommand;
import com.czecht.dddcqrs.sales.domain.client.Client;
import com.czecht.dddcqrs.sales.domain.client.ClientRepository;
import com.czecht.dddcqrs.sales.domain.order.Order;
import com.czecht.dddcqrs.sales.domain.order.OrderFactory;
import com.czecht.dddcqrs.sales.domain.order.OrderRepository;

import static com.google.common.base.Preconditions.checkNotNull;

@CommandHandlerAnnotation
public class AddOrderCommandHandler implements CommandHandler<AddOrderCommand, AggregateId> {

	@Autowired
	private OrderFactory orderFactory;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public AggregateId handle(AddOrderCommand command) {
		checkNotNull(command);
		Client client = clientRepository.load(command.getClientId());
		// todo check if client status, activity

		Order order = orderFactory.create(client);

		orderRepository.save(order);

		return order.getAggregateId();
	}
}
