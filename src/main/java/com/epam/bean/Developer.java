package com.epam.bean;

import java.util.List;

public class Developer extends User {

	private Integer id;

	private Integer freeHours;

	private Task task;

	private Double rate;
	
	public int getUserId() {
		return super.getId();
	}


	private List<ProjectDeveloper> projects;

	public Developer() {

	}

	public Developer(User user) {
		super(user);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<ProjectDeveloper> getProjects() {
		return projects;
	}

	public void setProjects(List<ProjectDeveloper> projects) {
		this.projects = projects;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Integer getFreeHours() {
		return freeHours;
	}

	public Double getRate() {
		return rate;
	}

	public void setFreeHours(Integer freeHours) {
		this.freeHours = freeHours;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	@Override
	public boolean equals(Object obj) {
		return this.id==((Developer)obj).id;
	}

	@Override
	public String toString() {
		return id + " " + super.toString() + " " + task + " " + freeHours + " "
				+ rate + " " + projects;
	}

}
