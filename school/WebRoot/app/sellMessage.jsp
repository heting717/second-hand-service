<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>出售详细</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	 function submitLogin(){
	 	alert("亲，还没登陆！");
	 }
	
	
	</script>

  </head>
  
  <body>
  
  		<table>
			<tr>
				<td>图片</td>
				<td colspan="2">${sellMessage.typeName} ${sellMessage.sellName} ${sellMessage.brandName}</td>
			</tr>
			<tr>
				<td>购买时间： ${sellMessage.buyTimeYear} 年 ${sellMessage.buyTimeMouth} 月</td>
				<td>价格：&yen;${sellMessage.sellPrice}元</td>
				<td>数量： ${sellMessage.sellCount}</td>
				
			</tr>
			<tr>
				<td colspan="3">物品描述：${sellMessage.goodsDescribe}</td>
			</tr>
			<tr>
				<td>邮箱： ${sellMessage.mail}</td>
			</tr>
			<tr>
				<td>给他发邮件：<a href="https://mail.qq.com/">QQ邮箱</a><a href="http://www.yeah.net/">网易邮箱</a></td>
			</tr>
		</table>
	<form action="${pageContext.request.contextPath}/IndexServlet?sellMessageId=${sellMessage.id}&method=showSellMessage" method="post">
		<input type="button" value="购买" onclick="submitLogin()"> 
		<input type="button" value="加入收藏" onclick="submitLogin()">    
  	</form>
			
    
  </body>
  
</html>
