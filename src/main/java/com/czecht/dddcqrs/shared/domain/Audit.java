package com.czecht.dddcqrs.shared.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.czecht.dddcqrs.ddd.annotations.domain.application.ValueObject;

import static com.google.common.base.Preconditions.checkNotNull;

@ValueObject
public class Audit {

	private static final long serialVersionUID = 1L;

	@Column
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime", parameters = {
			@Parameter(name = "databaseZone", value = "UTC"), @Parameter(name = "javaZone", value = "jvm") })
	private DateTime creationDate;

	@Column
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime", parameters = {
			@Parameter(name = "databaseZone", value = "UTC"), @Parameter(name = "javaZone", value = "jvm") })
	private DateTime modificationDate;

	@Embedded
	@AttributeOverride(name = AuditIdentity.IDENTIFIER_PROPERTY_NAME, column = @Column(name = "creator", nullable = false, length = AuditIdentity.IDENTIFIER_COLUMN_LENGTH))
	private AuditIdentity creator;

	@Embedded
	@AttributeOverride(name = AuditIdentity.IDENTIFIER_PROPERTY_NAME, column = @Column(name = "modifier", nullable = false, length = AuditIdentity.IDENTIFIER_COLUMN_LENGTH))
	private AuditIdentity modifier;

	protected Audit() {
	}

	public static Audit createDefault() {
		return new Audit();
	}

	public Audit(DateTime creationDate, DateTime modificationDate, AuditIdentity creator, AuditIdentity modifier) {
		this.creationDate = checkNotNull(creationDate);
		this.modificationDate = checkNotNull(modificationDate);
		this.creator = checkNotNull(creator);
		this.modifier = checkNotNull(modifier);
	}

	public Audit update(DateTime now, AuditIdentity modifier) {
		if (isNew()) {
			return new Audit(now, now, modifier, modifier);
		}

		return new Audit(this.creationDate, now, this.creator, modifier);
	}

	public DateTime getCreationDate() {
		return creationDate;
	}

	public DateTime getModificationDate() {
		return modificationDate;
	}

	public AuditIdentity getCreator() {
		return creator;
	}

	public AuditIdentity getModifier() {
		return modifier;
	}

	private boolean isNew() {
		return creationDate == null && creator == null;
	}

}
