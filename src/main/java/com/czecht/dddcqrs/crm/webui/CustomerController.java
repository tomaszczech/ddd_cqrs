package com.czecht.dddcqrs.crm.webui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.czecht.dddcqrs.crm.application.CustomerService;
import com.czecht.dddcqrs.crm.domain.Customer;
import com.czecht.dddcqrs.sales.infrastructure.repo.impl.JpaCustomerRepository;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private JpaCustomerRepository customerRepository;

	@Autowired
	private CustomerService customerService;

	@RequestMapping(method = RequestMethod.POST)
	public void addCustomer(@RequestBody NewCustomerDto newCustomerDto) {
		customerService.addCustomer(newCustomerDto);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Customer> customersList(){
		return customerRepository.findAll();
	}
}
