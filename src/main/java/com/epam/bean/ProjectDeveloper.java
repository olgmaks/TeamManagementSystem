package com.epam.bean;

import java.util.Date;

public class ProjectDeveloper {

	private Integer projectId;

	private Integer developerId;

	private Integer hours;

	private Date startDate;

	private Date endDate;

	private Double price;

	public ProjectDeveloper() {

	}

	public Integer getProjectId() {
		return projectId;
	}

	public Integer getDeveloperId() {
		return developerId;
	}

	public Integer getHours() {
		return hours;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public void setDeveloperId(Integer developerId) {
		this.developerId = developerId;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return developerId + " " + projectId + " " + hours + " " + price + " "
				+ TaskLoad.dateFormat.format(startDate) + " "
				+ TaskLoad.dateFormat.format(endDate);
	}

}
