<%@ tag body-content="scriptless" pageEncoding="utf-8" %>
<%@tag import="org.springframework.web.context.WebApplicationContext"%>
<%@tag import="com.java.addressbook.messages.service.MessagesService"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="apiUserName" type="java.lang.String" required="true"%>
<%@ attribute name="apiPassword" type="java.lang.String" required="true"%>

<%
	MessagesService messagesService = (MessagesService)jspContext.getAttribute("messagesService");
	if(messagesService==null){
		WebApplicationContext webContext = (WebApplicationContext)application.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		messagesService = (MessagesService)webContext.getBean("messagesService");
	}
	
	String smsNum = messagesService.getSmsInfo(apiUserName, apiPassword);
	jspContext.setAttribute("smsNum",smsNum);
%>
<c:out value="${smsNum}"></c:out>