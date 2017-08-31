package com.czecht.dddcqrs.sales.readmodel.impl;

import java.util.List;

import org.hibernate.transform.Transformers;

import com.czecht.dddcqrs.ddd.annotations.domain.application.FinderImpl;
import com.czecht.dddcqrs.ddd.support.sql.AbstractSqlFinder;
import com.czecht.dddcqrs.sales.readmodel.ClientDto;
import com.czecht.dddcqrs.sales.readmodel.ClientFinder;

@FinderImpl
public class SqlClientFinder extends AbstractSqlFinder implements ClientFinder {

	@Override
	public List<ClientDto> loadAll() {
		return getSession().createSQLQuery("select name, aggregate_id as clientId from Client ")
				.addScalar("name")
				.addScalar("clientId")
				.setResultTransformer(Transformers.aliasToBean(ClientDto.class))
				.list();
	}
}
