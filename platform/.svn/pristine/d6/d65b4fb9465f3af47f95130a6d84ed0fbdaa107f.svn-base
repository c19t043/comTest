<%@ tag body-content="scriptless" pageEncoding="utf-8" %>
<%@ tag import="com.java.platform.role.vo.Domain"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ attribute name="domains" type="java.util.Collection"%>
<%@ attribute name="values" type="java.util.Collection"%>
<%@ attribute name="layer" type="java.lang.String"%>
<%@ taglib tagdir="/WEB-INF/tags/domain" prefix="domain"%>

<table width="100%" class="tableRegion2" style="border:0;">
	<c:forEach items="${domains}" var="domain" varStatus="vs">
		<tr>
			<td class="inputLabel2" style="border:0;">
				<%
					Domain d = (Domain)jspContext.getAttribute("domain");
					if(values !=null && values.contains(d)){
						jspContext.setAttribute("checked","checked='${checked }'");
					}else{
						jspContext.setAttribute("checked","");
					}
				 %>
				<input id="${domain.domainId }" name="domainID" type="checkbox" ${checked } value="${domain.domainId }" class="${layer }<c:if test="${vs.count gt 9}">${vs.count}</c:if><c:if test="${vs.count le 9}">0${vs.count}</c:if> domainCheckBox"/><label for="${domain.domainId }">${domain.domainName }</label>
			</td>
			<td style="border:0;">
				<font color="gray">${domain.remark }</font> &nbsp;
			</td>
		</tr>
		<c:if test="${fn:length(domain.children) gt 0}">
			<tr>
				<td class="inputLabel2" style="border:0;">
					&nbsp;
				</td>
				<td style="border:0;">
					<c:set var="layerTemp">
						<c:if test="${vs.count gt 9}">${layer }${vs.count}</c:if><c:if test="${vs.count le 9}">${layer }0${vs.count}</c:if>
					</c:set>
					<domain:tableTree domains="${domain.children}" layer="${layerTemp }000" values="${values}"/>
				</td>
			</tr>
		</c:if>
	</c:forEach>
</table>