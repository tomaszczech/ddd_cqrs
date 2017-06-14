package com.czecht.dddcqrs.shared.configuration.application;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.czecht.dddcqrs.ddd.annotations.domain.application.ApplicationService;
import com.czecht.dddcqrs.shared.generators.GeneratorService;

@ApplicationService
public class ConfigurationService {

	@Autowired
	private GeneratorService generatorService;

	@PostConstruct
	public void setUp(){
		generatorService.initializeDefaultSequences();
	}
}
