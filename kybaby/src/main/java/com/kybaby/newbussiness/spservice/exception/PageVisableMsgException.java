package com.kybaby.newbussiness.spservice.exception;

/**
 * 影响正常业务流程运行的错误信息, 
 */
public class PageVisableMsgException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public PageVisableMsgException(String msg) {
		super(msg);
	}
}
