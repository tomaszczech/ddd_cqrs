package com.czecht.dddcqrs.sales.domain.order;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import com.czecht.dddcqrs.ddd.annotations.domain.application.Invariant;
import com.czecht.dddcqrs.ddd.annotations.domain.domain.AggregateRoot;
import com.czecht.dddcqrs.ddd.canonicalmodel.publishedlanguage.AggregateId;
import com.czecht.dddcqrs.ddd.support.domain.BaseAggregateRoot;
import com.czecht.dddcqrs.sales.domain.product.Product;
import com.czecht.dddcqrs.shared.domain.Audit;

import lombok.Getter;

import static com.google.common.base.Preconditions.checkNotNull;

@Entity
@Table(name = "order_basket")
@AggregateRoot
public class Order extends BaseAggregateRoot {

	public enum OrderStatus { OPENED, CLOSED, INACTIVE }

	@Enumerated(EnumType.STRING)
	private OrderStatus status = OrderStatus.OPENED;

	@Getter
	@Embedded @AttributeOverrides({
				@AttributeOverride(name = "aggregateId", column = @Column(name = "clientId", nullable = false))})
	private AggregateId clientId;

	@Embedded
	private Audit audit = Audit.createDefault();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "order")
	@Fetch(FetchMode.JOIN)
	private List<OrderItem> items;

	public Order(AggregateId clientId) {
		this.clientId = checkNotNull(clientId);
	}

	@Invariant({"closed"})
	public void close(){
		if (isClosed())
			domainError("Reservation is already closed");
		status = OrderStatus.CLOSED;
	}

	@Invariant({ "closed", "duplicates" })
	public void add(Product product, int quantity) {
		if(isClosed()) {
			domainError("Order already closed");
		}
		if(!product.isAvailabe()) {
			domainError("Product is no longer available");
		}

		if(contains(product)) {
			increase(product, quantity);
		} else {
			addNew(product, quantity);
		}
	}

	private void increase(Product product, int quantity) {
		for(OrderItem item : items) {
			if(item.getProduct().equals(product)) {
				item.changeQuantityBy(quantity);
				break;
			}
		}
	}

	private void addNew(Product product, int quantity) {
		OrderItem item = new OrderItem(product, quantity);
		items.add(item);
	}

	public boolean contains(Product product) {
		for(OrderItem item : items) {
			if(item.getProduct().equals(product)) {
				return true;
			}
		}
		return false;
	}

	public boolean isClosed() {
		return status.equals(OrderStatus.CLOSED);
	}

	public Money calculateTotalCost() {
		Money total = Money.zero(CurrencyUnit.EUR);
		for(OrderItem item : items) {
			total.plus(item.getProduct().getPrice());
		}

		return total;
	}

	private Money calculateItemCost(OrderItem item) {
		return item.getProduct().getPrice().multipliedBy(item.getQuantity());
	}
}
