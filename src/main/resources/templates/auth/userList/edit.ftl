
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
<!-- 日期框 -->
<style>
.table-bordered th, .table-bordered td{border:0px;}
.row{border-bottom: 1px solid #d5d5d5}
.table-hover tbody tr:hover td, .table-hover tbody tr:hover th{background:#fff;}
</style>
</head>
<body>
<form id="form" action="/auth/user/update.html" method="post" style="width:98%;padding-left:20px;">
		
		<div class="row"style="margin-left:0px;">
	        <h6 class="col-sm-9">
            	权限管理系统>用户管理>编辑用户
	        </h6>
	    </div>
		<table id="app" class="table table-bordered table-hover definewidth m10" style="border:0;width:50%;">
				<input name="userId" type="hidden" v-bind:value="userId"/>
				<tr>	
					<td style="text-align: left;width:20px;line-height:32px;">
						<font color="red">*</font>用户名：
					</td>
					<td style="text-align: left;line-height:35px;" width="84%">
						<span style="padding-left:10px;">{{userName}}</span>
						<input type="radio" name="userSex" value="1" v-if="userSex==1" checked="checked"/>
						<input type="radio" name="userSex" value="1" v-if="userSex!=1"/>
							<span class="lbl">先生</span>
						<input type="radio" name="userSex" value="0" v-if="userSex==0" checked="checked"/>
						<input type="radio" name="userSex" value="0" v-if="userSex!=0"/>
							 <span class="lbl">女士</span>
					</td>
				</tr>
				<tr>
					<td style="text-align: left;width:73px;line-height:32px;">
						<div align="left"  >密码：</div></td>
					<td>
						<div align="left"   style="padding-left:10px;">
							<input style="margin-bottom:0px;border-color:#ddd;height:26px;width: 190px" name="userPassWord" id="userPassWord" class="form-control"
								type="password"  ignore="ignore"  />
                        <span style="color: #999;" class="Validform_checktip">密码范围在5~16位之间(如不填默认为原密码！)</span>
						</div>
					</td>
				</tr>
				<tr>
					<td style="text-align: left;width:73x;line-height:32px;">
						<div align="left"  ><font color="red">*</font>所属工厂：</div></td>
					<td>
						<div align="left" style="padding-left:10px;width: 200px;height: 30px;" >
							<select  name="department" style="width: 203px;height: 35px;">
								<option v-for="dept of deptList" v-if="dept.id==department" selected  v-bind:value="dept.id">{{ dept.name }}</option>
           						<option v-for="dept of deptList" v-if="dept.id!=department"  v-bind:value="dept.id">{{ dept.name }}</option>
							</select>
						</div>
					</td>
				</tr>	
				<tr>
					<td style="text-align: left;width:73px;line-height:32px;">
						<div align="left"  ><font color="red">*</font>昵称：</div></td>
					<td>
						<div align="left"   style="padding-left:10px;">
							<input style="height:30px;width: 200px" name="userNickName" id="userNickName" v-bind:value="userNickName" class="form-control"
							/>
							<span class="Validform_checktip">昵称为2~18个字符，可以为汉字、拼音、数字。</span>
						</div></td>
				</tr>
				<tr>
					<td style="text-align: left;width:73px;line-height:32px;">
						<div align="left"  ><font color="red">*</font>真实姓名：</div></td>
					<td>
						<div align="left"   style="padding-left:10px;">
							<input style="height:30px;width: 200px" name="userRealname" id="userRealname" v-bind:value="userRealname" class="form-control"
								/>
							<span class="Validform_checktip">真实姓名为2~18个字符，可以为汉字、拼音、数字。</span>
						</div></td>
					
				</tr>
				<tr>
					<td style="text-align: left;width:73px;line-height:32px;">
						<div align="left"  ><font color="red">*</font>电话：</div></td>
					<td>
						<div align="left"   style="padding-left:10px;">
							<input style="height:30px;width: 200px" name="userPhone" id="userPhone" v-bind:value="userPhone" class="form-control"
							 id="userPhone" maxlength="11"
							/>
							 <span class="Validform_checktip">如：15012528693</span>
						</div></td>
				</tr>
				
		</table>
		<div style="margin-left:120px;margin-top: 10px;"id="app1">
			<input value="保　存" type="button" v-on:click="saveUser" class="btn btn-mini btn-primary"/>　
			<input type="button" value="返　回" v-on:click="back" class="btn btn-mini btn-danger"/>　　　　
		</div>
</form>
<#include "../../common/foot.ftl" >
</body>
</html>
<script src="/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="/js/jquery.tips.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/ace-elements.min.js"></script>
<script src="/js/ace.min.js"></script>
<script src="/js/bootbox.min.js"></script>
<script type="text/javascript" src="/js/mjIndex/vue.min.js"></script><!--vue.js-->
<script>
	var vm = new Vue({
		el:"#app",
		data:{
			userId:"${user.userId?c!''}",
			userName: "${user.userName!''}",
			userSex:"${user.userSex?c!''}",
			userNickName:"${user.userNickName!''}",
			userRealname:"${user.userRealname!''}",
			userPhone:"${user.userPhone!''}",
			department:"${user.department?c!''}",
			deptList:[
				<#list deptList as dept>
					{
						"id":"${dept.id?c!''}",
						"name":"${dept.name!'' }"
					}
				<#if dept_has_next>,</#if>
				</#list>
			]
		}
	});
	
	var vm1 = new Vue({
		el:"#app1",
		methods:{
			saveUser:function(){
				if($("#userPassWord").val().length!=0){
					if($("#userPassWord").val().length<5||$("#userPassWord").val().length>16){
				        $("#userPassWord").tips({
				            side:3,
				            msg:'密码范围在5~16位之间',
				            bg:'#AE81FF',
				            time:2
				        });
						
				        $("#userPassWord").focus();
				        return;
				    }
				}
				
				if($("#userNickName").val().length<2||$("#userNickName").val().length>18){
			        $("#userNickName").tips({
			            side:3,
			            msg:'真实姓名为2~18个字符',
			            bg:'#AE81FF',
			            time:2
			        });
			
			        $("#userNickName").focus();
			        return;
			    }
				if($("#userRealname").val().length<2||$("#userRealname").val().length>18){
			        $("#userRealname").tips({
			            side:3,
			            msg:'昵称为2~18个字符',
			            bg:'#AE81FF',
			            time:2
			        });
			
			        $("#userRealname").focus();
			        return;
			    }
				
				var userPhone = $("#userPhone").val();
				var partten = /^1[3,5,4,8,7,9]\d{9}$/;
				if (!partten.test(userPhone)) {
					$("#userPhone").tips({
			            side:3,
			            msg:'手机格式不正确，请重新输入',
			            bg:'#AE81FF',
			            time:2
			        });
			
			        $("#userPhone").focus();
			        return;
				}
				$("form").submit();
			},
			back:function(){
				var url = '${returnUrl!''}';
				window.location.href=url;
			}
		}
	});
	
</script>