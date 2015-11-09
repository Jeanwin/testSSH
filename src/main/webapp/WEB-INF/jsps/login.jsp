<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="./common/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE>

<html lang="zh-CN">

<head>
<base href="<%=basePath%>">
<title>用户登录界面</title>
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@ include file="./common/head.jsp"%>
<style type="text/css">
body {
	margin: 0;
	padding: 0;
	font-size: 12px;
	font-family: 微软雅黑;
	color: #666;
	background: url("<%=path%>/app/assets/images/bg.jpg");
	height: calc(100% - 44px);
}

#navbar_login {
	height: 40px;
	margin-top: 4px;
}

#navbar_login .navbar_login_inner {
	margin-left: 20%;
}

#navbar_login div #services_list {
	width: 110px;
	height: 100%;
	float: left;
}

#navbar_login div #services_list:hover {
	border: 1px solid #f2f2f2;
	border-radius: 3px;
	background: #f2f2f2;
}

#services_list ul {
	margin: 0;
	height: 100%;
	width: 80%;
	float: left;
	padding: 0;
}

#services_list ul li {
	list-style-type: none;
	padding: 9px 6px;
	font-size: 16px;
	height: 100%;
	display: none;
	-webkit-transition: all linear .32s;
	-moz-transition: all linear .32s;
	-ms-transition: all linear .32s;
	transition: all linear .32s;
}

#services_list ul .current {
	display: block;
}

#services_list ul li a i {
	color: #fff;
	font-style: normal;
}

#services_list ul li a i:hover {
	color: #666;
}

#services_list ul li a:hover {
	text-decoration: none;
}

#login_icon_services {
	height: 100%;
	padding-top: 12px;
}

#login_icon_services i {
	font-size: 16px;
	font-style: normal;
	color: #fff;
}

#login_icon_services i:hover {
	color: #666;
}

#links_list {
	width: 490px;
	float: left;
}

#links_list ul li {
	margin-right: 15px;
	list-style: none;
	float: left;
	width: 60px;
	height: 40px;
	text-align: center;
	padding-top: 10px;
	border: 1px solid #f2f2f2;
	border-radius: 20px;
	-webkit-transition: all linear .32s;
	-moz-transition: all linear .32s;
	-ms-transition: all linear .32s;
	transition: all linear .32s;
}

#links_list ul li:hover {
	background: #f2f2f2;
}

#links_list ul li a {
	color: #fff;
}

#links_list ul li a:hover {
	color: #666;
	text-decoration: none;
}

#title_login {
	padding-top: 30px;
}

#title_login .title_image,#title_login .title_words {
	margin: 0 auto;
	width: 600px;
	text-align: center;
}

#title_login div h2 {
	color: #fff;
	text-shadow: 0 0 6px rgba(0, 0, 0, .4);
	letter-spacing: .1em;
}

#div_login .warper_login {
	width: 750px;
	height: 276px;
	margin: 0 auto;
	margin-top: 15px;
}

#div_login .warper_login .weiwei {
	width: 390px;
	height: 276px;
	float: left;
	margin-right: 72px;
}

#div_login .warper_login .pannel_login {
	width: 262px;
	float: left;
	position: relative;
}

#div_login .warper_login .pannel_login .form-login {
	width: 304px;
	height: 244px;
}

#form_user_login ul {
	padding: 0;
}

#form_user_login ul li {
	width: 304px;
	height: 48px;
	list-style: none;
	margin-bottom: 6px;
	border-radius: 3px;
}

.bg_white {
	background: #fff;
}

#form_user_login ul li .txt {
	display: block;
	float: left;
	width: 50px;
	text-align: center;
	padding-top: 6px;
}

#form_user_login ul li .txt i {
	font-size: 32px;
	color: #66CCFF;
}

#form_user_login ul li input[type="text"],#form_user_login ul li input[type="password"] {
	height: 100%;
	width: calc(100% - 50px);
	border: none;
	border-radius: inherit;
	color: #333;
	font-size: 18px;
	padding: 6px 12px;
}

#form_user_login ul li input[type="checkbox"] {
	width: 17px;
	height: 17px;
	margin-top: 17px;
	float: left;
}

#form_user_login ul li input:focus {
	border-color: #fff;
	outline: 0;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px rgba(102, 175, 233, 0.6);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px rgba(102, 175, 233, 0.6);
}

.text_white {
	color: white;
}

#span_remember {
	display: block;
	float: left;
	padding-top: 16px;
	padding-left: 4px;
	font-size: 14px;
}

#form_user_login .submit_btn {
	height: 48px;
	font-size: 18px;
	background-color: #0096ff;
	color: #fff;
	border-radius: 3px;
}

#links_ext {
	margin-top: 20px;
}

#links_ext a {
	font-size: 14px;
	color: #fff;
	margin-right: 60px;
}

#links_ext a:hover {
	text-decoration: none;
	color: #30A5FF;
}

#login_footer {
	color: #fff;
	font-size: 12px;
	width: 100%;
	position: absolute;
	left: 0;
	bottom: 12px;
	text-align: center;
	text-shadow: 0 0 6px rgba(0, 0, 0, .4);
}

#login_footer a {
	color: #fff;
}

#login_footer a:hover {
	color: #30A5FF;
	text-decoration: none;
}

#tooltips_login {
	position: absolute;
	top: 0;
	left: 304px;
	height: 104px;
	width: 235px;
}

#tooltips_login .tooltip_item {
	height: 48px;
	margin-bottom: 6px;
	position: relative;
}

