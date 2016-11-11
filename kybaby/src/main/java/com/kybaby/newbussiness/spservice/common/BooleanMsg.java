package com.kybaby.newbussiness.spservice.common;

public class BooleanMsg{
	private Boolean isTrue;
	private String msg;
	private Object object;
	public Boolean isTrue() {
		return isTrue;
	}
	public void isTrue(Boolean isTrue) {
		this.isTrue = isTrue;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
}
