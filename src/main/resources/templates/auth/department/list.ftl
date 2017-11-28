<!DOCTYPE html>
<html lang="en">
<head>
<#include "../../common/top.ftl" >
</head> 
<body>
	<div class="container-fluid" id="main-container">
		<div id="page-content" class="clearfix">	
			<div class="row-fluid">
			<div class="row-fluid">
				<!-- 检索  -->
				<form id="fenye" name="fenye" action="/auth/department/queryDepartment.html" method="post">
					<input type="hidden" id="pageNo" name="pageNo">
					<table>
						<tr id="search">
							<td>
								<span class="input-icon">
								<input type="text" autocomplete="off" id="name" name="name" v-model="name" placeholder="工厂名称" />
								<i id="nav-search-icon" class="icon-search"></i>
								</span>
							</td>
							<td style="vertical-align:top;">
								<button class="btn btn-small btn-primary" v-on:click="search" title="搜索">
										搜索
									</button>
							</td>
							<td style="vertical-align:top;">
								<input type="button" value="重置" class="btn btn-small btn-primary" v-on:click="clear" />
							</td>
							<!-- <td style="vertical-align:top;">
								<input type="button" value="新增" class="btn btn-small btn-primary" v-on:click="add" />
							</td> -->
						</tr>
					</table>
				</form>
				<!-- 检索  -->
			
				<table id="table_report" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th style="text-align: center;">序号</th>
							<th style="text-align: center;">工厂名称</th>
							<th style="text-align: center;">创建人员</th>
							<th style="text-align: center;">创建时间</th>
							<th style="text-align: center;">修改人员</th>
							<th style="text-align: center;">修改时间</th>
							<th style="text-align: center;">描述</th>
							<th style="text-align: center;">操作</th>
						</tr>
					</thead>
											
					<tbody id="dataId">
						<tr v-for="(ur,$index) in dataList">
							 <td>{{$index+1}}</td>
							 <td>{{ur.name}}</td>
							 <td>{{ur.creater}}</td>
							 <td>{{ur.creatertime}}</td>
							 <td>{{ur.alterName}}</td>
							 <td>{{ur.altertime}}</td>
							 <td>{{ur.description}}</td>
							 <td class='center' style="width: 30px;">
								<div class='hidden-phone visible-desktop btn-group'>
									<a class='btn btn-mini btn-info' v-on:click="edit(ur.id)" title="编辑"><i class='icon-edit'></i></a>
									<a class='btn btn-mini btn-danger' v-on:click="del(ur.id)" title="删除"><i class='icon-trash'></i></a>
								</div>
							 </td>
						</tr>
					</tbody>
				</table>
					
				<div class="page-header position-relative">
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
		</div>
	</div>
			
	<!-- 返回顶部  -->
	<a href="javascript:;" id="btn-scroll-up" class="btn btn-small btn-inverse">
		<i class="icon-double-angle-up icon-only"></i>
	</a>
		
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
	</script>

	<script type="text/javascript">
		var vm = new Vue({
			el: '#dataId',
			data: {
				dataList:[
					<#list list as ur>
					{
						"id":"${ur.id?c !""}",
						"name":"${ur.name!""}",
						"creater":"${ur.creater!""}",
						"creatertime":"${ur.creatertime!""}",
						"alterName":"${ur.alterName!""}",
						"altertime":"${ur.altertime!""}",
						"description":"${ur.description!""}",
					}
					<#if ur_has_next>,</#if>
					</#list>
				]              
			},
			methods: {
				del: function(id) {
					bootbox.confirm("您确定要删除该条工厂信息吗？删除后不可恢复！", function(result) {
						if(result){
							$.ajax({
								async:false, 
								type : "POST",
								data:{id:id},
								url: "/auth/department/checkDepartment.html",
								dataType:'json',
								success: function(json){
									if(json){
										//删除
										window.location.href="/auth/department/deleteDepartment.html?id="+id;
									}else{
										bootbox.alert("由于该工厂下有用户关联，暂不能删除！",function(res){
											$("#fenye").submit();
										});
									}
								}
							});
						}
					})
				},
				//编辑
				edit: function(id) {
					var locationUrl = window.location.href;
					var locationUrl  = locationUrl.split("?")[0];
					var name = $.trim(vm1.name);
					var url = locationUrl+"?name="+name+"&pageNo=${pageInfo.pageNum}"; 
					var url = encodeURIComponent(url);
					window.location.href="/auth/department/toEditDepartment.html?id="+id+"&returnUrl="+url;
				}
			}
		})
		
		var vm1 = new Vue({
			el: '#search',
			data: {
				name: '${authDepartment.name!'' }',
			},
			// 在 `methods` 对象中定义方法
			methods: {
				search: function() {
					// // 方法内 `this` 指向 vm
					var name = $.trim(this.name);
					$("#name").val(name);
					$("#fenye").submit();
				},
				clear: function() {
				   this.name='';
				},
				add: function() {
					var locationUrl = window.location.href;
					var locationUrl  = locationUrl.split("?")[0];
					var name = $.trim(this.name);
					var url = locationUrl+"?name="+name+"&pageNo=${pageInfo.pageNum}";
					var url = encodeURIComponent(url);
					window.location.href="/auth/department/toAddDepartment.html?returnUrl="+url;
				}
			}
		})
	</script>	
<#include "../../common/foot.ftl" >
</body>
</html>