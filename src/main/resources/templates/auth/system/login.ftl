<!DOCTYPE html>
<html>
<head lang="en">
	<title>MES生产执行管理系统</title>
	 <meta charset="UTF-8">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">

<meta name="renderer" content="webkit" />
<meta name="renderer" content="ie-comp">
<meta name="renderer" content="ie-stand">

<link rel="stylesheet" type="text/css" href="/css/login/index.css">
<link rel="stylesheet" href="/font/login/iconfont.css"/>
<script type="text/javascript" src="/js/jquery-1.11.1.js"></script>
<link rel="icon" href="http://misc.zhongpin.cn/ztb/images/favorice.ico" mce_href="http://misc.zhongpin.cn/ztb/images/favorice.ico" type="image/x-icon">
<style>
    #login{
        padding-left:0;
    }
</style>
</head>
<body>
<div class="header">
    <div class="header_cont">
        <div class="header_logo">
            <img src="/img/login/logo.png" width="180">
        </div>
    </div>
</div>
<div class="loginBg" style="background-image: url(/img/login/loginbg.png);filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src='/img/login/loginbg.png',sizingMethod='scale');-ms-filter: 'progid:DXImageTransform.Microsoft.AlphaImageLoader(src='<%=basePath %>images/login/loginbg.png',sizingMethod='scale')';">
    <div class="login">
        <div class="login_cont">
            <h2>欢迎登录生产执行管理系统</h2>
            <form name="loginForm" id="loginForm"  method="post">
                <div id="checkSpan" class="loginSpan" style="display: none;">
                    <img src="/img/login/error.png" alt=""/>
                    <span id="checkSpanT">请输入正确的用户名或密码!</span>
                </div>


                <div class="input_div">
                    <label class="user">
                        <i style="color:#b8b8b8" class="iconfont">&#xe66e;</i>
                    </label>
                    <input autocomplete="off" type="text" name="username" id="textfield" placeholder="请输入用户名">
                </div>
                <div class="input_div">
                    <label class="password">
                        <i style="color:#b8b8b8" class="iconfont">&#xe648;</i>
                    </label>
                    <input type="password" name="password" id="textfield2" placeholder="请输入密码"  onpaste="return false" oncontextmenu="return false" oncopy="return false" oncut="return false" autocomplete="off" />
                </div>
                <div class="input_div" style="text-align: center;">
                    <input id="login" type="button" value="登录"  style="text-align: center;"/>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="footer">
    <p>©版权所有 河南众品食业股份有限公司 Copyright  2014 豫ICP备 14018318号-1</p>
</div>
</body>
<script type="text/javascript" src="/pageJs/authLogin.js"></script>
</html>

