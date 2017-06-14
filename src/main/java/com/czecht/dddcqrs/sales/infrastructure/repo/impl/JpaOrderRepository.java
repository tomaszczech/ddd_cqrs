package com.czecht.dddcqrs.sales.infrastructure.repo.impl;

import com.czecht.dddcqrs.ddd.annotations.domain.application.DomainRepositoryImpl;
import com.czecht.dddcqrs.ddd.support.jpa.GenericJpaRepository;
import com.czecht.dddcqrs.sales.domain.order.Order;
import com.czecht.dddcqrs.sales.domain.order.OrderRepository;

@DomainRepositoryImpl
public class JpaOrderRepository extends GenericJpaRepository<Order> implements OrderRepository {

}
