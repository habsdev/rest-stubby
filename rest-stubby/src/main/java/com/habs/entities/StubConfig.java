package com.habs.entities;

import com.habs.base.BaseEntity;

public class StubConfig extends BaseEntity {

	public static enum Action {
		ADD, DELETE, UPDATE
	}

	public static enum Status {
		ACTIVE, DELETED, INACTIVE
	}

	private String action;

	private String filter;

	private String key;

	private int priority;

	private String status;

	private String target;

	private String value;

	public String getAction() {
		return action;
	}

	public String getFilter() {
		return filter;
	}

	public String getKey() {
		return key;
	}

	public int getPriority() {
		return priority;
	}

	public String getStatus() {
		return status;
	}

	public String getTarget() {
		return target;
	}

	public String getValue() {
		return value;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
