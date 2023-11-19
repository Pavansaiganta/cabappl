package com.cabapp.pro.exception;

public class TripBookingException extends RuntimeException{
	
	 private int statusCode;
	    private String message;

	    public TripBookingException(int statusCode, String message) {
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
			return "CustomerException [statusCode=" + statusCode + ", message=" + message + "]";
		}

}
