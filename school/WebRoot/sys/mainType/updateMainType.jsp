<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>更新页面</title>

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
  
    <form action="${pageContext.request.contextPath}/MainTypeManageServlet?mainTypeId=${mainType.id}&method=updateMainType" method="post">
	   	主类别名：<input type="text" name="mainTypeName" value="${mainType.mainTypeName}"><br>
	   <input type="submit" value="保存">
	   <a href="#" onclick="javascript:history.go(-1);">返回</a>
   </form>
  </body>
</html>
