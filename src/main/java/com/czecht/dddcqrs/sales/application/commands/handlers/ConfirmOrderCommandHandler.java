package com.czecht.dddcqrs.sales.application.commands.handlers;

import org.springframework.beans.factory.annotation.Autowired;

import com.czecht.dddcqrs.cqrs.annotations.CommandHandlerAnnotation;
import com.czecht.dddcqrs.cqrs.command.handler.CommandHandler;
import com.czecht.dddcqrs.ddd.sharedkernel.exceptions.DomainOperationException;
import com.czecht.dddcqrs.sales.application.commands.ConfirmOrderCommand;
import com.czecht.dddcqrs.sales.domain.client.Client;
import com.czecht.dddcqrs.sales.domain.client.ClientRepository;
import com.czecht.dddcqrs.sales.domain.order.Order;
import com.czecht.dddcqrs.sales.domain.order.OrderRepository;

import static com.google.common.base.Preconditions.checkNotNull;

@CommandHandlerAnnotation
public class ConfirmOrderCommandHandler implements CommandHandler<ConfirmOrderCommand, Void> {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public Void handle(ConfirmOrderCommand command) {
		checkNotNull(command);

		Order order = orderRepository.load(command.getOrderId());
		if(order.isClosed()) {
			throw new DomainOperationException(order.getAggregateId(), "Order is already closed");
		}

		// przygotowanie oferty z uwzlednieniem rabatów

		Client client = clientRepository.load(order.getClientId());
		// client.canAfford(money) - sprawdzenie czy można klienta obciażyć

		// zapiasnie obiażenia klienta

		order.close();

		orderRepository.save(order);
		clientRepository.save(client);
		return null;
	}
}
