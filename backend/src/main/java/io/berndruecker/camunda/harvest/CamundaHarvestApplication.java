package io.berndruecker.camunda.harvest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CamundaHarvestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamundaHarvestApplication.class, args);
	}
}
