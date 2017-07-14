<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户信息</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
			.user_m{width: 150px;height: 150px;background-color: aquamarine;}
			a:hover{color:aqua;}
			.left_m{background-color:aliceblue;}
			.right_m{width: 900px;height: 3000px;margin: 1px 200px;background-color:#F0F8FF;}
	</style>
  </head>
  		<div align="right"><a href="${pageContext.request.contextPath}/UserIndexServlet?userId=${user.id}&method=toIndex">返回主页</a></div>
  		<div class="user_m">
			<table align="left">
				<tr><td>头像：<br><img alt="头像" width="100px" height="100px" src="${pageContext.request.contextPath}/upload/userImage/${user.imagePath}"><br></td></tr>
				<tr><td>用户名： ${user.userName} </td></tr>
			</table><br>
			
		</div>
			
		
		<hr />
		
		<table align="left" class="left_m" >
			<tr><td><a target="right" href="${pageContext.request.contextPath}/UserMessageServlet?userId=${user.id}&method=checkInformation">个人信息</a></td></tr>
			<tr><td><a target="right" href="${pageContext.request.contextPath}/UserBuyServlet?userId=${user.id}&method=toBuyMessage">想购</a></td></tr>
			<tr><td><a target="right" href="${pageContext.request.contextPath}/UserSellServlet?userId=${user.id}&method=toSellMessage">出售</a></td></tr>
			<tr><td><a target="right" href="${pageContext.request.contextPath}/UserSellServlet?userId=${user.id}&method=toSellSituation">交易情况</a></td></tr>
			<tr><td><a target="right" href="${pageContext.request.contextPath}/BuyServlet?userId=${user.id}&method=findUserBuy">已购买</a></td></tr>
			<tr><td><a target="right" href="${pageContext.request.contextPath}/UserCollectServlet?userId=${user.id}&method=showCollectMessage">收藏</a></td></tr>
		</table>
		
		<div class="right_m">
		
			<iframe name="right" width="900px" height="3000px" src="${pageContext.request.contextPath }/app/user/userRight.jsp" scrolling="yes"></iframe>
			
			
		
		</div>
		
  
  
  </body>
</html>
