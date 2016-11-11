<%@ tag body-content="scriptless" pageEncoding="utf-8" %>
<%@tag import="org.springframework.web.context.WebApplicationContext"%>
<%@tag import="com.java.platform.dictionary.service.DictionaryTableService"%>
<%@tag import="org.apache.commons.lang.StringUtils"%>
<%@tag import="com.java.platform.dictionary.vo.DictionaryTable"%>
<%@tag import="java.util.List"%>
<%@tag import="com.java.platform.dictionary.vo.DictionaryOption"%>
<%@tag import="java.util.Iterator"%>
<%@tag import="com.java.platform.dictionary.impl.IMutipartCheckbox"%>
<%@tag import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="tableCode" type="java.lang.String"%>
<%@ attribute name="fieldCode" type="java.lang.String" required="true"%>
<%@ attribute name="optionCollection" type="java.util.Collection"%>
<%@ attribute name="name" type="java.lang.String"%>
<%@ attribute name="id" type="java.lang.String"%>
<%@ attribute name="styleClass" type="java.lang.String"%>
<%@ attribute name="style" type="java.lang.String"%>
<%@ attribute name="onchange" type="java.lang.String"%>
<%@ attribute name="all" type="java.lang.Boolean"%>
<%@ attribute name="otherName" type="java.lang.String"%>
<%@ attribute name="otherStyleClass" type="java.lang.String"%>
<%@ attribute name="otherStyle" type="java.lang.String"%>
<%@ attribute name="checkboxClass" type="java.lang.String"%>
<%
	DictionaryTableService dictionaryTableService = (DictionaryTableService)jspContext.getAttribute("dictionaryTableService");
	if(dictionaryTableService==null){
		WebApplicationContext webContext = (WebApplicationContext)application.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		dictionaryTableService = (DictionaryTableService)webContext.getBean("dictionaryTableService");
	}
	if(StringUtils.isBlank(tableCode)){
		tableCode = DictionaryTable.GLOBAL_TABLE_CODE;
	}
	List<DictionaryOption> checkboxList = dictionaryTableService.getOptionList(tableCode,fieldCode);
	
	
%>
<div id="<%=id==null?"":id %>" class="<%=styleClass==null?"":styleClass %>" style="<%=style==null?"":style %>" onchange="<%=onchange==null?"":onchange %>">

<%
	String otherValue=null;
	ArrayList<Integer> intList=new ArrayList<Integer>();
	if(optionCollection!=null&&optionCollection.size()>0){
		for(Object o:optionCollection){
			if(o instanceof IMutipartCheckbox){
				IMutipartCheckbox imc=(IMutipartCheckbox) o;
				if(imc!=null&&imc.getSectionOption()!=null){
					intList.add(imc.getSectionOption());
					if(imc.getSectionOption()==DictionaryOption.OTHER){
						otherValue=imc.getSectionDescribe();
					}
					
				}
				
			}
		}
	}
	
		
	Integer num=0;	
	for(DictionaryOption d:checkboxList){
		
		if(d.getOptionValue()==DictionaryOption.OTHER){ 
			%>
				<span style="float:left;width:<%=d.getOptionText().length()*18+120 %>px;">
					<input class="<%=checkboxClass %>" id="<%=d.getOptionId()+num %>" type="checkbox" name="<%=name==null?"":name.replaceFirst("#",num.toString()) %>" value=<%=d.getOptionValue() %>> 
					<label for="<%=d.getOptionId()+num %>"><%=d.getOptionText() %></label>
					<input type="text" 
					value="<%=otherValue==null?"":otherValue %>" 
					name="<%=otherName==null?"":otherName.replaceFirst("#",num.toString()) %>" 
					class="<%=otherStyleClass==null?"":otherStyleClass %>" style="border: 0; border-bottom: 1px solid black;width:100px;<%=otherStyle==null?"":otherStyle %>">
				</span>
			<%
		}else{
			if(optionCollection!=null){
				if(intList.contains(d.getOptionValue())){
					%>
						<span  style="float:left;width:<%=d.getOptionText().length()*18+25 %>px;">&nbsp;&nbsp;<input class="<%=checkboxClass %>" id="<%=d.getOptionId()+num %>" type="checkbox" name="<%=name==null?"":name.replaceFirst("#",num.toString()) %>" checked  value=<%=d.getOptionValue() %> ><label for="<%=d.getOptionId()+num %>"><%=d.getOptionText() %></label> </span>
					<% 
				}else{
					%>
						<span  style="float:left;width:<%=d.getOptionText().length()*18+25 %>px;">&nbsp;&nbsp;<input class="<%=checkboxClass %>" id="<%=d.getOptionId()+num %>" type="checkbox" name="<%=name==null?"":name.replaceFirst("#",num.toString()) %>" value=<%=d.getOptionValue() %>> <label for="<%=d.getOptionId()+num %>"><%=d.getOptionText() %></label></span>
					<%
				}
			}else{
				%>
					<span style="float:left;width:<%=d.getOptionText().length()*18+25 %>px;">&nbsp;&nbsp;<input class="<%=checkboxClass %>" id="<%=d.getOptionId()+num %>" type="checkbox" name="<%=name==null?"":name.replaceFirst("#",num.toString()) %>" value=<%=d.getOptionValue() %>> <label for="<%=d.getOptionId()+num %>"><%=d.getOptionText() %></label></span>
				<%
			}
		}
		
		
		num++;
	}
	
%>
</div>