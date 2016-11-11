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
<%@ attribute name="allText" type="java.lang.String"%>
<%
	DictionaryTableService dictionaryTableService = (DictionaryTableService)jspContext.getAttribute("dictionaryTableService");
	if(dictionaryTableService==null){
		WebApplicationContext webContext = (WebApplicationContext)application.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		dictionaryTableService = (DictionaryTableService)webContext.getBean("dictionaryTableService");
	}
	if(StringUtils.isBlank(tableCode)){
		tableCode = DictionaryTable.GLOBAL_TABLE_CODE;
	}
	if(allText==null||"".equals(allText)){
		allText = "全部";
	}
	List<DictionaryOption> optionList = dictionaryTableService.getOptionList(tableCode,fieldCode);
	jspContext.setAttribute("optionList",optionList);
	jspContext.setAttribute("value",optionValue);
	jspContext.setAttribute("all",all);
	jspContext.setAttribute("allText",allText);
%>
<select name="<%=name==null?"":name %>" id="<%=id==null?"":id %>" class="<%=styleClass==null?"":styleClass %>" style="<%=style==null?"":style %>" onchange="<%=onchange==null?"":onchange %>">
	<c:if test="${optionList!=null}">
		<c:if test="${all}">			
			<option value="" <c:if test="${value==null}">selected="selected"</c:if>>${allText}</option>
		</c:if>
		<c:forEach var="option" items="${optionList}">	
			<option value="${option.optionValue }" <c:if test="${option.optionValue == value}">selected="selected"</c:if>>${option.optionText}</option>			
		</c:forEach>
	</c:if>	
</select>