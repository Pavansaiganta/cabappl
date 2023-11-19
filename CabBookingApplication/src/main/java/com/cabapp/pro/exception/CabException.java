package com.cabapp.pro.exception;

import lombok.Data;

@Data

public class CabException extends RuntimeException {
    private int statusCode;
    private String message;

    public CabException(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "CabException [statusCode=" + statusCode + ", message=" + message + "]";
	}
}
