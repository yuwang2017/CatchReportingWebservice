package com.example.appengine.demos.springboot;

import java.io.Serializable;
import java.util.List;

public class SubmitConfirmation implements Serializable {

	private String status = "OK";
	private List<String> confirmNumberList;
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getConfirmNumberList() {
		return confirmNumberList;
	}

	public void setConfirmNumberList(List<String> confirmNumberList) {
		this.confirmNumberList = confirmNumberList;
	}
	
	
}
