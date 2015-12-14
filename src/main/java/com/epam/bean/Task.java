package com.epam.bean;


public class Task {

	private Integer id;

	private String specialization;

	public Task() {

	}

	public Task(Integer id, String specialization) {
		super();
		this.id = id;
		this.specialization = specialization;
	}

	public Integer getId() {
		return id;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	@Override
	public String toString() {
		return id + " " + specialization;
	}

}
