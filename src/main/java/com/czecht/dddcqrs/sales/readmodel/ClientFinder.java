package com.czecht.dddcqrs.sales.readmodel;

import java.util.List;

import com.czecht.dddcqrs.ddd.annotations.domain.application.Finder;

@Finder
public interface ClientFinder {

	List<ClientDto> loadAll();

}
