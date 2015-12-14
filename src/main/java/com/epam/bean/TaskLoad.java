package com.epam.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskLoad {

	public static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"dd.MM.yyyy");

	private Integer id;
	
	private Integer orderId;

	private Integer developerQuantity;

	private Integer loadHours;

	private Date startDate;

	private Date endDate;

	private Task task;

	public TaskLoad() {

	}

	// public TaskLoad(Integer loadHours, Task task) {
	// super();
	// this.loadHours = loadHours;
	// this.task = task;
	// }
	//
	// public TaskLoad(int orderId, int taskId, int loadHours) {
	// this.orderId = orderId;
	// this.loadHours = loadHours;
	// this.task = new Task();
	// this.task.setId(taskId);
	// }

	public Integer getOrderId() {
		return orderId;
	}

	public Integer getLoadHours() {
		return loadHours;
	}

	public Task getTask() {
		return task;
	}

	public void setDeveloperQuantity(Integer developerQuantity) {
		this.developerQuantity = developerQuantity;
	}

	public Integer getDeveloperQuantity() {
		return developerQuantity;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public void setStartDate(String startDate) {
		try {
			this.startDate = dateFormat.parse(startDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void setEndDate (String endDate) {
		try {
			this.endDate = dateFormat.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setLoadHours(Integer loadHours) {
		this.loadHours = loadHours;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	@Override
	public String toString() {
		return task + " " + developerQuantity + " " + loadHours + " "
				+ dateFormat.format(startDate) + " "
				+ dateFormat.format(endDate);
	}

}
