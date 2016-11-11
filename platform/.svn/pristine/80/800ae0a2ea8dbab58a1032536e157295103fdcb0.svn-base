<%@tag body-content="scriptless" pageEncoding="utf-8" %>
<%@tag import="org.springframework.web.context.WebApplicationContext"%>
<%@tag import="com.java.platform.dictionary.service.DictionaryTableService"%>
<%@tag import="org.apache.commons.lang.StringUtils"%>
<%@tag import="com.java.platform.dictionary.vo.DictionaryTable"%>
<%@tag import="java.util.List"%>
<%@tag import="com.java.platform.dictionary.vo.DictionaryOption"%>
<%@tag import="java.util.Iterator"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="tableCode" type="java.lang.String"%>
<%@ attribute name="fieldCode" type="java.lang.String" required="true"%>
<%@ attribute name="optionValue" type="java.lang.Integer" required="true"%>
<%
	DictionaryTableService dictionaryTableService = (DictionaryTableService)jspContext.getAttribute("dictionaryTableService");
	if(dictionaryTableService==null){
		WebApplicationContext webContext = (WebApplicationContext)application.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		dictionaryTableService = (DictionaryTableService)webContext.getBean("dictionaryTableService");
	}
	if(StringUtils.isBlank(tableCode)){
		tableCode = DictionaryTable.GLOBAL_TABLE_CODE;
	}
	DictionaryOption option = dictionaryTableService.getOption(tableCode,fieldCode,optionValue);
	if(option!=null){
		out.print(option.getOptionText());
	}
%>
