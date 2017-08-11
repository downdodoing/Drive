package com.cqut.entity;

import java.util.HashMap;

public class Uri {

	private String uri;
	private HashMap<String, String> params;
	private String action;

	public Uri(String uri, HashMap<String, String> params, String action) {
		this.uri = uri;
		this.params = params;
		this.action = action;
	}

	public String getUri() {
		return uri;
	}

	public HashMap<String, String> getParams() {
		return params;
	}

	public String getAction() {
		return action;
	}

}
