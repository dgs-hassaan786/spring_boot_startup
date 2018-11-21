package io.hassaan.vm;

public enum StatusCodeEnum {
	
		OK(200),
		BAD_REQUEST(400),
		NOT_FOUND(404),
		INTERNAL_SERVER_ERROR(500);
	
	private final int statusCode;
	
	StatusCodeEnum(int code){		
		statusCode = code;		
	}
	
	public int val() {
		return statusCode;
	}
	
}
