<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>更新次类别页面</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  
    <form action="${pageContext.request.contextPath}/TypeManageServlet?typeId=${type.id}&method=updateType" method="post">
   	所属类别：<select name="mainTypeId">
   			<c:choose>
   				<c:when test="${not empty requestScope.mainTypeList}">
   					<c:forEach var="mainType" items="${requestScope.mainTypeList}">
   						<option value="${mainType.id}" >${mainType.mainTypeName}</option>
   					</c:forEach>
   				</c:when>
   			</c:choose>
	    </select>
	类别名称：<input type="text" name="typeName" value="${type.typeName}">
    <input type="submit" value="保存">
    <a href="#" onclick="javascript:history.go(-1);">返回</a>
    </form>
  </body>
</html>
