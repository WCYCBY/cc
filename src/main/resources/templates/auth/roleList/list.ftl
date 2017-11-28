
<!DOCTYPE html>
<html lang="en">
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
</head>
<body>
	<div class="container-fluid" id="main-container">
		<div id="page-content" class="clearfix">
			<div class="row-fluid">
				<div class="row-fluid">
					<form action="/auth/role/query.html" method="post" name="roleForm" id="roleForm">
					<input type="hidden" id="pageNo" name="pageNo">
						<table>
							<tr id="app">
								<td>
									<span class="input-icon">
										<input autocomplete="off" id="name" v-model="name" type="text"  name="name" placeholder="角色名" />
										<i id="nav-search-icon" class="icon-search"></i>
									</span>
								</td>
								
								<td style="padding-bottom:12px;">
									<button class="btn btn-small btn-primary" v-on:click="search" title="搜索">
										搜索
									</button>
								</td>
								
								<td style="padding-bottom:12px;">
									<input type="button" value="重置" class="btn btn-small btn-primary" v-on:click="clear" />
								</td>
								<td style="padding-bottom:12px;">
									<input type="button" value="新增角色" class="btn btn-small btn-primary" v-on:click="addRole" />
								</td>
							</tr>
						</table>
						
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th class="center">角色名</th>
									<th class="center"><i class="icon-user"></i>角色KEY</th>
									<th class="center"><i class="icon-tag"></i>是否禁用</th>
									<th class="center"><i class="icon-home"></i>描述</th>
									<th class="center"><i class="icon-time"></i>操作</th>
								</tr>
							</thead>
							<tbody id="dataId">
								
									<tr v-for="(role,$index) in dataList">
										<td class='center' style="width: 30px;"><a>{{role.name}}</a></td>
										<td style="width: 30px;" class="center">{{role.roleKey}}</td>
									
										<td style="width: 30px;" class="center">
											<font color="red" v-if="role.enable == 0">禁用</font>
											<font color="blue" v-else>正常</font>
										</td>
										
										<td style="width: 30px;" class="center">{{role.description}}</td>
						
											<div class='hidden-phone visible-desktop btn-group center'  >
										<td style="text-align:center" class="center" width="200px;">
												<a class='btn btn-mini btn-info' title="编辑" v-on:click="edit(role.id,role.roleKey)">
												<i class='icon-edit'></i>
												</a>
												<!-- <a class='btn btn-mini btn-info' title="您不能编辑">
													<i class='icon-edit'></i>
												</a> -->
												<!-- <a class='btn btn-mini btn-danger' title="不能删除">
													<i class='icon-trash'></i>
												</a> -->
												<a class='btn btn-mini btn-danger' title="删除" v-on:click="beforeDelete(role.id,role.name,'/auth/role/deleteById.html?roleId='+role.id)"><i class='icon-trash'></i></a>
					
												<button type="button" class="btn btn-mini btn-primary" data-toggle="button" v-on:click="permissioPro(role.id,role.roleKey)">分配项目权限</button>
												<button type="button" class="btn btn-mini btn-primary" data-toggle="button" v-on:click="permissio(role.id,role.roleKey)">分配菜单权限</button>
										</td>
											</div>
									</tr>
								
							</tbody>
						</table>
						
						<table style="width:100%;">
							<tr>
								<td style="vertical-align:top;">
									<div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">
										
									</div>
								</td>
							</tr>
						</table>
					</form>
					
					<input type="hidden" id="perRoleId" name="perRoleId"/>
				 	<div id="ccR" >
					 	<div id="content" class="hide" style='width:100%; min-width:600px; height:600px;min-height:600px; margin:5px;  overflow:auto; '>
					 		<ul id='tree1'></ul> 
					 	</div>
				 	</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<#include "../../common/foot.ftl" >
