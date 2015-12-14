package com.epam.bean;

import java.util.ArrayList;
import java.util.List;

public class Order {

	private Integer id;

	private String name;

	private String description;

	private String additionalInformation;

	private Integer customerId;

	private List<TaskLoad> taskLoads;

	public Order() {
		taskLoads = new ArrayList<TaskLoad>();
	}

	public Order(Integer id, String name, String description,
			String additionalInformation, Integer customerId,
			List<TaskLoad> taskLoads) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.additionalInformation = additionalInformation;
		this.customerId = customerId;
		this.taskLoads = taskLoads;
	}

	public Order(Integer id, String name, String description,
			String additionalInformation, Integer customerId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.additionalInformation = additionalInformation;
		this.customerId = customerId;
		taskLoads = new ArrayList<TaskLoad>();
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public List<TaskLoad> getTaskLoads() {
		return taskLoads;
	}

	public void setTaskLoads(List<TaskLoad> taskLoads) {
		this.taskLoads = taskLoads;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return id + " " + name + " " + description + " "
				+ additionalInformation + " " + customerId + " "+ taskLoads;
	}

}
