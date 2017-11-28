var locat = (window.location+'').split('/');
$(function(){
	if('main'== locat[3]){
		locat =  locat[0]+'//'+locat[2];
	}else{
		locat =  locat[0]+'//'+locat[2]+'/'+locat[3];
	};
})


//菜单状态切换
var fmid = "fhindex";
var mid = "fhindex";
function siMenu(id,fid,MENU_NAME,MENU_URL){
	
	document.getElementById("mainFrame").contentWindow.doubiTab();
	
    if(id != mid){
        $("#"+mid).removeClass();
        mid = id;
    }
    if(fid != fmid){
        $("#"+fmid).removeClass();
        fmid = fid;
    }
    $("#"+fid).attr("class","active open");
    $("#"+id).attr("class","active");
    top.mainFrame.tabAddHandler(id,MENU_NAME,MENU_URL);
    if(MENU_URL != "druid/index.html"){
        jzts();
    }
}



var USER_ID;


$(function(){
    $.ajax({
        type: "POST",
        url: '/auth/background/getUname.html?tm='+new Date().getTime(),
        data: encodeURI(""),
        dataType:'json',
        cache: false,
        success: function(data){
                //登陆者资料
                $("#user_info").html('<span>欢迎</span> '+data.loginUserName+'');
            }
    });
});



//修改个人密码
function updatePassword(){
    var diag = new top.Dialog();
    diag.Drag=true;
    diag.Title ="修改密码";
    diag.URL = '/auth/user/goEditPassword.html';
    diag.Width = 225;
    diag.Height = 132;
    diag.CancelEvent = function(){ //关闭事件
        diag.close();
    };
    diag.show();
}


//菜单
function menu(){
    jzts();
    var diag = new top.Dialog();
    diag.Drag=true;
    diag.Title ="菜单编辑";
    diag.URL = locat+'/menu.do';
    diag.Width = 720;
    diag.Height = 390;
    diag.CancelEvent = function(){ //关闭事件
        diag.close();
    };
    diag.show();

}

//切换菜单
function changeMenu(){
    websocket.send('[changeMenu]'+user);
}

//清除加载进度
function hangge(){
    $("#jzts").hide();
}

//显示加载进度
function jzts(){
    $("#jzts").show();
}