
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
					<form action="/mj/column/columnList.html" method="post" name="roleForm" id="roleForm">
					<input type="hidden" id="pageNo" name="pageNo">
						<table>
							<tr id="app">
								<td>
									<span class="input-icon">
										<input autocomplete="off" id="note" v-model="note" type="text"  name="note" placeholder="版块说明" />
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
									<input type="button" value="新增板块" class="btn btn-small btn-primary" v-on:click="addCms" />
								</td>
							</tr>
						</table>
						
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th class="center"><i class="icon-user"></i>板块名称</th>
									<th class="center"><i class="icon-tag"></i>板块说明</th>
									<th class="center"><i class="icon-home"></i>板块类型</th>
									<th class="center"><i class="icon-time"></i>是否已发布</th>
									<th class="center"><i class="icon-time"></i>操作</th>
								</tr>
							</thead>
							<tbody id="dataId">
								
									<tr v-for="(cms,$index) in dataList">
										<td class='center' style="width: 30px;"><a>{{cms.name}}</a></td>
										<td style="width: 30px;" class="center">{{cms.note}}</td>
									
										<td style="width: 30px;" class="center">
											<font color="blue" v-if="cms.type == 1">图片</font>
											<font color="blue" v-if="cms.type == 2">文字</font>
										</td>
										
										<td style="width: 30px;" class="center" v-if="cms.isrelease==1">
											已发布
										</td>
										<td style="width: 30px;" class="center" v-else>
											未发布
										</td>
											<div class='hidden-phone visible-desktop btn-group center'  >
										<td style="text-align:center" class="center" width="200px;">
											<a href="javascript:void(0);" class="btn btn-mini btn-primary" v-on:click="toedit(cms.id)">编辑模板</a>
											
											<a href="javascript:void(0);" class="btn btn-mini btn-primary"  v-if="cms.type == 1" v-on:click="toEdit(cms.id,cms.upperLimitPic,cms.lowerLimitPic )">编辑数据</a>
											<a href="javascript:void(0);" class="btn btn-mini btn-primary"  v-else="cms.type == 2" v-on:click="toContentEdit(cms.id )">编辑数据</a>
											
											<a href="javascript:void(0);" class="btn btn-mini btn-primary" v-on:click="previewOrrelease(cms.id,1)">预览</a>
											<a href="javascript:void(0);" class="btn btn-mini btn-primary" v-on:click="previewOrrelease(cms.id,2)">发布</a>
											<a href="javascript:void(0);" class="btn btn-mini btn-primary" v-on:click="deleteColumn(cms.id)">删除</a>
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
		$("#pageNo").val(n);
		$("#roleForm").submit();
	}
 	
var note = '${note!''}' 	
var dataVm = new Vue({
	el:'#cmsDiv',
	data:{
		note:note,
		dataList:[
			<#list column as ur>
			{
				"id":"${ur.id?c !""}",
				"name":"${ur.name!""}",
				"note":"${ur.note!""}",
				"type":"${ur.type!""}",
				"upperLimitPic":"${ur.upperLimitPic!""}",
				"lowerLimitPic":"${ur.lowerLimitPic!""}",
				"isrelease":"${ur.isrelease!""}"
				
			}
			<#if ur_has_next>,</#if>
			</#list>    
		 ]
	},
	methods:{
		addCms:function(){
			window.location.href="/mj/column/toAdd.html";	
		},
		toEdit: function(id,upperLimitPic,lowerLimitPic) {
						window.location.href = "/cms/columnContentCtl/toEdit.html?columnId="+id+"&upperLimitPic="+upperLimitPic+"&lowerLimitPic="+lowerLimitPic; 
					},
		search:function(){
			
		},
		clear: function() {
           $('#note').val('');
           
            this.uniqueKey='';
         },
         toedit:function(id){
        	 window.location.href="/mj/column/getById.html?id=" + id;
         },
         toContentEdit:function(id){
        	 window.location.href="/mj/content/contentList.html?columnId=" + id + "&page=" +1;
         },
         deleteColumn:function(id){
        	 bootbox.confirm("确定要删除吗！！", function (result) {  
	                if(result) {  
	                	window.location.href="/mj/column/deleteById.html?id="+id;
	                }else{
	                	top.Dialog.close();
	                }
	        
	            });
        	 
         },
         previewOrrelease: function(id,type){
         	$.ajax({
     			url:"/mj/cmsPublishCtl/publishOrPreview.html",
     			type:'POST',
     			data:{
     				columnId :id,
     				type:type
     			},
     			success:function(ret){
     				if(ret.code == 200){
     					if(type == 2){
         					bootbox.confirm("发布成功，是否跳转页面？",function(result){
         						if(result){
         							window.open(ret.url);	
     								window.location.reload();
         						}else{
         							window.location.reload();
         						}
         					})
         					
         				}else{
         					bootbox.confirm("预览成功，是否跳转页面？",function(result){
         						if(result){
         							window.open(ret.url);	
     								window.location.reload();
         						}else{
         							window.location.reload();
         						}
         					})
         				}
     				}else {
     					bootbox.alert("发布失败");
     				}
     			}
     		});
         	
         }
	}
	
})
	




</script>	
