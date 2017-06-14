package com.czecht.dddcqrs.cqrs.command.handler;

public interface CommandHandler<C, R> {

	R handle(C command);

}
