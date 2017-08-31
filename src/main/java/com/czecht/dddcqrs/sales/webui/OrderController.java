package com.czecht.dddcqrs.sales.webui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.czecht.dddcqrs.cqrs.command.Gate;
import com.czecht.dddcqrs.sales.application.commands.AddOrderCommand;
import com.czecht.dddcqrs.sales.readmodel.OrderDto;
import com.czecht.dddcqrs.sales.readmodel.OrderFinder;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private OrderFinder orderFinder;

	private Gate gate;

	@Autowired
	public OrderController(OrderFinder orderFinder, Gate gate) {
		this.orderFinder = orderFinder;
		this.gate = gate;
	}

	@RequestMapping(method = RequestMethod.POST)
	public void addNewOrder(@RequestBody AddOrderCommand addOrderCommand) {
		gate.dispatch(addOrderCommand);
		//todo redirect to list page
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<OrderDto> orderList(@RequestParam(name = "clientId") String clientId) {
		return orderFinder.findByClient(clientId);
	}
}
