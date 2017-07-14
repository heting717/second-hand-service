<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>登陆页面</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- <style type="text/css">
			body{background: url(/app/style/images/back.jpeg) no-repeat;font-family: arial,helvetica,sans-serif;}
			.lg{width:468px;height: 468px;margin: 50px auto;background: url(/app/style/images/login_bg.png) no-repeat;}
			.lg_top{width: 468px;height: 200px;}
			.lg_m_1{width: 290px;height: 100px;padding: 60px 55px 20px 55px;}
			.lg_main{width: 400px;height: 180px;margin: 0 25px;}
			.user{width:236px;height: 37px;line-height:27px;border: 0;color:#666;margin: 4px 28px;
				background: url(/app/style/images/user.png) no-repeat;padding-left: 10px;font-size: 12px;
				font-family: arial,helvetica,sans-serif;}
			.pw{width:236px;height: 37px;line-height:27px;border: 0;color:#666;margin: 4px 28px;
				background: url(/app/style/images/password.png) no-repeat;padding-left: 10px;font-size: 12px;
				font-family: arial,helvetica,sans-serif;}
			.bu{width: 330px;height: 72px;border: 0;color: #FFF;background: url(/app/style/images/enter.png) no-repeat;
				display: block;font-weight: border;cursor:pointer;font-size: 18px;
				font-family: arial,helvetica,sans-serif;}
			.lg_foot{height: 80px;width: 330px;padding: 6px 68px 0 68px;}
	</style> -->
	<style type="text/css">
		body{font-family: arial,helvetica,sans-serif;}
		.lg{width:468px;height: 468px;margin: 50px auto;}
		.lg_top{width: 468px;height: 200px;}
		.lg_m_1{width: 290px;height: 100px;padding: 60px 55px 20px 55px;}
		.lg_main{width: 400px;height: 280px;margin: 0 25px;background-color:aquamarine}
		.user{width:236px;height: 37px;line-height:27px;border: 0;color:#666;margin: 4px 28px;padding-left: 10px;font-size: 12px;
				font-family: arial,helvetica,sans-serif;}
		.pw{width:236px;height: 37px;line-height:27px;border: 0;color:#666;margin: 4px 28px;padding-left: 10px;font-size: 12px;
		font-family: arial,helvetica,sans-serif;}
		.lg_m_1{width: 290px;height: 100px;padding: 60px 55px 20px 55px;}
		.bu{width: 240px;height: 60px;border: 0;color: blue;display: block;font-weight: border;cursor:pointer;font-size: 18px;
			font-family: arial,helvetica,sans-serif;}
		.lg_foot{height: 60px;width: 330px;padding: 20px 120px 10px 80px;}
	</style>
  </head>
  
  <body>
		<div class="lg" >
			<form action="${pageContext.request.contextPath}/LoginServlet?method=login" method="post">
				<div class="lg_top"></div>
				<div class="lg_main">
					<div class="lg_m_1">
						电话号码：<input class="user" type="text" name="phone"  />	
						密码：<input class="pw" type="password" name="password"  />	
					</div>
					<div class="lg_foot"><input class="bu" type="submit" value="登陆"/></div>
				</div>
				
			</form>
		</div>
		<div class="lg_rg" style="text-align: center;">
			<p>还没账号，去<a href="${pageContext.request.contextPath}/app/register.jsp">注册</a></p>
			<p><a href="#" onclick="javascript:history.go(-1);">返回</a></p>
		</div>   
    
    
  </body>
</html>
