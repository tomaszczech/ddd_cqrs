package com.czecht.dddcqrs.sales.readmodel.impl;

import java.util.List;

import com.czecht.dddcqrs.ddd.annotations.domain.application.FinderImpl;
import com.czecht.dddcqrs.ddd.canonicalmodel.publishedlanguage.AggregateId;
import com.czecht.dddcqrs.ddd.support.sql.AbstractSqlFinder;
import com.czecht.dddcqrs.ddd.support.sql.AbstractSqlResultTransformer;
import com.czecht.dddcqrs.sales.readmodel.OrderDto;
import com.czecht.dddcqrs.sales.readmodel.OrderFinder;
import com.czecht.dddcqrs.sales.readmodel.OrderedProductDto;

@FinderImpl
public class SqlOrderFinder extends AbstractSqlFinder implements OrderFinder {

	@Override
	public List<OrderDto> findByClient(String clientId) {
		return getSession().createSQLQuery("select idValue from order where clientId := clientId ")
				.setParameter("clientId", clientId)
				.setResultTransformer(new OrderListResultTransformer())
				.list();
	}

	private class OrderListResultTransformer extends AbstractSqlResultTransformer<OrderDto> {

		@Override
		protected OrderDto transformObject() {
			OrderDto.OrderDtoBuilder builder = OrderDto.builder();
			builder.orderId(new AggregateId(getString("idValue")));
			List<OrderedProductDto> orderedProducts = getSession().createSQLQuery(
					"select order_item.quantity from order_item "
							+ "	inner join order on order.id_value=order_item.order "
							+ " inner join product on order_item.product=product.entity_id "
							+ " where order= :orderEntityId ")
					.setResultTransformer(new OrderedProductResultTransformer())
					.setParameter("orderEntityId", 1L)
					.list();

			builder.orderedProducts(orderedProducts);

			return builder.build();
		}

		@Override
		public List transformList(List list) {
			return list;
		}
	}

	private class OrderedProductResultTransformer extends AbstractSqlResultTransformer<OrderedProductDto> {

		@Override
		protected OrderedProductDto transformObject() {
			OrderedProductDto.OrderedProductDtoBuilder builder = OrderedProductDto.builder();
			builder.name(getString("name")).quantity(getInteger("quantity"));

			return builder.build();
		}

		@Override
		public List transformList(List list) {
			return list;
		}
	}

}
