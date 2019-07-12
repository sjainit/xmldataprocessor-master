package com.companyname.ManageCustomerData.data.dto;

import java.util.Date;

public class ErrorDetails {

	private Date timestamp;
	private String errorMessage;
	private String errorDesc;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public Date setTimestamp(Date timestamp) {
		return this.timestamp = timestamp;
	}

	public Date getTimestamp() {
		return timestamp;
	}

}
