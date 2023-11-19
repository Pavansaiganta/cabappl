package com.cabapp.pro.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AdminException extends RuntimeException {
    private int statusCode;
    private String message;

    public AdminException(int statusCode, String message) {
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
		return "AdminException [statusCode=" + statusCode + ", message=" + message + "]";
	}
}
