package com.czecht.dddcqrs.sales.infrastructure.repo.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.czecht.dddcqrs.crm.domain.Customer;
import com.czecht.dddcqrs.ddd.canonicalmodel.publishedlanguage.AggregateId;

@Repository
public interface JpaCustomerRepository extends JpaRepository<Customer, AggregateId> {

}
