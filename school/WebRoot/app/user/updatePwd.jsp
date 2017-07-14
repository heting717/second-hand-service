<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>修改密码页面</title>

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
  
  
    <form action="${pageContext.request.contextPath}/UserMessageServlet?userId=${user.id}&method=updatePwd" method="post">
   		密码：<input type="password" name="oldPwd" />
   		新密码：<input type="password" name="newPwd" />
   		<input type="submit" value="保存" />
   	</form>
    
    
  </body>
</html>