<script src="/js/jquery-1.11.1.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/ace-elements.min.js"></script>
<script src="/js/ace.min.js"></script>
<script src="/js/bootbox.min.js"></script>
<script type="text/javascript" src="/js/mjIndex/page.js"></script><!--分页-->
<script type="text/javascript" src="/js/mjIndex/vue.min.js"></script><!--vue.js-->
<script type="text/javascript">
$(function(){
    $(".pagination").createPage({
        totalPage:${pageInfo.pages},
        currPage:${pageInfo.pageNum},
        rowCount:${pageInfo.total},
        backFn:function(p){
           //console.log("回调函数："+p);
           queryLine(p);
        }
    });
   
})
	 function queryLine(n){
		n = isNaN(n)?1:n;
		$("#pageNo").val(n);
		$("#roleForm").submit();
	}
	
	 var name = '${name!''}';
	  var vm = new Vue({
	        el: '#app',
	        data: {
	            name: name
	        },
	        // 在 `methods` 对象中定义方法
	        methods: {
	            search: function() {
	                // // 方法内 `this` 指向 vm
	                var name = $.trim(this.name);
	                $("#name").val(name);
	                $("#roleForm").submit();
	            },
	            clear: function() {
	               this.name='';
	               this.uniqueKey='';
	            },
	            addRole:function(){
	            	window.location.href="/auth/role/addUI.html";	
	            }
	        }
	    }) 	
	 
	  
	  var dataVm = new Vue({
		  el:'#dataId',
		  data: {
			  	name:'${nowUser.userName!''}',
			  	nowURoleId:'${nowUser.roleId!''}',
				dataList:[
					<#list pageView as ur>
						{
							"id":"${ur.id?c !""}",
							"name":"${ur.name!""}",
							"roleKey":"${ur.roleKey!""}",
							"enable":"${ur.enable!""}",
							"description":"${ur.description!""}",
							
						}
						<#if ur_has_next>,</#if>
					</#list>
				]              
			},
		methods:{
			edit:function (id,roleKey){
					 var username = '${nowUser.userName}';
						if(roleKey == 'admin' && username != 'admin'){
							bootbox.alert("你不能操作超级管理员角色！");
							return;
						}
					
				 	var url = encodeURIComponent(window.location.href);
				 	window.location.href="/auth/role/getById.html?roleId="+id+"&returnUrl="+url;
				},
			beforeDelete:function(id,name,url){
				if(id != 1){
					var flag = false;
				 	 $.ajax({
				 		url:"/auth/role/checkRolesIsUsed.html",
				 		type:"POST",
				 		async:false,
				 		data:{"rolesId":id},
				 		success:function(rs){
				 			if(rs == 0){
				 				flag = true;
				 			}else{
				 				//alert("有 用 户 正 在 使 用 该 角 色 ， 不 允 许 删 除 ！");
				 				bootbox.alert('有 用 户 正 在 使 用 该 角 色 ， 不 允 许 删 除 ！');
				 			}
				 		},
				 		dataType:"json"
				 	}); 
				 	if(flag){
				 		window.location.href=url;
				 	}
				}else{
					 bootbox.alert(name+"不可删除！");
				}
			},
			permissioPro:function(id,roleKey){
				var username = this.name;
				var nowURoleId = this.nowURoleId;
				if(roleKey == 'admin' && username != 'admin'){
					bootbox.alert("你不能操作超级管理员角色！");
					return;
				}
				
				var diag = new top.Dialog();
			    diag.Drag=true;
			    diag.Title ="分配项目权限";
			    diag.URL = '/auth/resources/permissioRoleProject.html?roleId='+id + "&nowURoleId=" + nowURoleId;
			    diag.Width = 500;
			    diag.Height = 700;
			    diag.CancelEvent = function(){ //关闭事件
			        diag.close();
			    };
			    diag.show();
			},
			permissio:function(id,roleKey){
				var username = this.name;
				if(roleKey == 'admin' && username != 'admin'){
					bootbox.alert("你不能操作超级管理员角色！！");
					return;
				}
				var diag = new top.Dialog();
			    diag.Drag=true;
			    diag.Title ="分配菜单权限";
			    diag.URL = '/auth/resources/permissioRole.html?roleId='+id;
			    diag.Width = 500;
			    diag.Height = 700;
			    diag.CancelEvent = function(){ //关闭事件
			        diag.close();
			    };
			    diag.show();
			}
			
			
		}
		  
	  })




</script>	
