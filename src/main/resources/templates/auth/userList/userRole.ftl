
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#include "/common/top.ftl" >
</head>
<body>
<body>
	<div class="table-responsive" id="app" style="width:98%;padding-left:20px;">
	<div class="row"style="margin-left:0px;">
	        <h6 class="col-sm-9">
            	权限管理系统>用户管理>分配角色
	        </h6>
	    </div>
		<table  class="table table-bordered" style="width:15%">
			<tr>
				<td>
					<div>用户名</div>
				</td>
				<td>
					<div>
						{{userName}}
						 </div>
				</td>
			</tr>
			<tr>
				<td>
					<div>所属角色</div>
				</td>
				<td>
					<div>
						<font color="blue" v-if="roleName!=''">{{roleName}}</font>
						<font color="red" v-else>没有分配角色</font>
					</div>
				</td>
			</tr>
			
		</table>
	</div>
	<table class="table table-bordered" id="app1" style="width:40%;margin-left:20px;">
		<thead>
			<tr>
				<td >
				</td>
				<td >角色名</td>
				<td >是否禁用</span></td>
				<td >
					<span>描述</span>
				</td>
			</tr>
		</thead>
		<tbody>
			<tr v-for="(role,$index) in dataList" v-bind:class="{'':$index%2==0,'warning':$index%2==1}">
				<td height="20">
					<input  type="radio" name="roleId" v-if="userRoleId==role.id" checked="checked" v-bind:value="role.id"/>
					<input  type="radio" name="roleId" v-bind:value="role.id" v-else/>
					<span class="lbl"></span>
				</td>
				<td height="20">
					<span >{{role.name}}</span>
				</td>
				<td height="20">
					<span> 
						<font color="red" v-if="role.enable==0">禁用</font>
						<font color="blue" v-else>正常</font>
					</span>
				</td>
				<td height="20">
					<span>{{role.description}}</span>
				</td>
			</tr>
			</tbody>
		</table>			
		<tr>
			<td colspan="2" style="padding: 10px">
				<div id="app2" style="padding-left:20px;">
					<input type="button" value="保　存" class="btn btn-mini btn-primary"	 v-on:click="saveRole" /> 
					<input id="backBt" type="button" value="返     回" class="btn btn-mini btn-danger" v-on:click="back" />
				</div>
			</td>
		</tr>
		<#include "../../common/foot.ftl" >
</body>
</body>
</html>
<!-- 引入 -->
<script src="/js/jquery-1.11.1.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/ace-elements.min.js"></script>
<script src="/js/ace.min.js"></script>
<script type="text/javascript" src="/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
<script type="text/javascript" src="/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
<script type="text/javascript" src="/js/bootbox.min.js"></script><!-- 确认窗口 -->
<!-- 引入 -->
<script type="text/javascript" src="/js/jquery.tips.js"></script><!--提示框-->
<script type="text/javascript" src="/js/mjIndex/page.js"></script><!--分页-->
<script type="text/javascript" src="/js/mjIndex/vue.min.js"></script><!--vue.js-->
<script type="text/javascript">
	var returnUrl = '${returnUrl!''}';
	var vm = new Vue({
		el:"#app",
		data:{
			userName: "${user.userName!''}",
			roleName: "${user.roleName!''}"
		}
	});

	var vm1 = new Vue({
		el:"#app1",
		data:{
			dataList:[
				<#list roles as role>
					{
						"id":"${role.id?c!''}",
						"name":"${role.name!'' }",
						"enable":"${role.enable!''}",
						"description":"${role.description!''}"
					}
				<#if role_has_next>,</#if>
				</#list>
			],
			userRoleId:"${user.roleId?c!''}"
		}
	});
	var vm2 = new Vue({
		el:"#app2",
		methods:{
			back:function(){
				window.location.href=returnUrl;
			},
			saveRole:function(){
				var val = $('input:radio:checked').val();
				if(val==null){
					bootbox.alert("请选择角色", function () {
						location.reload();
		       		 });
					return;
				}
				$.ajax({
					async : false, //请勿改成异步，下面有些程序依赖此请数据
					type : "POST",
					data : {
						userId : "${user.userId?c}",
						roleId : $('input[name="roleId"]:checked').val()
					},
					url : "/auth/user/saveRole.html",
					dataType : 'json',
					success : function(data) {
						if (data.code == "") {
							bootbox.alert("分配成功！", function () {
								window.location.href=returnUrl;
		               		 });
						} else if (data == "USER_0005") {
							bootbox.confirm("分配失败！",function(result){
								if(result){
									window.location.href=returnUrl;
								}else{
									location.reload();
								}
							})
						}else{
							bootbox.alert("系统异常！");
						}
					}
				});
			}
		}
	});
	
	
</script>