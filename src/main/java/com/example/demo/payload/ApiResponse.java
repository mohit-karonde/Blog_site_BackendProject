package com.example.demo.payload;

public class ApiResponse<T> {
	 
	    private String statusCode;
	    private String success;
	    private String message;
	    private T data;
	    
	    
	    
	    
	    
	    
		public ApiResponse() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		public ApiResponse(String statusCode, String success, String message, T data) {
			super();
			this.statusCode = statusCode;
			this.success = success;
			this.message = message;
			this.data = data;
		}
		public String getStatusCode() {
			return statusCode;
		}
		public void setStatusCode(String statusCode) {
			this.statusCode = statusCode;
		}
		public String getSuccess() {
			return success;
		}
		public void setSuccess(String success) {
			this.success = success;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public T getData() {
			return data;
		}
		public void setData(T data) {
			this.data = data;
		}

		@Override
		public String toString() {
			return "ApiResponse [statusCode=" + statusCode + ", success=" + success + ", message=" + message + ", data="
					+ data + "]";
		}
		
		
		

}
