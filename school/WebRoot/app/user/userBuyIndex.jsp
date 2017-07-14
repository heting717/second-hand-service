<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>登陆后的想购页面</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<style type="text/css">
		body{background:url(/app/style/images/back.jpeg)}
		a:hover{color:aqua;}
		.table_lb{background-color:aliceblue;}
		.message_lb{width: 900px;height: 300px;margin: 1px 200px;background-color:#F0F8FF;}
		.hr_lb{background-color: aquamarine;}
	</style>
  </head>
  
  <body>
   	<div class="head">
			<p align="right">您好！<a href="${pageContext.request.contextPath}/UserMessageServlet?userId=${user.id}&method=showInformation">${user.userName}</a>  &nbsp;&nbsp;<a href="${pageContext.request.contextPath}/IndexServlet?method=showAllMessageList">退出</a></p>
			<h2>乐意网，校园二手交易平台</h2>
			
		</div>
		
		<div class="hr_lb">
			<hr />
				<a href="index.html">出售中</a>&nbsp;&nbsp;<a href="help.html">他人想购</a>&nbsp;&nbsp;<a href="0yuan.html">0元换购</a>
			<hr />
		</div>
		
		<table align="left" class="table_lb">
			<tr><td>商品类别</td></tr>
			<c:choose>
				<c:when test="${not empty requestScope.mainTypeList }">
					<c:forEach var="mainType" items="${requestScope.mainTypeList }">
							<tr><td><a href="${pageContext.request.contextPath}/UserIndexServlet?userId=${user.id}&mainTypeId=${mainType.id}&method=buyShowTypeAndData">${mainType.mainTypeName}</a></td></tr>
					</c:forEach>
				</c:when>
			</c:choose>
		</table>
		<table align="right" class="table_lb">
			<tr><td><a href="${pageContext.request.contextPath}/UserMessageServlet?userId=${user.id}&method=showInformation">${user.userName}</a></td></tr>
			<tr><td><a href="${pageContext.request.contextPath}/UserMessageServlet?userId=${user.id}&method=showInformation">想购</a></td></tr>
			<tr><td><a href="${pageContext.request.contextPath}/UserMessageServlet?userId=${user.id}&method=showInformation">出售</a></td></tr>
			<tr><td><a href="${pageContext.request.contextPath}/UserMessageServlet?userId=${user.id}&method=showInformation">交易情况</a></td></tr>
			<tr><td><a href="${pageContext.request.contextPath}/UserMessageServlet?userId=${user.id}&method=showInformation">收藏</a></td></tr>
			<tr><td><a href="${pageContext.request.contextPath}/UserMessageServlet?userId=${user.id}&method=showInformation">消息</a></td></tr>
		</table>  
		<div class="message_lb">
			<%-- <iframe src="${pageContext.request.contextPath}/app/messageList.jsp" width="100%" height="50" align="center"></iframe> --%>
			<table>
				<tr>
					<c:choose>
						<c:when test="${not empty requestScope.typeList}">
							<c:forEach var="type" items="${requestScope.typeList}">
							<td><a href="${pageContext.request.contextPath}/UserIndexServlet?userId=${user.id}&typeId=${type.id}&method=showAllBuyMessageList">${type.typeName}</a></td>
							</c:forEach>
						</c:when>
					</c:choose>
				</tr>

			</table>
		 
			<c:choose>
				<c:when test="${not empty requestScope.pb.pageData}">
					<c:forEach var="buyMessage" items="${requestScope.pb.pageData}">
					
					<table align="left">
						<tr>
							<td><a href="${pageContext.request.contextPath}/UserIndexServlet?buyMessageId=${buyMessage.id}&method=showUserBuyMessage">${buyMessage.brandName}</a></td>
						</tr>
						
						<tr>
							<td> &yen;${buyMessage.priceRequest}</td>
							
						</tr>
					</table>
			
			
					</c:forEach>
					
				</c:when>
			</c:choose>
			
		</div>    
    
	    <div class="page" align="center">
				当前${pb.currentPage }/${pb.totalPage }页 &nbsp;
			 <a href="javascript:gotoPage(1)">首页</a>
			 <a href="javascript:gotoPage(${pb.currentPage-1 })">上一页</a>
			 <a href="javascript:gotoPage(${pb.currentPage+1 })">下一页</a>
			 <a href="javascript:gotoPage(${pb.totalPage })">尾页</a>
		</div>
    
  </body>
</html>
