package com.czecht.dddcqrs.ddd.support.sql;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;

import com.czecht.dddcqrs.shared.domain.TimezoneConstants;

public abstract class AbstractSqlFinder {

	@PersistenceContext
	protected EntityManager entityManager;

	private static Properties dateTimeProperties = new Properties();

	static {
		dateTimeProperties.put(TimezoneConstants.DB_ZONE, TimezoneConstants.UTC);
		dateTimeProperties.put(TimezoneConstants.JAVA_ZONE, TimezoneConstants.JVM);
	}

	protected Session getSession() {
		return entityManager.unwrap(Session.class);
	}
}
