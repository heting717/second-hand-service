<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>收藏列表</title>

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
	  		<td>物品名称</td>
	  		<td>品牌</td>
	  		<td>出售价格</td>
	  		<td>物品描述</td>
	  		<td>上传时间</td>
	  		<td>操作</td>
	  	
	  	</tr>
	  	<c:choose>
	  		<c:when test="${not empty requestScope.collectMessageList}">
	  			<c:forEach var="collectMessage" items="${requestScope.collectMessageList}" varStatus="vs">
	  				<tr>
	  					<td> ${vs.count}</td>
	  					<td>  ${collectMessage.sellName}</td>
	  					<td> ${collectMessage.brandName}</td>
	  					<td> ${collectMessage.sellPrice}</td>
	  					<td>${collectMessage.goodsDescribe}</td>
	  					<td>${collectMessage.upTime}</td>
	  					<td><a href="${pageContext.request.contextPath}/UserCollectServlet?userId=${collectMessage.userId}&collectId=${collectMessage.id}&method=delete">删除</a></td>
	  				</tr>
	  			
	  			</c:forEach>
	  		</c:when>
	  	</c:choose>
	  
	  </table>

  </body>
</html>
