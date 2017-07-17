package com.uff.logger;

/**
 * 
 * @author abdelbaki_mahmoudi
 *
 */
public class Message {

	private String code;
	private DetailMessage message;
	
	public Message() {
		super();
	}

	public Message(String code, DetailMessage message) {
		super();
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public DetailMessage getDetailMessage() {
		return message;
	}

	public void setDetailMessage(DetailMessage message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return String.format("Message [code=%s, message=%s]", code, message);
	}
	
	
}
