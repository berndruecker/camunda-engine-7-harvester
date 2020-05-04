package io.berndruecker.camunda.harvest.domain;

import java.time.Instant;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CamundaEngineInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(unique=true)
	private String name;
	private String endpoint;
	private String cockpit;

	private String version;
	private Instant latestUpdate;
	private boolean lastUpdateSuccesful;

	private long processInstancesStarted;
	private long fniCounter;

	private long processInstancesStartedLimit;
	private long fniLimit;

	public String getEndpointVersion() {
		return getEndpoint() + "version";
	}
	
	public String getEndpointMetricsFniCurrentYear() {
		return getEndpoint() + "metrics/activity-instance-start/sum"; // TODO
	}

	public String getEndpointMetricsFni(Date startDate, Date endDate) {
		return getEndpointMetrics("activity-instance-start", startDate, endDate);
	}

	public String getEndpointMetrics(String name, Date startDate, Date endDate) {
		// TODO: Fix ISO format
		return getEndpoint() + "metrics/"+name+"/sum?startDate=" + startDate + "&endDate=" + endDate;
	}
	
	public boolean isFniInLimit() {
		return fniCounter < fniLimit;
	}

	public void setEndpoint(String endpoint) {
		if (endpoint.endsWith("/")) {
			this.endpoint = endpoint;
		} else {
			this.endpoint = endpoint + "/";			
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Instant getLatestUpdate() {
		return latestUpdate;
	}

	public void setLatestUpdate(Instant latestUpdate) {
		this.latestUpdate = latestUpdate;
	}

	public boolean isLastUpdateSuccesful() {
		return lastUpdateSuccesful;
	}

	public void setLastUpdateSuccesful(boolean lastUpdateSuccesful) {
		this.lastUpdateSuccesful = lastUpdateSuccesful;
	}

	public long getProcessInstancesStarted() {
		return processInstancesStarted;
	}

	public void setProcessInstancesStarted(long processInstancesStarted) {
		this.processInstancesStarted = processInstancesStarted;
	}

	public long getFniCounter() {
		return fniCounter;
	}

	public void setFniCounter(long fniCounter) {
		this.fniCounter = fniCounter;
	}

	public String getCockpit() {
		return cockpit;
	}

	public void setCockpit(String cockpit) {
		this.cockpit = cockpit;
	}

	public long getProcessInstancesStartedLimit() {
		return processInstancesStartedLimit;
	}

	public void setProcessInstancesStartedLimit(long processInstancesStartedLimit) {
		this.processInstancesStartedLimit = processInstancesStartedLimit;
	}

	public long getFniLimit() {
		return fniLimit;
	}

	public void setFniLimit(long fniLimit) {
		this.fniLimit = fniLimit;
	}

}
