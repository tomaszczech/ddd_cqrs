package com.czecht.dddcqrs.cqrs.command.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.czecht.dddcqrs.cqrs.command.handler.CommandHandler;
import com.czecht.dddcqrs.cqrs.command.handler.HandlersProvider;

@Component
public class RunEnvironment {

	@Autowired
	private HandlersProvider handlersProvider;

	public Object run(Object command) {
		CommandHandler<Object, Object> handler = handlersProvider.getHandler(command);

		//You can add Your own capabilities here: dependency injection, security, transaction management, logging, profiling, spying, storing commands, etc

		Object result = handler.handle(command);

		//You can add Your own capabilities here

		return result;
	}

}
