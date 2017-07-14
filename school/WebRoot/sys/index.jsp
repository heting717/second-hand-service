<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>校园二手服务管理平台</title>

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
		<tr><td><a href="${pageContext.request.contextPath}/MainTypeManageServlet?method=showMainTypeList">物品主类别管理</a></td></tr>
		<tr><td><a href="${pageContext.request.contextPath}/TypeManageServlet?method=typeList">所有类别管理</a></td></tr>
		<tr><td></td></tr>
		<tr><td></td></tr>
	
	
	</table>    
    
    
    
  </body>
</html>
