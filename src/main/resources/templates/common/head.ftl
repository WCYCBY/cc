
<!--引入属于此页面的js -->
<script type="text/javascript" src="/js/mjIndex/head.js"></script>
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
<style>
	.dropdown-menu.dropdown-closer{
		top:89%;
	}
</style>



<div class="navbar navbar-inverse">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="brand" style="display:inline-block;width:150px;background:#03559f;">
            		<img src="/img/logo.png" style="width: 100px;isplay:block;margin:0 auto;">
        	</a>
        	<div style="display:inline-block" id="sidebar-collapse"><i class="icon-list"></i></div>

            <ul class="nav ace-nav pull-right" style="margin-top:4px;">

                <li class="light-blue user-profile dt">
                    <a class="user-menu" href="javascript:;">
                        <img alt="FH" src="/img/user.jpg" class="nav-user-photo" />
                        <span id="user_info" style="line-height:30px;"></span>
                        <i class="icon-caret-down"></i>
                    </a>
                    <ul id="user_menu" class="pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-closer">
                        <li><a onclick="updatePassword();" style="cursor:pointer;"><i class="icon-user"></i> 修改密码</a></li>
                        <li class="divider"></li>
                        <li><a href="/auth/login/logout.html"><i class="icon-off"></i> 退出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>
<script>
	$(function() {
	    $('.dt').hover(function(){
	    	$('#user_menu').slideDown()
	    },function(){
	    	$('#user_menu').slideUp()
	    });
	});
</script>

