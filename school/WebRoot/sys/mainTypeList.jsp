<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>主类别页面</title>

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
  		<table>
  			<tr>
  				<td>序号</td>
  				<td>编号</td>
  				<td>主类别名</td>
  				<td>操作</td>
  			</tr>
  			<c:choose>
  				<c:when test="${not empty requestScope.mainTypeList}">
	  				<c:forEach var="mainType" items="${requestScope.mainTypeList}" varStatus="vs">
		  				<tr>
			  				<td>${vs.count}</td>
			  				<td>${mainType.id }</td>
			  				<td>${mainType.mainTypeName}</td>
			  				<td>
			  					<a href="${pageContext.request.contextPath}/MainTypeManageServlet?mainTypeId=${mainType.id}&method=toUpdateMainType">修改</a>
			  					<a href="${pageContext.request.contextPath}/MainTypeManageServlet?mainTypeId=${mainType.id}&method=deleteMainType">删除</a>
			  				</td>
			  			</tr>
	  				
	  				
	  				</c:forEach>
  				</c:when>
  			
  			</c:choose>
  			
  			<tr><td colspan="4" align="left"><a href="${pageContext.request.contextPath}/sys/mainType/addMainType.jsp">添加</a></td></tr>
  		</table>
  
  
  </body>
</html>
