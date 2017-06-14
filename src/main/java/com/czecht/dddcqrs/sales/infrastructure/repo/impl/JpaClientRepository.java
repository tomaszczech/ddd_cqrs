/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.czecht.dddcqrs.sales.infrastructure.repo.impl;

import com.czecht.dddcqrs.ddd.annotations.domain.application.DomainRepositoryImpl;
import com.czecht.dddcqrs.ddd.support.jpa.GenericJpaRepository;
import com.czecht.dddcqrs.sales.domain.client.Client;
import com.czecht.dddcqrs.sales.domain.client.ClientRepository;

@DomainRepositoryImpl
public class JpaClientRepository extends GenericJpaRepository<Client> implements ClientRepository {

}
