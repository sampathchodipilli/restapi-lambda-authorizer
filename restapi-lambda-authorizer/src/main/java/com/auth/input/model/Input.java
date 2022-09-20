package com.auth.input.model;

import java.util.HashMap;
import java.util.Map;

public class Input {

	private String type;
	private String methodArn;
	private String authorizationToken;
	private Map<String, String> headers;

	public Input() {
		super();
	}

	public Input(String type, String methodArn, String authorizationToken, Map<String, String> headers) {
		super();
		this.type = type;
		this.methodArn = methodArn;
		this.authorizationToken = authorizationToken;
		this.headers = headers;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMethodArn() {
		return methodArn;
	}

	public void setMethodArn(String methodArn) {
		this.methodArn = methodArn;
	}

	public String getAuthorizationToken() {
		return authorizationToken;
	}

	public void setAuthorizationToken(String authorizationToken) {
		this.authorizationToken = authorizationToken;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}
	
	/**
	 * Example:
	 * 
	 * methodArn: arn:aws:execute-api:ap-south-1:<account-id>:<api-id>/<stage>/<httpMethod>
	 * 
	 * **/
	public Map<String, String> getHeaderData() {
		HashMap<String, String> map = new HashMap<>();
		String[] split1 = this.methodArn.split(":");
		String[] split2 = split1[5].split("/");
		map.put("region", split1[3]);
		map.put("accountId", split1[4]);
		map.put("apiId", split2[0]);
		map.put("stage", split2[1]);
		map.put("httpMethod", split2[2]);
		return map;
	}

	@Override
	public String toString() {
		return "Input [type=" + type + ", methodArn=" + methodArn + ", authorizationToken=" + authorizationToken + "]";
	}

}
