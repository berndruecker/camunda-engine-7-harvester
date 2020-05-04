package io.berndruecker.camunda.harvest.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import io.berndruecker.camunda.harvest.domain.CamundaEngineInfo;

@ConfigurationProperties(prefix = "harvest")
@Configuration
public class DefaultCamundaEngineConfig {

	private List<CamundaEngineInfo> engines;

	public List<CamundaEngineInfo> getEngines() {
		return engines;
	}

	public void setEngines(List<CamundaEngineInfo> engines) {
		this.engines = engines;
	}
}
