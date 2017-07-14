<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="cn.itcast.entity.User" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>个人信息</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
			.user_m{width: 100px;height: 50px;}
			
	</style>
	<script type="text/javascript">
		function showCollege(){
			var college =[[],["四川大学","电子科技大学","西南石油大学","西南财经大学","四川农业大学","西南交通大学","成都理工大学","西华大学","成都中医药大学","四川师范大学","成都信息工程大学","西南科技大学","西南民族大学" ,"西南民族大学",
					"西华师范大学" , "中国民用航空飞行学院" , "四川理工学院" ,"泸州医学院" ,"川北医学院" ,"成都医学院" ,"内江师范学院" ,"四川文理学院" ,"乐山师范学院","四川警察学院","成都体育学院" ,"四川音乐学院" ,"四川民族学院" ,"绵阳师范学院",
					"宜宾学院","攀枝花学院" ,"西昌学院"  ,"成都学院"],["武汉大学","华中科技大学","华中师范大学","武汉理工大学","中国地质大学","华中农业大学","中南财经政法大学","湖北大学","武汉科技大学","湖北中医药大学","武汉体育学院","武汉音乐学院","武汉工程大学","湖北工业大学",
					"湖北美术学院","武汉纺织大学","湖北经济学院","武汉工业学院","湖北警官学院","湖北第二师范学院","华中科技大学文华学院","华中科技大学武昌分校","武汉大学东湖分校","武汉大学珞珈学院","武汉理工大学华夏学院","中国地质大学江城学院",
					"华中师范大学汉口分校","华中师范大学武汉传媒学院","中南财经政法大学武汉学院","华中农业大学楚天学院","中南民族大学工商学院","武汉科技大学中南分校","武汉科技大学城市学院","湖北大学知行学院","江汉大学文理学院","湖北工业大学工程技术学院",
					"湖北工业大学商贸学院","武汉工程大学邮电与信息工程学院","武汉科技学院外经贸学院","湖北经济学院法商学院","武汉体育学院体育科技学院","武汉工业学院工商学院","武汉生物工程学院"],["中南大学","湖南大学","湖南师范大学","湘潭大学" ,"湖南科技大学","长沙理工大学","南华大学","湖南工程学院" ,"湖南工业大学","湖南商学院","衡阳师范学院","湖南文理学院" ,"国防科学技术大学","湖南农业大学","长沙医学院","中南林业科技大学",
					"长沙师范专科学校","湖南铁道职业技术学院","邵阳学院","长沙学院" ,"湖南涉外经济学院","湖南商务职业技术学院","湖南科技职业学院","长沙民政职业技术学院","长沙通信职业技术学院","湖南交通职业技术学院","张家界航空工业职业技术学院",
					"湖南科技学院","湖南中医药大学","湖南工学院","湖南化工职业技术学院","邵阳医学高等专科学校","怀化医学高等专科学校","中南大学湘雅医学院","湖南软件职业学院","湖南第一师范学院","湖南城市学院","湖南工业职业技术学院","湖南信息职业技术学院",
					"湖南机电职业技术学院","湘潭职业技术学院","湖南农业大学东方科技学院","湖南城建职业技术学院","长沙南方职业学院","株洲职业技术学院","湖南财政经济学院","湖南工程职业技术学院","长沙商贸旅游职业技术学院","娄底职业技术学院",
					"湖南生物机电职业技术学院","湖南都市职业学院","湘南学院","湖南人文科技学院","湖南现代物流职业技术学院","湖南理工学院","吉首大学" ,"怀化学院"]];
			//获取省份对应节点
			var provinceNode = document.getElementById("provinceSelect");
			//获取省份选中选项
			var selectIndex = provinceNode.selectedIndex;
			//获取对应学校
			var collegeDatas = college[selectIndex];
			 
			//找到学校的节点
			var collegeNode = document.getElementById("collegeSelect");
			//设置option的个数
			collegeNode.options.length = 1;
			//遍历所有的学校，创建option将学校添加上去
			for(var index = 0;index < collegeDatas.length;index++){
				var option = document.createElement("option");
				option.innerHTML = collegeDatas[index];
				collegeNode.appendChild(option);
			}
		}
		//入学时间
		function createInTime(){
			var date =new Date();
			var fullYear = date.getFullYear();
			var inTime = document.getElementById("inTime");
			for(var i = fullYear;i>fullYear-80;i--){
				var options = document.createElement("option");
				options.innerHTML = i;
				inTime.appendChild(options);
			}
		}
		function createOutTime(){
			var date =new Date();
			var fullYear = date.getFullYear();
			var inTime = document.getElementById("outTime");
			for(var i = fullYear+10;i>fullYear-80;i--){
				var options = document.createElement("option");
				options.innerHTML = i;
				inTime.appendChild(options);
			}
		}
		/* //图片下载
		function uploadImage(){
			// 检查图片格式  
		    var f=document.getElementById("userImage").value;  
		 
		    if(!/\.(gif|jpg|jpeg|png|JPG|PNG)$/.test(f)){  
		        alert("图片类型必须是.jpeg,jpg,png中的一种")  
		        return false;  
		    }  
		    // 利用ajaxFileUpload js 插件上传图片  
		    $.ajaxFileUpload({url:"uploadPreviewImage.html",  
		        secureuri:false,  
		        fileElementId:"userImage",  
		        dataType:"json",  
		         
		        success:function (data , status) {  
		             //上传成功后，直接跳出截图框，使用imgAreaSelect插件  
		            piso = $('#photo').imgAreaSelect({   
		                  x1: 0, y1: 0, x2:480 , y2: 520 ,onSelectEnd: preview,  
		            resizable: false,  
		            instance: true,  
		            persistent:true 
		            });  
		            // 这个方法是现实一个div，托住截图框  
		            showCutImage();  
		            // 一些变量在页面的隐藏input的设置  
		            document.getElementById("photo").src = data.tempPath;  
		            document.getElementById("currentPath").value = data.tempPath;  
		              
		        },  
		        error:function (data, status, e) {  
		            //alert("图片上传失败,请重新选择图片");  
		        }  
		    });  
		    return false;  
		
		}
		// 截图选中后调用方法，保存好起始坐标和宽高  
		function preview(img, selection){  
	        $('#x1').val(selection.x1);  
	        $('#y1').val(selection.y1);  
	        $('#x2').val(selection.x2);  
	        $('#y2').val(selection.y2);  
	        $('#w').val(selection.width);  
	        $('#h').val(selection.height);  
	   }  
		 */
	</script>
	
	
  </head>
  <body>
  	<div class="rg" >
			<div class="rg_m">
				<a href="${pageContext.request.contextPath}/UserMessageServlet?userId=${user.id}&method=toUpdatePwd">修改密码</a>
				
				
				<form action="${pageContext.request.contextPath}/UserMessageServlet?userId=${user.id}&method=updateInformation" method="post" enctype="multipart/form-data" >
					头像：<br><img alt="头像" width="100px" height="100px" src="${pageContext.request.contextPath}/upload/userImage/${user.imagePath}"><br>
					上传头像：<input type="file" name="userImage" id="userImage"  ><br>
					电话号码：<input class="userphoto" type="text" name="userphoto" value="${user.phone }"/><br />
					邮箱：<input type="text" name="mail" value="${user.mail}">
					用户名：<input class="user" type="text" name="username" value="${user.userName}"/><br />
					学校所属省份：<select id="provinceSelect" onchange="showCollege()" name="province">
						<option>省份</option>
						<option>四川省</option>
						<option>湖北省</option>
						<option>湖南省</option>
						
					</select>&nbsp;<br />
					学校：<select id="collegeSelect" name="college">
						<option value="${user.school}">${user.school}</option>
					</select><br />
					入学时间：<select id="inTime" onclick=" createInTime()" name="inTime">
						<option>${user.inTime}</option>
					</select>年&nbsp;9月<br />
					离校时间：<select id="outTime" onclick=" createOutTime()" name="outTime">
						<option>${user.outTime}</option>
					</select>年&nbsp;6月<br />
			<%-- 		<% User user2 = (User)request.getAttribute("user"); %> <%=user2.getImagePath() %>--%>
					
					<!--<input type="button" value="换一张" /><br /> -->
					<input type="submit" value="保存" />&nbsp;&nbsp;
					<a href="#" onclick="javascript:history.go(-1);">返回</a>
					<br />
				</form>
				
			</div>
		</div>    
  
  
  
  </body>	
  	
</html>
