package com.czecht.dddcqrs.sales.application.commands.handlers;

import org.springframework.beans.factory.annotation.Autowired;

import com.czecht.dddcqrs.cqrs.annotations.CommandHandlerAnnotation;
import com.czecht.dddcqrs.cqrs.command.handler.CommandHandler;
import com.czecht.dddcqrs.ddd.sharedkernel.exceptions.DomainOperationException;
import com.czecht.dddcqrs.sales.application.commands.AddClientCommand;
import com.czecht.dddcqrs.sales.domain.client.Client;
import com.czecht.dddcqrs.sales.domain.client.ClientRepository;

@CommandHandlerAnnotation
public class AddClientCommandHandler implements CommandHandler<AddClientCommand, Void> {

	private ClientRepository clientRepository;

	@Autowired
	public AddClientCommandHandler(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public Void handle(AddClientCommand command) {
		boolean exists = clientRepository.exists(command.getClientId());
		if(exists) {
			throw new DomainOperationException(command.getClientId(), "Client already exists");
		}
		Client client = Client.create(command.getClientId(), command.getName());
		clientRepository.save(client);

		return null;
	}
}
