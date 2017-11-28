
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="/css/bootstrap.min.css" rel="stylesheet" />
<link href="/css/bootstrap-responsive.min.css" rel="stylesheet" />
<link rel="stylesheet" href="/css/font-awesome.min.css" />
<!-- page specific plugin styles -->
<!-- 下拉框-->
<link rel="stylesheet" href="/css/chosen.css" />
<!-- ace styles -->
<link rel="stylesheet" href="/css/ace.min.css" />
<link rel="stylesheet" href="/css/ace-responsive.min.css" />
<link rel="stylesheet" href="/css/ace-skins.min.css" />
<link rel="stylesheet" href="/css/datepicker.css" />
<style>
	 .row{border-bottom: 1px solid #d5d5d5}
	 .kdd{width:80px;}
	 input[type=radio]+.lbl::before{position:relative;top:-2px;}
</style>
<!-- 日期框 -->
</head>
<body>
<form id="form" action="/auth/role/add.html" method="post" style="width:98%;padding-left:20px;">
		<input name="type" id="type" type="hidden" value="0"/>
		
		<div class="row"style="margin-left:0px;">
	        <h6 class="col-sm-9">
            	权限管理系统>角色管理>新增角色
	        </h6>
	    </div>
		<div class="control-group" style="margin-top:10px;">
		  <div class="controls">
		  	<span class="help-inline kdd">角色名：</span>
		    <input style="height: 30px;width: 200px" name="name" id="name" datatype="*"/>
		    <span class="help-inline">输入内容为将要显示的角色名称！</span>
		  </div>
		</div>
		 
		<div class="control-group">
		  <div class="controls">
		  <span class="help-inline kdd">角色KEY：</span>
		    <input style="height: 30px;width: 200px" name="roleKey" id="roleKey" datatype="checkKey" errormsg="资源key不能为空或格式错误！" />
		    <span class="help-inline">角色key是角色的英文标示(例：sys_user)</span>
		  </div>
		</div>
		 
		<div class="control-group">
			<span class="help-inline kdd">是否禁用：</span>
		  <div class="radio inline" style="padding-left:0px;">
                <label style="display:inline-block">
                    <input name="enable" type="radio" value="1" class="ace" checked="checked">
                    <span class="lbl">正常</span>
                </label>
                <label style="display:inline-block">
                    <input name="enable" type="radio" value="0" class="ace">
                    <span class="lbl">禁用</span>
                </label>
            </div>
		</div>
		<div class="control-group">
		  <div class="controls">
		  	<span class="help-inline kdd">描述：</span>
		    <input style="height: 30px;width: 400px" name="description"/>
		  </div>
		</div>
		<div class="control-group">
		  <div class="controls">
		  	<span class="help-inline kdd">&nbsp;</span>
		    <input type="button" v-on:click="save" value="保　存" class="btn btn-mini btn-primary"/>　
			<input type="button" style="margin-left:0px;" value="返　回" class="btn btn-mini btn-danger" onclick="back()"/>
		  </div>
		</div>	
</form>
</body>
</html>
<#include "../../common/foot.ftl" >
<script src="/js/jquery-1.11.1.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/ace-elements.min.js"></script>
<script src="/js/ace.min.js"></script>
<script type="text/javascript" src="/js/jquery.tips.js"></script>
<script src="/js/bootbox.min.js"></script>
<script type="text/javascript" src="/js/mjIndex/vue.min.js"></script>
<script>

function back(){
	window.history.back(-1); 
	}

var vue = new Vue({
	el:'#form',
	methods:{
		save:function(){
			var name = $('#name').val();
			  if($("#name").val()==""){
			        $("#name").tips({
			            side:3,
			            msg:'输入角色名',
			            bg:'#AE81FF',
			            time:2
			        });
					
			        $("#name").focus();
			        return false;
			    }
			  var errorCode;
			  $.ajax({
					async : false,
					type : "POST",
					data : {
						rolesName : name
					},
					url : "/auth/role/checkRolesName.html",
					dataType : 'json',
					success : function(json) {
						errorCode = json;
					}
				});
			 	 if (errorCode == "1001") {
			 		$("#name").tips({
			            side:3,
			            msg:'角色名已存在',
			            bg:'#AE81FF',
			            time:2
			        });
			 		return false;
				}
			
				if($("#roleKey").val()==""){
			        $("#roleKey").tips({
			            side:3,
			            msg:'输入角色key',
			            bg:'#AE81FF',
			            time:2
			        });
			
			        $("#roleKey").focus();
			        return false;
			    }
				
				var roleKey = $('#roleKey').val();
					var errorCode;
					$.ajax({
				   		async:false, 
				   		type : "POST",
				   		data:{rolesKey : roleKey},
				   		url: "/auth/role/checkRolesKey.html",
				   		dataType:'json',
				   		success: function(json){
				           	errorCode=json;
				    	}
				   	});
					if(errorCode == "1001"){
						 $("#roleKey").tips({
				            side:3,
				            msg:'项目key已被使用',
				            bg:'#AE81FF',
				            time:2
				        });
						
						$("#roleKey").focus();
						return false;
					}
				
			$("#form").submit();
		}
	}
})

	
</script>