 package com.wx.util.exception;
 
 public class ServiceException extends RuntimeException
 {
   private static final long serialVersionUID = 1L;
   private String errorCode = "UNKNOW_ERROR";
 
   protected String[] errorArgs = null;
 
   public ServiceException()
   {
   }
 
   public ServiceException(String errorCode, String[] errorArgs) {
     this.errorCode = errorCode;
     this.errorArgs = errorArgs;
   }
 
   public ServiceException(String errorCode, String errorArg) {
     this.errorCode = errorCode;
     this.errorArgs = new String[] { errorArg };
   }
 
   public ServiceException(String errorCode, String[] errorArgs, Throwable cause)
   {
     super(cause);
     this.errorCode = errorCode;
     this.errorArgs = errorArgs;
   }
 
   public ServiceException(String errorCode, String errorArg, Throwable cause) {
     super(cause);
     this.errorCode = errorCode;
     this.errorArgs = new String[] { errorArg };
   }
 
   public String getErrorCode() {
     return this.errorCode;
   }
 }
