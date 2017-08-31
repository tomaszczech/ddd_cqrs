package com.czecht.dddcqrs.sales.application.listeners;

import com.czecht.dddcqrs.canonical.events.OrderSubmittedEvent;
import com.czecht.dddcqrs.ddd.annotations.domain.event.EventListener;
import com.czecht.dddcqrs.ddd.annotations.domain.event.EventListeners;

@EventListeners
public class InvoiceListener {

	@EventListener
	public void handle(OrderSubmittedEvent event) {

		Purchase purchase = purchaseRepository.load(event.getOrderId());

		Client client = clientRepository.load(purchase.getClientData().getAggregateId());
		InvoiceRequest request = invoiceRequestFactory.create(client, purchase);
		Invoice invoice = bookKeeper.issuance(request, taxAdvisor.suggestBestTax(client));

		invoiceRepository.save(invoice);
	}
}
