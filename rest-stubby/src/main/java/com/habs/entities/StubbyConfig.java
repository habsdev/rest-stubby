package com.habs.entities;

import com.habs.base.BaseEntity;

public class StubbyConfig extends BaseEntity {

	public static enum Status {
		ACTIVE, INACTIVE, DELETED
	}

	public static enum Action {
		UPDATE, DELETE, ADD
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getRequestFilter() {
		return requestFilter;
	}

	public void setRequestFilter(String requestFilter) {
		this.requestFilter = requestFilter;
	}

	public String getResponseTarget() {
		return responseTarget;
	}

	public void setResponseTarget(String responseTarget) {
		this.responseTarget = responseTarget;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private int priority;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	private String status;
	private String action;
	private String requestFilter;
	private String responseTarget;
	private String value;
	
	

}
