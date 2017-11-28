
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
<form id="form" action="/auth/role/update.html" method="post" style="width:98%;padding-left:20px;">
		<input name="id" type="hidden" id="idRole" value="${role.id!'' }"/>
		<div class="row"style="margin-left:0px;">
	        <h6 class="col-sm-9">
            	权限管理系统>角色管理>角色编辑
	        </h6>
	    </div>
		<div class="control-group" style="margin-top:10px;">
		  <div class="controls">
		  	<span class="help-inline kdd">角色名：</span>
		    <input style="height: 30px;width: 200px" name="name" id="name" datatype="*" value="${role.name!''}"/>
		    <span class="help-inline">输入内容为将要显示的角色名称！</span>
		  </div>
		</div>
		 
		<div class="control-group">
		  <div class="controls">
		  <span class="help-inline kdd">角色KEY：</span>
		    <input style="height: 30px;width: 200px"  name="roleKey" id="roleKey"  value="${role.roleKey!''}" />
		    <span class="help-inline">角色key是角色的英文标示(例：sys_user)</span>
		  </div>
		</div>
		 
		<div class="control-group">
			<span class="help-inline kdd">是否禁用：</span>
		  <div class="radio inline" style="padding-left:0px;">
                <label style="display:inline-block">
                    <input name="enable" class="ace" type="radio" value="1"
						<#if role.enable == 1>checked="checked"</#if>
						<#if role.id == 1>disabled="disabled"</#if> >
                    <span class="lbl">正常</span>
                </label>
                <label style="display:inline-block">
                    <input name="enable" class="ace" type="radio" value="0"
							<#if role.enable == 0>checked="checked"</#if>
							<#if role.id == 1>disabled="disabled"</#if>>
                    <span class="lbl">禁用</span>
                </label>
            </div>
		</div>
		<div class="control-group">
		  <div class="controls">
		  	<span class="help-inline kdd">描述：</span>
		    <input style="height: 30px;width: 400px" name="description" value="${role.description!''}"/>
		  </div>
		</div>
		<div class="control-group">
		  <div class="controls">
		  	<span class="help-inline kdd">&nbsp;</span>
		    <input type="button" v-on:click="save" value="保　存" class="btn btn-mini btn-primary"/>　
			<input type="button" value="返　回" class="btn btn-mini btn-danger" v-on:click="back"/>　
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
<script src="/js/bootbox.min.js"></script>
<script src="/js/mjIndex/vue.min.js"></script>
<script type="text/javascript" src="/js/jquery.tips.js"></script>
<script>



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
						rolesName : name,
						rolesId : $("#idRole").val()
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
				   		data:{
				   			rolesKey : roleKey,
				   			rolesId :  $("#idRole").val()
				   		},
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
		},
		back:function(){
			var url = '${returnUrl!''}';
			window.location.href=url;
		}
	}
})
</script>