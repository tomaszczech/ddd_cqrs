package com.czecht.dddcqrs.cqrs.command;


public interface Gate {

	Object dispatch(Object command);

}
