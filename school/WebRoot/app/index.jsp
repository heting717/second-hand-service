<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>校园二手服务平台</title>
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
		.page{padding: 10px;}
	</style>
  </head>
  
  <body>
	    <div class="head">
			<p align="right">您好！请<a href="${pageContext.request.contextPath}/app/login.jsp">登陆</a>,&nbsp;<a href="${pageContext.request.contextPath}/app/register.jsp">注册</a></p>
			<h2>乐意网，校园二手交易平台</h2>
			
		</div>
		
		<div class="hr_lb">
			<hr />
				<a href="${pageContext.request.contextPath}/IndexServlet?method=showAllMessageList">出售中</a>&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/IndexServlet?method=showAllBuyMessageList">他人想购</a>&nbsp;&nbsp;<a href="0yuan.html">0元换购</a>
			<hr />
		</div>
		
		<table align="left" class="table_lb">
			<tr><td>商品类别</td></tr>
			<c:choose>
				<c:when test="${not empty requestScope.mainTypeList }">
					<c:forEach var="mainType" items="${requestScope.mainTypeList }">
							<tr><td><a href="${pageContext.request.contextPath}/IndexServlet?mainTypeId=${mainType.id}&method=showTypeAndData">${mainType.mainTypeName}</a></td></tr>
					</c:forEach>
				</c:when>
			</c:choose>
		</table>
		<div class="message_lb">
			<%-- <iframe src="${pageContext.request.contextPath}/app/messageList.jsp" width="100%" height="50" align="center"></iframe> --%>
			<table>
				<tr>
					<c:choose>
						<c:when test="${not empty requestScope.typeList}">
							<c:forEach var="type" items="${requestScope.typeList}">
							<td><a href="${pageContext.request.contextPath}/IndexServlet?typeId=${type.id}&method=showAllMessageList">${type.typeName}</a></td>
							</c:forEach>
						</c:when>
					</c:choose>
				</tr>

			</table>
			<c:choose>
				<c:when test="${not empty requestScope.pb.pageData}">
					<c:forEach var="sellMessage" items="${requestScope.pb.pageData}">
					
					<table align="left">
						<tr>
							<td colspan="2">
							<a href="${pageContext.request.contextPath}/UserIndexServlet?userId=${user.id}&sellMessageId=${sellMessage.id}&method=showSellMessage">
								<img alt="无" width="100px" height="100px" src="${pageContext.request.contextPath}/upload/userImage/${sellMessage.imagePath}"><br>
							</a> 
							</td>
						</tr>
						<tr>
							<td><a href="${pageContext.request.contextPath}/IndexServlet?sellMessageId=${sellMessage.id}&method=showSellMessage">${sellMessage.sellName}</a></td><td><a href="${pageContext.request.contextPath}/MessageServlet?sellMessageId=${sellMessage.id}&method=showSellMessage">${sellMessage.brandName}</a> </td>
						</tr>
						
						<tr>
							<td> &yen;${sellMessage.sellPrice}</td>
							
						</tr>
					</table>
					</c:forEach>
				</c:when>
			</c:choose>
			<br>
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
