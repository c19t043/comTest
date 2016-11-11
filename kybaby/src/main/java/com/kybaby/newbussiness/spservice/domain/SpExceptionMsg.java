package com.kybaby.newbussiness.spservice.domain;

/**
 * SpExceptionMsg entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class SpExceptionMsg implements java.io.Serializable {

	// Fields

	private Long id;
	private String msg;
	private String requestMsg;
	private String optime;

	// Constructors

	/** default constructor */
	public SpExceptionMsg() {
	}

	/** full constructor */
	public SpExceptionMsg(String requestMsg,String msg, String optime) {
		this.requestMsg = requestMsg;
		this.msg = msg;
		this.optime = optime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public String getRequestMsg() {
		return requestMsg;
	}

	public void setRequestMsg(String requestMsg) {
		this.requestMsg = requestMsg;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getOptime() {
		return this.optime;
	}

	public void setOptime(String optime) {
		this.optime = optime;
	}

}