package com.example.demo.payload;

import java.time.LocalDateTime;

public class ErrorResponse {
	private String message;
    private String status;
    private String errorDetails;
    
    private LocalDateTime timpestamp;
    
    

	public ErrorResponse() {
		
		// TODO Auto-generated constructor stub
	}



	public ErrorResponse(String message, String status, String errorDetails) {
		super();
		this.message = message;
		this.status = status;
		this.errorDetails = errorDetails;
		this.timpestamp = LocalDateTime.now();
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getErrorDetails() {
		return errorDetails;
	}



	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}



	public LocalDateTime getTimpestamp() {
		return timpestamp;
	}



	public void setTimpestamp(LocalDateTime timpestamp) {
		this.timpestamp = timpestamp;
	}



	@Override
	public String toString() {
		return "ErrorResponse [message=" + message + ", status=" + status + ", errorDetails=" + errorDetails
				+ ", timpestamp=" + timpestamp + "]";
	}
    

	
}
