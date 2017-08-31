package com.czecht.dddcqrs.sales.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.czecht.dddcqrs.ddd.annotations.domain.application.ApplicationService;
import com.czecht.dddcqrs.ddd.canonicalmodel.publishedlanguage.AggregateId;
import com.czecht.dddcqrs.sales.domain.client.Client;
import com.czecht.dddcqrs.sales.domain.client.ClientRepository;
import com.czecht.dddcqrs.sales.domain.order.Order;
import com.czecht.dddcqrs.sales.domain.order.OrderFactory;
import com.czecht.dddcqrs.sales.domain.order.OrderRepository;
import com.czecht.dddcqrs.sales.domain.product.Product;
import com.czecht.dddcqrs.sales.domain.product.ProductRepository;

@ApplicationService
public class OrderingServiceImpl {

	private OrderFactory orderFactory;

	private OrderRepository orderRepository;

	private ProductRepository productRepository;

	private ClientRepository clientRepository;

	@Autowired
	public OrderingServiceImpl(OrderFactory orderFactory, OrderRepository orderRepository,
			ProductRepository productRepository, ClientRepository clientRepository) {
		this.orderFactory = orderFactory;
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
		this.clientRepository = clientRepository;
	}

	@Transactional
	//@PreAuthorize("hasPermission(#clientId, 'ORDER_CREATE')")
	public AggregateId createOrder(AggregateId clientId) {
		Client client = clientRepository.load(clientId);
		Order order = orderFactory.create(client);
		orderRepository.save(order);
		return order.getAggregateId();
	}


	@Transactional
	public void addProduct(AggregateId clientId, AggregateId orderId, AggregateId productId,
			int quantity) {
		Order order = orderRepository.load(orderId);

		Product product = productRepository.load(productId);

		if (! product.isAvailabe()){
			Client client = clientRepository.load(clientId);
//			product = suggestionService.suggestEquivalent(product, client);
		}

		order.add(product, quantity);

		orderRepository.save(order);
	}
}
