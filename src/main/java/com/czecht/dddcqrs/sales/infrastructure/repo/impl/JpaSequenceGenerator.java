package com.czecht.dddcqrs.sales.infrastructure.repo.impl;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.czecht.dddcqrs.shared.generators.Sequence;
import com.czecht.dddcqrs.shared.generators.SequenceGenerator;
import com.czecht.dddcqrs.shared.generators.SequenceValue;

import static java.util.Objects.requireNonNull;

@Repository
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class JpaSequenceGenerator implements SequenceGenerator {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void initialize(String name, String separator, int initialSequence) {
		Sequence sequence = new Sequence(name, separator, initialSequence);
		entityManager.persist(sequence);
		entityManager.flush();
	}

	@Override
	public boolean isInitialized(String name) {
		TypedQuery<Long> query = entityManager.createQuery(
				"SELECT count(s) FROM Sequence s WHERE  s.name = :name", Long.class);
		query.setParameter("name", name);

		return query.getSingleResult() > 0;
	}

	@Override
	public SequenceValue next(String name) {
		requireNonNull(name);

		TypedQuery<Sequence> query = entityManager.createQuery(
				"SELECT s FROM Sequence s WHERE s.name = :name", Sequence.class);
		query.setParameter("name", name);
		Sequence sequence = query.getSingleResult();

		entityManager.refresh(sequence, LockModeType.PESSIMISTIC_WRITE);
		SequenceValue next = sequence.next();
		entityManager.flush();

		return next;
	}
}
