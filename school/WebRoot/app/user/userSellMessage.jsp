<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>登陆后的出售详细</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function submitDatas(){
			
			alert("收藏成功！");
		}
	
	
	</script>
  </head>
  
  <body>
  <form action="${pageContext.request.contextPath}/UserIndexServlet?userId=${user.id}&sellMessageId=${sellMessage.id}&method=buy" method="post">
    <table>
			<tr>
				<td>图片</td>
				<td colspan="2">${sellMessage.typeName} ${sellMessage.sellName} ${sellMessage.brandName}</td>
			</tr>
			<tr>
				<td>购买时间： ${sellMessage.buyTimeYear} 年 ${sellMessage.buyTimeMouth} 月</td>
				<td>价格：&yen;${sellMessage.sellPrice}元</td>
				<td id="sellCount">数量： ${sellMessage.sellCount}</td>
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
	
		<input type="submit" value="购买" onclick="sellCount()"> 
		
  	</form>
			
    <form action="${pageContext.request.contextPath}/UserIndexServlet?userId=${user.id}&sellMessageId=${sellMessage.id}&method=addCollect" method="post">
		<input type="submit" value="加入收藏" onclick="submitDatas()">    
  	</form>
    
    
    
  </body>
</html>
