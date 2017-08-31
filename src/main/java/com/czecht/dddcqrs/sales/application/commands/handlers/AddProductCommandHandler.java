package com.czecht.dddcqrs.sales.application.commands.handlers;

import org.springframework.beans.factory.annotation.Autowired;

import com.czecht.dddcqrs.cqrs.annotations.CommandHandlerAnnotation;
import com.czecht.dddcqrs.cqrs.command.handler.CommandHandler;
import com.czecht.dddcqrs.ddd.canonicalmodel.publishedlanguage.AggregateId;
import com.czecht.dddcqrs.sales.application.commands.AddOrderCommand;
import com.czecht.dddcqrs.sales.application.commands.AddProductCommand;
import com.czecht.dddcqrs.sales.domain.order.Order;
import com.czecht.dddcqrs.sales.domain.order.OrderRepository;
import com.czecht.dddcqrs.sales.domain.product.Product;
import com.czecht.dddcqrs.sales.domain.product.ProductRepository;

import static com.google.common.base.Preconditions.checkNotNull;

@CommandHandlerAnnotation
public class AddProductCommandHandler implements CommandHandler<AddProductCommand, Void> {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Void handle(AddProductCommand command) {
		checkNotNull(command);

		Order order = orderRepository.load(command.getOrderId());

		Product product = productRepository.load(command.getProductId());

		if(!product.isAvailabe()) {
			//todo zaproponowac podobny produkt, ktory jest dostepny
		}

		order.add(product, command.getQuantity());

		orderRepository.save(order);

		return null;
	}

}
