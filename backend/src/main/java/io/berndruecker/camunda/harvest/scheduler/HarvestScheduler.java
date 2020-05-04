package io.berndruecker.camunda.harvest.scheduler;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import io.berndruecker.camunda.harvest.domain.CamundaEngineInfo;
import io.berndruecker.camunda.harvest.repository.CamundaEngineRepository;

@Component
public class HarvestScheduler {

	private static final Logger log = LoggerFactory.getLogger(HarvestScheduler.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	private CamundaEngineRepository repository;

	@Autowired
	private RestTemplate rest;

	@Scheduled(fixedRate = 5000)
	public void reportCurrentTime() {
		log.debug("Checking engines now {}", dateFormat.format(new Date()));

		queryAllEngines();
	}

	private void queryAllEngines() {
		Iterable<CamundaEngineInfo> engines = repository.findAll();
		for (CamundaEngineInfo camundaEngineInfo : engines) {
			queryEngine(camundaEngineInfo);
		}
	}

	private void queryEngine(CamundaEngineInfo engine) {		
		// query version
		try {
			VersionResponse versionResponse = rest.getForObject(engine.getEndpointVersion(), VersionResponse.class);
			engine.setVersion(versionResponse.version);
			
			// query metrics, here PI/x		
			MetricsSumResponse metricsResponse = rest.getForObject(engine.getEndpointMetricsFniCurrentYear(), MetricsSumResponse.class);
			engine.setFniCounter(metricsResponse.result);
	
			engine.setLatestUpdate(Instant.now());
			engine.setLastUpdateSuccesful(true);			
		} catch (Exception ex) {
			log.warn("Error while querying status for engine " + engine, ex);
			engine.setLastUpdateSuccesful(false);						
		}
		repository.save(engine);
	}

	public static class MetricsSumResponse {
		public long result;
	}

	public static class VersionResponse {
		public String version;
	}
}
