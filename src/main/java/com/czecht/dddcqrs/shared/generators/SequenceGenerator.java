package com.czecht.dddcqrs.shared.generators;


public interface SequenceGenerator {

	void initialize(String name, String separator, int initialSequence);

	boolean isInitialized(String name);
	
	SequenceValue next(String name);
}
