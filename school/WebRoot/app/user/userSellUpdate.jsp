<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>修改出售信息</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		//购买时间
		function createBuyTimeYear(){
			var date =new Date();
			var fullYear = date.getFullYear();
			var buyTimeYear = document.getElementById("buyTimeYear");
			for(var i = fullYear;i>fullYear-80;i--){
				var options = document.createElement("option");
				options.innerHTML = i;
				buyTimeYear.appendChild(options);
			}
		}
		//月份
		function createBuyTimeMouth(){
			var buyTimeMouth = document.getElementById("buyTimeMouth")
			for(var i = 1;i<=12;i++){
				var options = document.createElement("option");
				options.innerHTML = i;
				buyTimeMouth.appendChild(options);
			}
		}
	
		
	</script>
  </head>
  
  <body>

	<form action="${pageContext.request.contextPath}/UserSellServlet?sellMessageId=${sellMessage.id}&userId=${user.id}&method=updateSell" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td>
			类别：<select name="typeId">
					<option selected="selected">${sellMessage.typeName}</option>
					<c:choose>
						<c:when test="${not empty requestScope.typeList}">
							<c:forEach var="type" items="${requestScope.typeList}">
								<option value="${type.id}">${type.typeName}</option>
							</c:forEach>
						</c:when>
					</c:choose>
			</select>
			</td>
		</tr>
		<tr>
			<td>
			物品名称：<input type="text" name="sellName" value="${sellMessage.sellName}">
			</td>
		</tr>
		<tr>
			<td>
			品牌：<input type="text" name="brandName" value="${sellMessage.brandName}" >
			</td>
		</tr>
		<tr>
			<td>
			购买时间：<select id="buyTimeYear" onclick="createBuyTimeYear()" name="buyTimeYear">
					<option selected="selected">${sellMessage.buyTimeYear}</option>
				</select>年&nbsp;
				<select id="buyTimeMouth" onclick="createBuyTimeMouth()" name="buyTimeMouth">
					<option selected="selected">${sellMessage.buyTimeMouth}</option>
				</select>月
			</td>
		</tr>
		<tr>
			<td>
			购买价格：<input type="text" name="buyPrice" value="${sellMessage.buyPrice}">
			</td>
		</tr>
		<tr>
			<td>
			出售价格：<input type="text" name="sellPrice" value="${sellMessage.sellPrice}">
			</td>
		</tr>
		<tr>
			<td>
			出售数量：<input type="text" name="sellCount" value="${sellMessage.sellCount}">
			</td>
		</tr>
		<tr>
			<td>
			物品描述：<textarea rows="3" cols="20" name="describe" >${sellMessage.goodsDescribe}</textarea>
			</td>
		</tr>
		<tr>
			<td>
			添加图片：<input type="file" name="sellImage" id="sellImage"  >
			</td>
		
		</tr>
		<tr>
			<td>
			图片：<img alt="无" width="100px" height="100px" src="${pageContext.request.contextPath}/upload/userImage/${sellMessage.imagePath}">
			</td>
		
		</tr>
	</table><br>
	
	<input type="submit" value="提交">
	<a href="#" onclick="javascript:history.go(-1);">返回</a>
	
	</form>
        
    
    
    
    
  </body>
</html>
