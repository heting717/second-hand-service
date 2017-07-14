<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>注册页面</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		.rg{width: 300px;height: 300px;margin: 50px 480px;background-color: aquamarine;}
		.rg_m{width:290px;height:300px;margin:20px 10px;}
		/*.user{width:236px;height: 37px;line-height:27px;border: 0;color:#666;margin: 4px 28px;
				background: url(/app/style/images/user.png) no-repeat;padding-left: 10px;font-size: 12px;
				font-family: arial,helvetica,sans-serif;}*/
		.bu{width: 240px;height: 60px;border: 0;color: blue;display: block;font-weight: border;cursor:pointer;font-size: 18px;
			font-family: arial,helvetica,sans-serif;}
		.foot{height: 60px;width: 330px;padding: 10px 20px 10px 20px;}
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
		
		
		
		//随机产生验证码
		function createCode(){
			var datas = ['A','B','C','D','E','F','G','0','1','2','3','4','5','6','7','8','9']; 
			var code = "";
			for(var i = 0 ; i<4; i++){
				//随机产生四个索引值
				var index =  Math.floor(Math.random()*datas.length); // random 0.0-1.0（不包括1.0）
				code+=datas[index];
			}	
			var spanNode = document.getElementById("code");
			spanNode.innerHTML = code;
			spanNode.style.fontSize ="24px";
			spanNode.style.color = "red";
			spanNode.style.backgroundColor="gray";
			spanNode.style.textDecoration = "line-through";
		}
		//输入密码时判定是否超过六位数
		//确认密码时，判定是否相同
		//数据提交时判断验证码是否输入正确
	</script>
  </head>
  
  <body>
		<div class="rg">
			<div class="rg_m">
				<form action="${pageContext.request.contextPath}/RegisterServlet?method=register" method="post">
					用户名：<input class="user" type="text" name="username" value=""/><br />
					电话号码：<input class="userphoto" type="text" name="userphoto" value=""/><br />
					邮箱：<input type="text" name="mail" value=" "><br>
					学校所属省份：<select id="provinceSelect" onchange="showCollege()" name="province">
						<option>省份</option>
						<option>四川省</option>
						<option>湖北省</option>
						<option>湖南省</option>
						
					</select>&nbsp;<br />
					学校：<select id="collegeSelect" name="college">
						<option>学校</option>
					</select><br />
					入学时间：<select id="inTime" onclick=" createInTime()" name="inTime">
						<option>时间</option>
					</select>年&nbsp;9月<br />
					离校时间：<select id="outTime" onclick=" createOutTime()" name="outTime">
						<option>时间</option>
					</select>年&nbsp;6月<br />
					<div id="outTime"></div>
					密码：<input class="pwd" type="password" name="password" /><br/>
					确认密码：<input class="pwd2" type="password" name="password2" /><br/>
					验证码： <input id="codeText" type="text" name="" size="4" lang="4"/>
					<span id="code"></span><a href="#" onclick="createCode()"  >换一个</a><br/>
					
					<!--<input type="button" value="换一张" /><br /> -->
					<div class="foot"><input class="bu" type="submit" value="注册" /></div>&nbsp;&nbsp;
					
					<a href="#" onclick="javascript:history.go(-1);">返回</a>
					<br />
				</form>
			
			</div>
		</div>    
    
  </body>
</html>
