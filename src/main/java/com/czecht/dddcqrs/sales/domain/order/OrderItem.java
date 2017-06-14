package com.czecht.dddcqrs.sales.domain.order;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.czecht.dddcqrs.ddd.sharedkernel.exceptions.DomainOperationException;
import com.czecht.dddcqrs.ddd.support.domain.BaseEntity;
import com.czecht.dddcqrs.sales.domain.product.Product;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.google.common.base.Preconditions.checkNotNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter(AccessLevel.NONE)
public class OrderItem extends BaseEntity {

	@ManyToOne
	private Product product;

	private int quantity;

	OrderItem(Product product, int quantity) {
		this.product = checkNotNull(product);
		this.setQuantity(quantity);
	}

	void changeQuantityBy(int change) {
		int changed = quantity + change;
		assertQuantity(quantity);
		this.quantity = changed;
	}

	private void setQuantity(int quantity) {
		assertQuantity(quantity);
		this.quantity = quantity;
	}

	private void assertQuantity(int quantity) {
		if(quantity <= 0) {
			throw new DomainOperationException(null, "change below 1");
		}
	}
}
