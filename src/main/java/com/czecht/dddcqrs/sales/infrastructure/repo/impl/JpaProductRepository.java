package com.czecht.dddcqrs.sales.infrastructure.repo.impl;

import com.czecht.dddcqrs.ddd.annotations.domain.application.DomainRepositoryImpl;
import com.czecht.dddcqrs.ddd.support.jpa.GenericJpaRepository;
import com.czecht.dddcqrs.sales.domain.product.Product;
import com.czecht.dddcqrs.sales.domain.product.ProductRepository;

@DomainRepositoryImpl
public class JpaProductRepository extends GenericJpaRepository<Product> implements ProductRepository {

}
