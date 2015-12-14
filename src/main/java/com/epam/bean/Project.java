package com.epam.bean;

import java.util.List;

public class Project {

	private Integer id;

	private Integer orderId;

	private String status;

	private Double price;

	private List<ProjectDeveloper> developers;

	public Project() {

	}

	public Project(Integer id, Integer orderId, String status, Double price) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.status = status;
		this.price = price;
	}

	public List<ProjectDeveloper> getDevelopers() {
		return developers;
	}

	public void setDevelopers(List<ProjectDeveloper> developers) {
		this.developers = developers;
	}

	public Integer getId() {
		return id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public String getStatus() {
		return status;
	}

	public Double getPrice() {
		return price;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return id + " " + orderId + " " + status + " " + price + " "
				+ developers;
	}

}
