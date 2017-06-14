package com.czecht.dddcqrs.ddd.support.sql;

import java.util.Properties;

import javax.persistence.EntityManager;

import org.hibernate.Session;

import com.czecht.dddcqrs.shared.domain.TimezoneConstants;

public class AbstractSqlFinder {

	protected final EntityManager entityManager;

	private static Properties dateTimeProperties = new Properties();

	static {
		dateTimeProperties.put(TimezoneConstants.DB_ZONE, TimezoneConstants.UTC);
		dateTimeProperties.put(TimezoneConstants.JAVA_ZONE, TimezoneConstants.JVM);
	}

	public AbstractSqlFinder(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	protected Session getSession() {
		return entityManager.unwrap(Session.class);
	}
}
