<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>所有物品类别</title>

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
			<td>类别名</td>
			<td>所属主要类别名</td>
			<td>操作</td>
		</tr>
		<c:choose>
			<c:when test="${not empty requestScope.typeList}">
				<c:forEach var="type" items="${requestScope.typeList}" varStatus="vs">
					<tr>
						<td>${vs.count}</td>
						<td>${type.id}</td>
						<td>${type.mainTypeName}</td>
						<td>${type.typeName}</td>
						<td>
							<a href="${pageContext.request.contextPath}/TypeManageServlet?typeId=${type.id}&method=toUpdateType">修改</a>
							<a href="${pageContext.request.contextPath}/TypeManageServlet?typeId=${type.id}&method=deleteType">删除</a>
						</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
		<tr><td colspan="5" align="left"><a href="${pageContext.request.contextPath}/TypeManageServlet?method=addTypeList">添加</a></td></tr>
 
    
    </table>
    
  </body>
</html>
