<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>已购买页面</title>

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
  	<table border="-1">
  		<tr>
  			<td>序号</td>
  			<td>物品名称</td>
  			<td>购买价格</td>
  			<td>备注</td>
  			<td>购买时间</td>
  		</tr>
  		<c:choose>
  			<c:when test="${not empty requestScope.userBuyList}">
  				<c:forEach var="userBuy" items="${requestScope.userBuyList}" varStatus="vs">
  				<tr>
  					<td> ${vs.count}</td>
  					<td>${userBuy.sellName}</td>
  					<td>${userBuy.sellPrice}</td>
  					<td> ${userBuy.buyRemark}</td>
					<td> ${userBuy.buyTime}</td>
  				</tr>
  				</c:forEach>
  			</c:when>
  		
  		</c:choose>
  	</table>
  
  </body>
</html>
