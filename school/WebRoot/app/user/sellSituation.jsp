<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>交易情况</title>

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
	出售情况<br/>
	<hr/>
	正在出售：
	<table border="-1">
		
		<tr>
			<td>物品名称</td>
			<td>物品类别</td>
			<td>品牌</td>
			<td>购买时间</td>
			<td>购买价格</td>
			<td>出售价格</td>
			<td>出售数量</td>
			<td>物品描述</td>
			<td>上传时间</td>
			<td>操作</td>
		</tr>
		<c:choose>
			<c:when test="${not empty requestScope.sellNoSellList}">
				<c:forEach var="sellMessage" items="${requestScope.sellNoSellList}">
					<tr>
						<td>${sellMessage.sellName}</td>
						<td>${sellMessage.typeName}</td>
						<td>${sellMessage.brandName}</td>
						<td>${sellMessage.buyTime}</td>
						<td>${sellMessage.buyPrice}</td>
						<td>${sellMessage.sellPrice}</td>
						<td>${sellMessage.sellCount}</td>
						<td>${sellMessage.goodsDescribe}</td>
						<td>${sellMessage.upTime}</td>
						<td>
							<a href="${pageContext.request.contextPath}/UserSellServlet?sellMessageId=${sellMessage.id}&userId=${user.id}&method=toUpdateSell">修改</a>
							<a href="${pageContext.request.contextPath}/UserSellServlet?sellMessageId=${sellMessage.id}&userId=${user.id}&method=delete">删除</a>
						</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>

	</table><br/>
	已经出售：
	<table border="-1">
		
		<tr>
			<td>物品名称</td>
			<td>物品类别</td>
			<td>品牌</td>
			<td>购买时间</td>
			<td>购买价格</td>
			<td>出售价格</td>
			<td>出售数量</td>
			<td>物品描述</td>
			<td>上传时间</td>
			<td>操作</td>
		</tr>
		<c:choose>
			<c:when test="${not empty requestScope.sellSellList}">
				<c:forEach var="sellMessage" items="${requestScope.sellSellList}">
					<tr>
						<td>${sellMessage.sellName}</td>
						<td>${sellMessage.typeName}</td>
						<td>${sellMessage.brandName}</td>
						<td>${sellMessage.buyTime}</td>
						<td>${sellMessage.buyPrice}</td>
						<td>${sellMessage.sellPrice}</td>
						<td>${sellMessage.sellCount}</td>
						<td>${sellMessage.goodsDescribe}</td>
						<td>${sellMessage.upTime}</td>
						<td>
							<a href="${pageContext.request.contextPath}/UserSellServlet?sellMessageId=${sellMessage.id}&userId=${user.id}&method=delete">删除</a>
						</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>

	</table><br/>
	想购情况：<br/>
	<hr/>
	未解决：<br/>
	<table>
		<tr>
			<td>物品名称</td>
			<td>类别名称</td>
			<td>价格要求</td>
			<td>要求</td>
			<td>上传时间</td>
			<td>操作</td>
		</tr>
	
		<c:choose>
			<c:when test="${not empty requestScope.buyNoSolveList }">
				<c:forEach var="buyMessage" items="${requestScope.buyNoSolveList}">
					<tr>
						<td>${buyMessage.brandName}</td>
						<td>${buyMessage.typeName}</td>
						<td>${buyMessage.priceRequest}</td>
						<td>${buyMessage.requireDescribe}</td>
						<td>${buyMessage.upTime}</td>
						<td>
							<a href="${pageContext.request.contextPath}/UserBuyServlet?buyId=${buyMessage.id}&userId=${user.id}&method=toUpdateBuy">修改</a>
							<a href="${pageContext.request.contextPath}/UserBuyServlet?buyId=${buyMessage.id}&userId=${user.id}&method=delete">删除</a>
						</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
		
	</table>
	已经解决：<br>
	<table>
		<tr>
			<td>物品名称</td>
			<td>类别名称</td>
			<td>价格要求</td>
			<td>要求</td>
			<td>上传时间</td>
			<td>操作</td>
		</tr>
	
		<c:choose>
			<c:when test="${not empty requestScope.buySolveList }">
				<c:forEach var="buyMessage" items="${requestScope.buySolveList}">
					<tr>
						<td>${buyMessage.brandName}</td>
						<td>${buyMessage.typeName}</td>
						<td>${buyMessage.priceRequest}</td>
						<td>${buyMessage.requireDescribe}</td>
						<td>${buyMessage.upTime}</td>
						<td>
							<a href="${pageContext.request.contextPath}/UserBuyServlet?buyId=${buyMessage.id}&userId=${user.id}&method=delete">删除</a>
						</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
		
	</table>
  </body>
</html>
