package com.czecht.dddcqrs.sales.readmodel;

import java.util.ArrayList;
import java.util.List;

import com.czecht.dddcqrs.ddd.canonicalmodel.publishedlanguage.AggregateId;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDto {

	private AggregateId orderId;
	private List<OrderedProductDto> orderedProducts = new ArrayList<OrderedProductDto>();

}
