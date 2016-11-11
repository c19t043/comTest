<%@ tag body-content="scriptless" pageEncoding="utf-8" %>
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
<%@ attribute name="optionValue" type="java.lang.Integer"%>
<%@ attribute name="name" type="java.lang.String"%>
<%@ attribute name="id" type="java.lang.String"%>
<%@ attribute name="styleClass" type="java.lang.String"%>
<%@ attribute name="style" type="java.lang.String"%>
<%@ attribute name="onchange" type="java.lang.String"%>
<%@ attribute name="all" type="java.lang.Boolean"%>
<%@ attribute name="radioClass" type="java.lang.String"%>
<%
	DictionaryTableService dictionaryTableService = (DictionaryTableService)jspContext.getAttribute("dictionaryTableService");
	if(dictionaryTableService==null){
		WebApplicationContext webContext = (WebApplicationContext)application.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		dictionaryTableService = (DictionaryTableService)webContext.getBean("dictionaryTableService");
	}
	if(StringUtils.isBlank(tableCode)){
		tableCode = DictionaryTable.GLOBAL_TABLE_CODE;
	}
	List<DictionaryOption> optionList = dictionaryTableService.getOptionList(tableCode,fieldCode);
	jspContext.setAttribute("optionList",optionList);
	jspContext.setAttribute("value",optionValue);
	jspContext.setAttribute("all",all);
	
%>
<div name="<%=name==null?"":name %>" id="<%=id==null?"":id %>" class="<%=styleClass==null?"":styleClass %>" style="<%=style==null?"":style %>" onchange="<%=onchange==null?"":onchange %>">
	<c:if test="${optionList!=null}">
		<c:forEach var="option" items="${optionList}">			
			&nbsp;&nbsp;<input class="<%=radioClass %>" value="${option.optionValue }" <c:if test="${option.optionValue == value}">checked</c:if> type="radio" name="<%=name==null?"":name %>">&nbsp;${option.optionText}
		</c:forEach>
	</c:if>	
</div>