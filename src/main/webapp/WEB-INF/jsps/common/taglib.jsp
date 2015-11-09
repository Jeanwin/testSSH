<%--<%@include%>可以包含任意页面，处理动态页面时：先包含，再处理；
<jsp:include/>处理动态页面时：先处理，在包含
 --%>
<%-- <%@ taglib prefix="page" tagdir="/WEB-INF/tags"%> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />