package com.czecht.dddcqrs.crm.application;

import org.springframework.beans.factory.annotation.Autowired;

import com.czecht.dddcqrs.crm.domain.Address;
import com.czecht.dddcqrs.crm.domain.Customer;
import com.czecht.dddcqrs.crm.domain.CustomerFactory;
import com.czecht.dddcqrs.crm.webui.NewCustomerDto;
import com.czecht.dddcqrs.ddd.annotations.domain.application.ApplicationService;
import com.czecht.dddcqrs.sales.infrastructure.repo.impl.JpaCustomerRepository;

import static com.google.common.base.Preconditions.checkNotNull;

@ApplicationService
public class CustomerService {

	@Autowired
	private JpaCustomerRepository customerRepository;

	@Autowired
	private CustomerFactory customerFactory;

	public void addCustomer(NewCustomerDto newCustomerDto) {
		// todo
		checkNotNull(newCustomerDto);
		Address address = new Address(newCustomerDto.getCity(),
				newCustomerDto.getStreet(),
				newCustomerDto.getPostCode());
		Customer customer = customerFactory.create(newCustomerDto.getName(), address);
		customerRepository.save(customer);
	}
}
