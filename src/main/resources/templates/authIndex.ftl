<!DOCTYPE html>
<html lang="en">
<head>
    <title>MES生产执行管理系统</title>
    <!-- ftl文件头和头部 -->
    <#include "common/top.ftl"/>
    <link rel="icon" href="//misc.zhongpin.cn/ztb/images/favorice.ico" mce_href="//misc.zhongpin.cn/ztb/images/favorice.ico" type="image/x-icon">
    <style type="text/css">
        .commitopacity{position:absolute; width:100%; height:100px; background:#7f7f7f; filter:alpha(opacity=50); -moz-opacity:0.8; -khtml-opacity: 0.5; opacity: 0.5; top:0px; z-index:99999;}
    </style>
</head>
<body>

<!-- 页面顶部¨ -->
<#include "common/head.ftl"/>


<div class="container-fluid" id="main-container">
    <a href="#" id="menu-toggler"><span></span></a>
    <!-- menu toggler -->

    <!-- 左侧菜单 -->
    <#include "common/left.ftl"/>

    <div id="main-content" class="clearfix" style="background:#ecf0f1">


        <div>
            <iframe name="mainFrame" id="mainFrame" frameborder="0" src="/auth/background/tab.html" style="margin:0 auto;width:100%;height:100%;"></iframe>
        </div>

        <!-- 换肤 -->
        <div id="ace-settings-container" class="hide">
            <div class="btn btn-app btn-mini btn-warning" id="ace-settings-btn">
                <i class="icon-cog"></i>
            </div>
            <div id="ace-settings-box" style="height: 35px;">
                <div>
                    <label><input type='checkbox' name='menusf' id="menusf"
                                  onclick="menusf();" /><span class="lbl" style="padding-top: 5px;">菜单缩放</span></label>
                </div>
            </div>
        </div>

    </div>
</div>
<!-- 底部菜单 -->
    <#include "common/foot.ftl"/>


<!-- basic scripts-->
<script type="text/javascript">window.jQuery || document.write("<script src='/js/jquery-1.11.1.js'>\x3C/script>");</script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/ace-elements.min.js"></script>
<script src="/js/ace.min.js"></script>


<script type="text/javascript" src="/js/jquery.cookie.js"></script>

<script type="text/javascript" src="/js/mjIndex/menusf.js"></script>

<!--引入index.js   -->
<script type="text/javascript" src="/js/mjIndex/index.js"></script>
</body>
</html>
