
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
			<div class="row-fluid" id="cmsDiv">
				<div class="row-fluid"> 
					<form action="/mj/content/contentList.html" method="post" name="contentForm" id="contentForm">
					<input type="hidden" id="page" name="page" value="${pageInfo.pageNum}">
					<input type="hidden" id="columnId" name="columnId" value="${content.columsId!''}">
						<table>
							<tr id="app">
								<td>
									<span class="input-icon">
										<input autocomplete="off" id="title" v-model="title" type="text"  name="title" placeholder="公告标题" />
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
									<input type="button" value="新增公告" class="btn btn-small btn-primary" v-on:click="addCms" />
								</td>
							</tr>
						</table>
						
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th class="center"><i class="icon-user"></i>公告标题</th>
									<th class="center"><i class="icon-home"></i>公告连接</th>
									<th class="center"><i class="icon-time"></i>当前状态</th>
									<th class="center"><i class="icon-time"></i>是否置顶</th>
									<th class="center"><i class="icon-time"></i>操作</th>
								</tr>
							</thead>
							<tbody id="dataId">
								<!-- <#list list as ur>
									<tr>
									<td class='center' style="width: 30px;"><a>${ur.title!""}</a></td>
									<td style="width: 30px;" class="center">${ur.fileName!""}</td>
									<td style="width: 30px;" class="center">
										<#if ur.status==1>
										已发布
										<#else>
										未发布
										</#if>
									</td>
									<td style="width: 30px;" class="center">
										<#if ur.isTop==1>
										是
										<#else>
										否
										</#if>
									</td>
									
									<td style="text-align:center" class="center" width="200px;">
										<a class='btn btn-mini btn-primary'  title="编辑"   onclick="toEdit(${ur.id!''})" />
										<i class='icon-edit'></i>
										<a class='btn btn-=mini btn-primary'  title="删除"  onclick="del(${ur.id!''})" />
										<i class='icon-trash'></i>
										<button type="button" class="btn btn-mini btn-primary" data-toggle="button"  id="toedit">编辑</button>
										<button type="button" class="btn btn-mini btn-primary" data-toggle="button" id="del">删除</button>
									</td>
									</tr>
								</#list>
						 -->
						 		
										<tr v-for="ur in dataList">
										<td class='center' style="width: 30px;">{{ur.title}}</td>
										<td style="width: 30px;" class="center"><a target="_blank" v-bind:href="'http://ztb-notice.gyl.zhongpin.cn/'+[ur.fileName]">http://ztb-notice.gyl.zhongpin.cn/{{ur.fileName}}</a></td>
									
										<td style="width: 30px;" class="center">
											<font color="blue" v-if="ur.status== 1">发布中</font>
											<font color="blue" v-if="ur.status== 2">已屏蔽</font>
										</td>
										
										<td style="width: 30px;" class="center" v-if="ur.isTop==1">
											是
										</td>
										<td style="width: 30px;" class="center" v-else>
											否
										</td>
											<div class='hidden-phone visible-desktop btn-group center'  >
										<td style="text-align:center" class="center" width="200px;">
											<a href="javascript:void(0);" class="btn btn-mini btn-primary" v-on:click="toedit(ur.id)">编辑</a>
											
											<a href="javascript:void(0);" class="btn btn-mini btn-primary" v-on:click="del(ur.id)">删除</a>
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
<script type="text/javascript" src="/plugins/attention/zDialog/zDrag.js"></script>
<script type="text/javascript" src="/plugins/attention/zDialog/zDialog.js"></script>
<script type="text/javascript" src="/js/mjIndex/page.js"></script><!--分页-->
<script type="text/javascript" src="/js/mjIndex/vue.min.js"></script><!--vue.js-->
<script src="/js/bootbox.min.js"></script>
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
		$("#page").val(n);
		$("#contentForm").submit();
	}




var vm1 = new Vue({
	el: '#cmsDiv',
	data: {
		title: '${content.title!'' }',
		dataList:[
			<#list list as ur>
			{
				"id":"${ur.id !""}",
				"title":"${ur.title!""}",
				"isTop":"${ur.isTop!""}",
				"status":"${ur.status!""}",
				"fileName":"${ur.fileName!""}"
			}
			<#if ur_has_next>,</#if>
			</#list>    
		 ]
	},
	// 在 `methods` 对象中定义方法
	methods: {
		search: function() {
			// // 方法内 `this` 指向 vm
			var title = $.trim(this.title);
			$("#title").val(title);
			$("#contentForm").submit();
		},
		clear: function() {
		   this.title='';
		},
		addCms: function() {
			var locationUrl = window.location.href;
			var locationUrl  = locationUrl.split("?")[0];
			var title = $.trim(this.title);
			if(title == "" || title == undefined ||title == null){
				var url = locationUrl+"?pageNo=${pageInfo.pageNum!""}";
			}else{
				var url = locationUrl+"?title="+title+"&pageNo=${pageInfo.pageNum!""}";
			}
			var url = encodeURIComponent(url);
			window.location.href="/mj/content/toContentAdd.html?columsId=${content.columsId!""}&returnUrl="+url;
		},
		toedit: function(id){
			window.location.href="/mj/content/toContentEdit.html?columsId=${content.columsId!''}&contentId=" + id;
		},
		del: function(id){
			 bootbox.confirm("确定要删除吗！！", function (result) {  
	                if(result) {  
	                	window.location.href="/mj/content/deleteContent.html?contentId="+id;
	                }else{
	                	top.Dialog.close();
	                }
	        
	            });
		}
	}
});

</script>	
