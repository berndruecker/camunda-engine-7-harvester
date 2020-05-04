package io.berndruecker.camunda.harvest.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.berndruecker.camunda.harvest.domain.CamundaEngineInfo;
import io.berndruecker.camunda.harvest.repository.CamundaEngineRepository;

@Component
public class DefaultCamundaEnginePopulation {

	@Autowired
	private DefaultCamundaEngineConfig defaultConfig;
	
	@Autowired
	private CamundaEngineRepository repository;
	
	@PostConstruct
	public void populateDefaults() {
		for (CamundaEngineInfo engine : defaultConfig.getEngines()) {
			if (!repository.existsByName(engine.getName())) {
				repository.save(engine);
			}
		}
	}
}
