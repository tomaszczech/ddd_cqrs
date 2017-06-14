package com.czecht.dddcqrs.cqrs.command.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.czecht.dddcqrs.cqrs.command.Gate;

@Component
public class StandardGate implements Gate {

	private RunEnvironment runEnvironment;

	@Autowired
	public StandardGate(RunEnvironment runEnvironment) {
		this.runEnvironment = runEnvironment;
	}

	@Override
	public Object dispatch(Object command) {
		//todo np dodanie do historii zdarzeń

		return runEnvironment.run(command);
	}
}
