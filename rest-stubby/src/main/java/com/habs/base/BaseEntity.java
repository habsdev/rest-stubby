package com.habs.base;

import com.google.gson.Gson;

public abstract class BaseEntity {
	
	private static final Gson gson = new Gson();
	
	public String toString() {
		return BaseEntity.gson.toJson(this);
	}

}
