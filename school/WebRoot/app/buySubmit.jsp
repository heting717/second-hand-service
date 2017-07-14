<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'buySubmit.jsp' starting page</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function judgeCount(){
			var sellCount = document.getElementsByName("sellCount");
			var buyCount = document.getElementsByName("buyCount");
			
			if(buyCount>sellCount){
				alert("数量超出了，请重新输入！");
			 	window.location.href="buySubmit.jsp";
			}else{
				alert("购买成功");
			 	/* window.location.href="${pageContext.request.contextPath}/MessageServlet?sellMessageId=${sellMessage.id}&method=showSellMessage"; */
			
			}
		}
		
	
	</script>
  </head>
  
  <body>

    <form action="${pageContext.request.contextPath}/UserIndexServlet?userId=${user.id}&sellMessageId=${sellMessage.id}&method=saveBuy" method="post">
    	<input type="hidden" name="sellCount" value="${sellMessage.sellCount}">
    	数量：<input type="text" name="buyCount">
    	备注：<textarea rows="2" cols="15" name="buyRemark"></textarea>(颜色、大小等其他备注)
    	<input type="submit" value="购买" onclick="judgeCount()">
    </form>
    
    
  </body>
</html>
