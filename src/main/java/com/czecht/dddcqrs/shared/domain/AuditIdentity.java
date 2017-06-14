package com.czecht.dddcqrs.shared.domain;

import com.czecht.dddcqrs.ddd.annotations.domain.application.ValueObject;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@ValueObject
public class AuditIdentity {

	private static final long serialVersionUID = 1L;

	public static final AuditIdentity NOT_AVAILABLE = new AuditIdentity("N/A");

	public static final AuditIdentity SYSTEM = new AuditIdentity("SYSTEM");

	public static final String IDENTIFIER_PROPERTY_NAME = "identifier";

	public static final int IDENTIFIER_COLUMN_LENGTH = 20;

	private String identifier;

	protected AuditIdentity() {
	}

	public AuditIdentity(String identifier) {
		this.identifier = checkNotNull(identifier);

		checkArgument(identifier.length() <= IDENTIFIER_COLUMN_LENGTH);
	}

	public String getIdentifier() {
		return identifier;
	}

}
