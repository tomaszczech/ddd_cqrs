package com.czecht.dddcqrs.shared.generators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.czecht.dddcqrs.ddd.annotations.domain.application.ApplicationService;

@ApplicationService
public class GeneratorService {

	public static final String DEFAULT_SEPARATOR = "";
	private static final int DEFAULT_INITIAL_VALUE = 0;

	@Autowired
	private SequenceGenerator sequenceGenerator;

	@Transactional
	public void initializeSequence(String name, String separator, int initialValue) {
		sequenceGenerator.initialize(name, separator, initialValue);
	}

	@Transactional
	public void initializeSequence(String name) {
		initializeSequence(name, DEFAULT_SEPARATOR, DEFAULT_INITIAL_VALUE);
	}

	@Transactional
	public void initializeDefaultSequences() {
		for(SequenceIdentifier defaultSequence : SequenceIdentifier.values()) {
			initializeSequence(defaultSequence.name());
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public SequenceValue nextSequence(SequenceIdentifier sequenceIdentifier) {
		return sequenceGenerator.next(sequenceIdentifier.name());
	}

}
