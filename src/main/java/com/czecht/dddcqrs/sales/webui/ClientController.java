package com.czecht.dddcqrs.sales.webui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.czecht.dddcqrs.cqrs.command.Gate;
import com.czecht.dddcqrs.ddd.canonicalmodel.publishedlanguage.AggregateId;
import com.czecht.dddcqrs.sales.application.commands.AddClientCommand;
import com.czecht.dddcqrs.sales.readmodel.ClientDto;
import com.czecht.dddcqrs.sales.readmodel.ClientFinder;

@RestController
@RequestMapping("/clients")
public class ClientController {

	private ClientFinder clientFinder;

	private Gate gate;

	@Autowired
	public ClientController(ClientFinder clientFinder, Gate gate) {
		this.clientFinder = clientFinder;
		this.gate = gate;
	}

	@RequestMapping(method = RequestMethod.POST)
	public void addClient(@RequestParam(value = "id") String id, @RequestParam(value = "name") String name) {
		gate.dispatch(new AddClientCommand(new AggregateId(id), name));
		//todo: przekierowanie na liste wszystkich klientów
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<ClientDto> clientList() {
		return clientFinder.loadAll();
	}
}