#tooltips_login .tooltip_trangle {
	position: absolute;
	width: 20px;
	float: left;
	height: 20px;
	border: 10px solid #FF0033;
	top: 15px;
	left: -8px;
	border-color: transparent #FF0033 transparent transparent;
}

#tooltips_login .tooltip_content {
	height: 48px;
	margin-left: 12px;
	background: red;
	border-radius: 3px;
	padding: 15px 12px;
	font-size: 14px;
	color: #fff;
}

.transition {
	-webkit-transition: all linear .32s;
	-moz-transition: all linear .32s;
	-ms-transition: all linear .32s;
	transition: all linear .32s;
}
</style>
</head>

<body>
	<div class="container-fluid">
		<!-- navbar -->
		<div class="row" id="navbar_login">
			<div class="navbar_login_inner">

				<!-- services -->
				<div id="services_list">
					<ul>
						<li class="current"><a href="https://www.baidu.com" target="_blank"><i class="">威锋网</i></a></li>
						<li><a href="https://www.baidu.com" target="_blank"><i class="">威锋游戏</i></a></li>
						<li><a href="https://www.baidu.com" target="_blank"><i class="">威锋商城</i></a></li>
						<li><a href="https://www.baidu.com" target="_blank"><i class="">威智网</i></a></li>
						<li><a href="https://www.baidu.com" target="_blank"><i class="">安锋网</i></a></li>
					</ul>
					<div id="login_icon_services">
						<i class="glyphicon glyphicon-th-list"></i>
					</div>
				</div>

				<!-- links -->
				<div id="links_list">
					<ul>
						<li><a href="https://www.taobao.com" target="_blank">首页</a></li>
						<li><a href="https://www.taobao.com" target="_blank">锋科技</a></li>
						<li><a href="https://www.taobao.com" target="_blank">游戏</a></li>
						<li><a href="https://www.taobao.com" target="_blank">新闻</a></li>
						<li><a href="https://www.taobao.com" target="_blank">视频</a></li>
						<li><a href="https://www.taobao.com" target="_blank">酷音乐</a></li>
					</ul>
				</div>
			</div>
		</div>

		<!-- title -->
		<div class="row" id="title_login">
			<div class="title_image">
				<img width="80" height="80" alt="title_login" src="<%=path%>/app/assets/images/logo.png" />
			</div>
			<div class="title_words">
				<h2>
					Hi,欢迎来到威锋网.
					<h2>
			</div>
		</div>

		<!--login pannel -->
		<div class="row" id="div_login">
			<div class="warper_login">
				<!-- weiweitupian -->
				<div class="weiwei">
					<img width="390" height="276" src="<%=path%>/app/assets/images/wea_d_390.png">
				</div>

				<!-- login form -->
				<div class="pannel_login">
					<div class="form_login">
						<form action="<%=path%>/login" method="post" id="form_user_login">
							<!-- 防止表单重复提交 -->
							<input type="hidden" name="user.token" id="user_token" value="56397b2e98ff8">
							<ul>
								<li class="bg_white"><span class="txt"> <i class="glyphicon glyphicon-user"></i></span> <input type="text" placeholder="用户名/邮箱" name="user.username" autofocus /></li>
								<li class="bg_white"><span class="txt"> <i class="glyphicon glyphicon-lock"></i></span><input type="password" placeholder="密码" name="user.password" /></li>
								<li><input type="checkbox" name="user.rememberMe" value="this.checked" /><span id="span_remember" class="text_white">保存登录状态(公用电脑不建议使用)</span></li>
								<li><input type="submit" value="登录" class="btn btn-block submit_btn"></li>
							</ul>
						</form>
					</div>

					<!-- ext links -->
					<div id="links_ext">
						<a class="transition" href="https://www.baidu.com" target="_blank">忘记密码？</a><a class="transition" href="https://www.baidu.com" target="_blank">注册新账号</a>
					</div>

					<!-- tooltips -->
					<div id="tooltips_login">
						<div class="tooltip_item">
							<div class="tooltip_trangle"></div>
							<div class="tooltip_content">
								<s:property value="shiroLoginFailure"></s:property>
							</div>
						</div>
						<div class="tooltip_item">
							<div class="tooltip_trangle"></div>
							<div class="tooltip_content">密码不能为空</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- footer -->
		<div id="login_footer">
			<div>
				<p>Copyright 2007-2015 © FENG.COM®. All rights reserved 保留所有权利</p>
				<p>
					<a class="transition" href="http://www.miitbeian.gov.cn/" target="_blank">沪ICP备14001835号-1 / 深公安网监备案号4403101901155</a>
				</p>
			</div>
		</div>
	</div>

	<!-- scripts -->
	<script type="text/javascript" src="<%=path%>/app/assets/js/jquery-1.11.3.js"></script>
	<script type="text/javascript" src="<%=path%>/app/assets/js/jquery-browser.js"></script>
	<script type="text/javascript" src="<%=path%>/app/assets/js/bootstrap.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#form_user_login").bind("submit", function() {
				$("#user_token").val(new Date().getMilliseconds());
			})
			//只适用于ie
			window.onbeforeunload = function() {
				alert("===onbeforeunload===");
				if (event.clientX > document.body.clientWidth && event.clientY < 0 || event.altKey) {
					alert("你关闭了浏览器");
				} else {
					alert("你正在刷新页面");
				}
			}
		})
	</script>
</body>

</html>
