package com.czecht.dddcqrs.shared.generators;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static com.google.common.base.Preconditions.checkState;
import static java.util.Objects.requireNonNull;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class Sequence {

	@Id
	@GeneratedValue
	@SuppressWarnings("PMD.UnusedPrivateField")
	private Long id;

	@Column(nullable = false, updatable = false)
	private String name;

	@Column(nullable = true, updatable = false)
	private String separator;

	private int sequence;

	Sequence() {
	}

	public Sequence(String name, String separator, int sequence) {
		this.name = requireNonNull(name);
		this.separator = requireNonNull(separator);

		checkState(sequence >= 0, "Initial sequence must be positive but was " + sequence);
		this.sequence = sequence;
	}

	public SequenceValue next() {
		sequence++;
		return new SequenceValue(name, separator, sequence);
	}
}
