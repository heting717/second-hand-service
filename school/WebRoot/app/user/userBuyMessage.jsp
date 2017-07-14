<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>登陆后想购详细信息</title>

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
				<td colspan="2">${buyMessage.typeName} ${buyMessage.brandName}</td>
			</tr>
			<tr>
				
				<td>价格要求：&yen;${buyMessage.priceRequest}元</td>
			</tr>
			<tr>
				<td>时间： ${buyMessage.upTime}</td>
			</tr>
			<tr>
				<td>其他要求： ${buyMessage.requireDescribe}</td>
			</tr>
			<tr>
				<td>邮箱： ${buyMessage.mail}</td>
			</tr>
			<tr>
				<td>给他发邮件：<a href="https://mail.qq.com/">QQ邮箱</a><a href="http://www.yeah.net/">网易邮箱</a></td>
			</tr>
		</table>
	
    
    
  </body>
</html>
