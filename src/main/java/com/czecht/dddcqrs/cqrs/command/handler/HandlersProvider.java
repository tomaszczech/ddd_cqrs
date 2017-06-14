package com.czecht.dddcqrs.cqrs.command.handler;

public interface HandlersProvider {

	CommandHandler<Object, Object> getHandler(Object command);

}
