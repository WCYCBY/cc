
<!DOCTYPE html>
<html lang="en">
<head>
	<#include "/common/top.ftl" >
</head>
<body>
	<div class="container-fluid" id="main-container">
		<div id="page-content" class="clearfix">
			<div class="row-fluid">
				<div class="row-fluid">
					<form action="/auth/user/query.html" method="post" id="fenye" name="fenye">
						<input type="hidden" id="pageNo" name="pageNo">
						<table>
							<tr id="vmSearch">
								<td>
									<span class="input-icon">
										<input  type="text" autocomplete="off" id="userName" v-model="userName" name="userName" value="" placeholder="用户名" />
										<i id="nav-search-icon" class="icon-search"></i>
									</span>
								</td>
								<td>
									<span class="input-icon">
										<input  type="text" autocomplete="off" id="userNickName" v-model="userNickName" name="userNickName" value="" placeholder="昵称" />
										<i id="nav-search-icon" class="icon-search"></i>
									</span>
								</td>
								<td style="vertical-align:top;">
									<button class="btn btn-small btn-primary" v-on:click="search" title="搜索">
										搜索
									</button>
								</td>
								<td style="vertical-align:top;">
									<input type="button" value="重置" class="btn btn-primary btn-small" v-on:click="clear" />
								</td>
								<td style="vertical-align:top;">
									<a class="btn btn-small btn-danger" v-on:click="addUser">新增</a>
								</td>
							</tr>
						</table>
						
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th class="center">用户名</th>
									<th class="center"><i class="icon-user"></i>所属角色</th>
									<th class="center"><i class="icon-tag"></i>昵称</th>
									<th class="center"><i class="icon-home"></i>所属工厂</th>
									<th class="center"><i class="icon-time"></i>注册时间</th>
									<th class="center"><i class="icon-time hidden-phone"></i>最后一次登录时间</th>
									<th class="center"><i class="icon-asterisk"></i>基本操作</th>
								</tr>
							</thead>
							<tbody id="dataId">
								<tr v-for="user in dataList">
									<td class="center">
										<a>{{user.userName}}</a>
									</td>
									<td class="center">{{user.roleName}}</td>
									<td class="center">{{user.userNickName}}</td>
									<td class="center">{{user.departmentName}}</td>
									<td class="center">{{user.regtime}}</td>
									<td class="center">{{user.lastLogintime}}</td>
									<div class='hidden-phone visible-desktop btn-group'>
										<td style="text-align: center;">
											<a class="btn btn-mini btn-primary" href="javascript:void(0);" v-on:click="userRole(user.userId)">分配角色</a>
											<a class='btn btn-mini btn-info' title="编辑" v-on:click="edit(user.userId)">
												<i class='icon-edit'></i>
											</a>
											<a class='btn btn-mini btn-danger' title="删除" v-on:click="del(user.userId,user.userName)">
												<i class='icon-trash'></i>
											</a>
										</td>
									</div>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
				<table style="width:100%;">
					<tr>
						<td style="vertical-align:top;">
							<div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">
							
							</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<#include "../../common/foot.ftl" >
</body>
</html>

<!-- 引入 -->
<script type="text/javascript">window.jQuery || document.write("<script src='/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
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
	$(top.hangge());
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
		$("#fenye").submit();
	}
	
	
	var vmSearch = new Vue({
        el: '#vmSearch',
        data: {
            userName:"${user.userName!'' }",
            userNickName:"${user.userNickName!''}"
        },
        methods: {
            search: function() {
                var userName = $.trim(this.userName);
                var userNickName = $.trim(this.userNickName);
                $("#userName").val(userName);
                $("#userNickName").val(userNickName);
                $("#fenye").submit();
            },
            clear: function() {
                this.userName='';
                this.userNickName='';
            },
            addUser:function(){
				var locationUrl = window.location.href;
				locationUrl  = locationUrl.split("?")[0];
				var userName = $.trim(vmSearch.userName);
				var userNickName = $.trim(vmSearch.userNickName);
				var url = locationUrl+"?userName="+userName+"&userNickName="+userNickName+"&pageNo=${pageInfo.pageNum}"; 
				url = encodeURIComponent(url);
				window.location.href="/auth/user/toAddPage.html?returnUrl="+url;
			}
        }
    });
	

	
	var vmList = new Vue({
		el: "#dataId",
		data: {
			dataList:[
				<#list userlist as user>
					{
						"userId":"${user.userId?c!''}",
						"userName":"${user.userName!'' }",
						"roleName":"${user.roleName!''}",
						"userNickName":"${user.userNickName!''}",
						"departmentName":"${user.departmentName!''}",
						"regtime":"${(user.regtime?string("yyyy-MM-dd dd hh:mm:ss"))!''}",
						"lastLogintime":"${(user.lastLogintime?string("yyyy-MM-dd hh:mm:ss"))!''}"
					}
					<#if user_has_next>,</#if>
				</#list>
			],
			loginUser: {
				userName: "${loginUser.userName!''}"
			}
		},
		methods:{
			//编辑
			edit:function(userId) {
				var locationUrl = window.location.href;
				locationUrl  = locationUrl.split("?")[0];
				var userName = $.trim(vmSearch.userName);
				var userNickName = $.trim(vmSearch.userNickName);
				var url = locationUrl+"?userName="+userName+"&userNickName="+userNickName+"&pageNo=${pageInfo.pageNum}"; 
				url = encodeURIComponent(url);
				window.location.href="/auth/user/edit.html?userId="+userId+"&returnUrl="+url+"&type=1";
			},
			//删除
			del:function(userId,userName){
				if(userName == vmList.loginUser.userName){
					bootbox.alert("当前登录用户不可删除！");
					return false;
				}else if(userId == 1){
					bootbox.alert("超级管理员不可删除！");
					return false;
				}else{
					bootbox.confirm("您确定要删除该条记录吗？删除后不可恢复",function(result){
						if(result){
							window.location.href="/auth/user/deleteById.html?userId="+userId;
						}
					})
				}
			},
			//分配角色
			userRole:function(userId){
				if (userId == 1) {
					bootbox.alert("超级管理员角色不可修改！");
					return false;
				}
				var locationUrl = window.location.href;
				locationUrl  = locationUrl.split("?")[0];
				var userName = $.trim(vmSearch.userName);
				var userNickName = $.trim(vmSearch.userNickName);
				var url = locationUrl+"?userName="+userName+"&userNickName="+userNickName+"&pageNo=${pageInfo.pageNum}"; 
				url = encodeURIComponent(url);
				window.location.href='/auth/user/toUserRole.html?userId='+userId+"&returnUrl="+url;
			}
		}
	});
	
	
	
</script>