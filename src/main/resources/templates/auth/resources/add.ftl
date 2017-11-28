
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "../../common/top.ftl" >
	<style>
	 .row{border-bottom: 1px solid #d5d5d5}
	 .kdd{width:80px;}
	 input[type=radio]+.lbl::before{position:relative;top:-2px;}
	 select{    width: 203px;height: 36px;margin-bottom: 0px;}
</style>
</head>
<body>
<form id="form" action="/auth/resources/add.html" method="post" style="width:98%;padding-left:20px;">
		<input name="type" id="type" type="hidden" value="0"/>
		<input name="initKey" id="initKey" type="hidden">
		
		
		<div class="row"style="margin-left:0px;">
	        <h6 class="col-sm-9">
            	权限管理系统>资源管理>添加资源
	        </h6>
	    </div>
		<div class="control-group" style="margin-top:10px;">
		  <div class="controls">
		  	<span class="help-inline kdd"><font color="red">*</font>所属项目：</span>
		    <select id="proId" name="proId" datatype="checkPro" errormsg="请选择所属项目！"  onchange="changePro()">
							<option value="0" selected>--请选择--</option>
							  	<#list projects as item>
							  		<option value = "${item.id?c }" key="${item.uniqueKey!''  }">${item.name!'' }</option>
							  	</#list>
						</select>
					<span class="help-inline Validform_checktip">资源所属的项目！！</span>
		  </div>
		</div>
		<div class="control-group">
			<span class="help-inline kdd"><font color="red">*</font>资源类型：</span>
			  <div class="radio inline" style="padding-left:0px;">
		        <label style="display:inline-block">
		            <input type="radio" name="typeT" value=0><span class="lbl">目录</span>
		    		<span class="lbl">正常</span>
				</label>
				<label style="display:inline-block">
				    <input type="radio" name="typeT"  value=1><span class="lbl">菜单</span>
				    <span class="lbl">禁用</span>
				</label>
				<label style="display:inline-block">
			    <input type="radio" name="typeT"  value=2><span class="lbl">按钮</span>
			        <span class="lbl">禁用</span>
			    </label>
			</div>
			<span class="help-inline Validform_checktip">资源类型！</span>
		</div>
		<div class="control-group">
		  <div class="controls">
		  	<span class="help-inline kdd"><font color="red">*</font>上级资源：</span>
		    <select id="parentId" name="parentId" datatype="*" errormsg="资源父节点不能为空！" onblur="visableType()" onclick="clickMenu()" onchange="changeMenu()">
							<option value="0" type="-1" selected="selected">顶级菜单</option>
						</select>
			<span class="Validform_checktip help-inline">资源的父节点！</span>
		  </div>
		</div>
		<div class="control-group">
		  <div class="controls">
		  	<span class="help-inline kdd"><font color="red">*</font>资源名称：</span>
		    <input style="height: 30px;width: 200px" id="name" name="name" datatype="*"/>
			<span class="Validform_checktip help-inline">资源名称为将要显示的菜单名称！</span>
		  </div>
		</div>
		<div class="control-group">
		  <div class="controls">
		  	<span class="help-inline kdd"><font color="red">*</font>资源KEY：</span>
		    <input style="height: 30px;width: 200px" name="resKey" id="resKey" datatype="en_" errormsg="资源key不能为空或格式错误！" />
				<span class="Validform_checktip help-inline" id="resourceKey">资源KEY是资源的唯一标识!</span>
		  </div>
		</div>
		<div class="control-group">
		  <div class="controls">
		  	<span class="help-inline kdd"><font color="red">*</font>资源URL：</span>
		    <input style="height: 30px;width: 200px" id="resUrl" name="resUrl" datatype="urll" errormsg="url格式不对！"/>
			<span class="Validform_checktip help-inline">资源url（例：/auth/resources/add.html）</span>
		  </div>
		</div>
		<div class="control-group">
		  <div class="controls">
		  	<span class="help-inline kdd"><font color="red">*</font>优先级：</span>
		    <input style="height: 30px;width: 200px" id="level" name="level" datatype="n" errormsg="优先级不能为空，且必须是数字！"/>
			<span class="help-inline Validform_checktip">请填写数字！</span>
		  </div>
		</div>
		<div class="control-group">
		  <div class="controls">
		  	<span class="help-inline kdd">资源描述：</span>
		    <input style="height: 30px;width: 200px" name="description"/>
			<span class="help-inline Validform_checktip">请填写数字！</span>
		  </div>
		</div>
		<div class="control-group">
		  <div class="controls">
		  	<span class="help-inline kdd">&nbsp;</span>
		    <input type="submit" value="保　存" class="btn btn-mini btn-primary"/>　
			<input type="button" style="margin-left:0px;" value="返　回" class="btn btn-mini btn-danger" onclick="back()"/>
		  </div>
		</div>
		
		
		
</form>
<#include "../../common/foot.ftl" >
</body>
</html>
		
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="/js/bootstrap.min.js"></script>
		<script src="/js/ace-elements.min.js"></script>
		<script src="/js/ace.min.js"></script>
		<script type="text/javascript" src="/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<!-- 引入 -->
		<script type="text/javascript" src="/js/jquery.tips.js"></script><!--提示框-->
		<script type="text/javascript" src="/js/mjIndex/page.js"></script><!--分页-->
		<script type="text/javascript" src="/js/mjIndex/vue.min.js"></script><!--vue.js-->
		<script src="/js/resources/add.js"></script><!--校验-->
		<script type="text/javascript">
	    var vm1=new Vue({  
        el:'#radio1',  
        data: {  
            0:'主菜单',  
            1:'菜单',  
            2:'按钮'
      }  
    });  
    	//返回
	function back(){
		var url = '${returnUrl}';
 		window.location.href=url;
	}
	$("input:radio[name='typeT']").focus(function(){
			var proId = $("select[name='proId'] option:selected").val();
			if(proId == 0){
				//提示请先选择所属项目
				bootbox.alert("请先选择所属项目！");
				return;
			}
	});

$("input:radio[name='typeT']").change(function(){
	var key = $("select[name='proId'] option:selected").attr("key");
	$("#initKey").val(key);
	initResourceList();
});


function visableType(){
		var obj=$("select[name='parentId'] option:selected");
		var val=parseInt(obj.attr("type"));
		$("#type").val(parseInt(val)+1);
}

function changePro(){
		var parentId = $("#parentId");
		parentId.html("<option value='0' type='-1' selected='selected'>顶级菜单</option>");
		$("input:radio[name='typeT']").attr("checked",false);
	}

function clickMenu(){
	var proId = $("select[name='proId'] option:selected").val();
	if(proId == 0){
		//提示请先选择所属项目
		bootbox.alert("请先选择所属项目！");
		return;
}

var typeT = $("input:radio[name='typeT']:checked").val();
		if(typeT == undefined){
			bootbox.alert("请先选择资源类型！");
			return;
			}
}


function changeMenu(){
		var val=$("select[name='parentId'] option:selected").attr("resKey");
		if(val != undefined){
			$("#initKey").val(val);
		}else{
			var key = $("select[name='proId'] option:selected").attr("key");
			$("#initKey").val(key);
		}
}

//设置提示
function initResourceList(){
var typeT = $("input:radio[name='typeT']:checked").val();
var parentId = $("#parentId");
if(typeT == 0){
	parentId.html("<option value='0' type='-1' selected='selected'>顶级菜单</option>");
	return;
}
parentId.html("");
var proId = $("select[name='proId'] option:selected").val();
$.ajax({
	url:"/auth/resources/queryByProId.html",
	type:"POST",
	async:false,
	data:{"proId":proId,type:typeT},
	dataType:"json",
	success:function(data){
		for(var i=0;i<data.length;i++){
			if(i==0){
				parentId.append("<option selected='selected' value='"+data[i].id+"' type='"+data[i].type+"' resKey='"+data[i].resKey+"'>"+data[i].name+"</option>");
				//
				visableType();
				changeMenu();
			}else{
				parentId.append("<option value='"+data[i].id+"' type='"+data[i].type+"' resKey='"+data[i].resKey+"'>"+data[i].name+"</option>");
			}
		}
	}
});
}
	
</script>