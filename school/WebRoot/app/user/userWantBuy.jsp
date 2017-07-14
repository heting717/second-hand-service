<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>想购</title>

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

	<form action="${pageContext.request.contextPath}/UserBuyServlet?userId=${user.id}&method=save" method="post">
		<table>
			<tr>
				<td>
				类别：<select name="typeId">
						<c:choose>
							<c:when test="${not empty requestScope.typeList}">
								<c:forEach var="type" items="${requestScope.typeList}">
								<option value="${type.id}">${type.typeName}</option>
								</c:forEach>
							</c:when>
						</c:choose>
				</select>
				</td>
			</tr>
			<tr>
				<td>
				  物品名称：<input type="text" name="brandName">
			
				</td>
			</tr>
			<tr>
				<td>
			
				 价格要求：<input type="text" name="priceRequest">
				</td>
			</tr>
			<tr>
				<td>
				 要求描述：<textarea rows="3" cols="20" name="describe"></textarea>
			
				</td>
			</tr>
		
		</table>
		<input type="submit" value="提交">
		<a href="#" onclick="javascript:history.go(-1);">返回</a>
	
	</form>
	
  
   
   
    
    
  </body>
  
</html>
