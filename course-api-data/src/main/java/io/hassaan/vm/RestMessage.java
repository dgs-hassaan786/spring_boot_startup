package io.hassaan.vm;

public class RestMessage {

	private Object data;
	private StatusCodeEnum httpStatus;
	private int statusCode;
	private Exception ex;
	
	public RestMessage() {
		
	}
	
	public RestMessage(Object d, StatusCodeEnum c) {
		data = d;
		httpStatus = c;
		statusCode = c.val();
	}
	
	public void setData(Object d) {
		data =d;
	}
	
	public void setHttpStatus(StatusCodeEnum c) {
		httpStatus = c;
	}
	
	public void setStatusCode(int c) {
		statusCode = c;
	}
	
	public void setException(Exception e) {
		ex = e;
	}
	
	public void setStatusCode(StatusCodeEnum c) {
		httpStatus = c;
	}
	
	public Object getData() {
		return data;
	}
	
	public StatusCodeEnum getHttpStatus() {
		return httpStatus;
	}
	
	public Exception getException() {
		return ex;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	
}
