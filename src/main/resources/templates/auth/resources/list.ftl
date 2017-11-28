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
			<form id="fenye" name="fenye" action="/auth/resources/list.html" method="post">
				<input type="hidden" id="pageNo" name="pageNo">
				<table>
					<tr id="search">
					<td><div style="margin-top:-10px;">项目名称：</div></td>
						<td>   
									<select id="proId" name = "proId">
										<option value="0">--请选择--</option>
										<#list projects as item>
											<#if  item.checkFlag == true>
													<option value = "${item.id?c}" selected>${item.name }</option>
											<#else>
                                                    <option value = "${item.id?c}">${item.name }</option>
											</#if>
										</#list>
									</select>
						</td>
								<td>
								<span class="input-icon">
								<input  type="text" autocomplete="off" id="name" v-model="name" name="name" value="" placeholder="资源名称" />
								<i id="nav-search-icon1" class="icon-search"></i></span></td>
						</td>
						<td style="vertical-align:top;">
									<button class="btn btn-small btn-primary" v-on:click="search" title="搜索">
										搜索
									</button>
								</td>
						<td style="vertical-align:top;">
									<input type="button" value="重置" class="btn btn-small btn-primary" v-on:click="clear" />
						</td>
					   <td style="vertical-align:top;">
									<input type="button" value="新增资源" class="btn btn-small btn-primary" v-on:click="add" />
						</td>
						   <td style="vertical-align:top;">
									<input type="button" value="全部删除" class="btn btn-small btn-primary" v-on:click="deleteByIds" />
						</td>
					</tr>
				</table>
			</form>
			<!-- 检索  -->
			<table id="dataId" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th class="center">
						<label><input type="checkbox" id="choseAll" name="checkbox" onclick="selectAllCheckBox()" /><span class="lbl"></span></label>
						</th>
						<th  style="text-align: center;width: 80px;" >项目名称</th>
						<th  style="text-align: center;width: 100px;" >资源名称</th>
						<th  style="text-align: center;width: 300px;" >资源KEY</th>
						<th  style="text-align: center;width: 300px;" >资源URL</th>
						<th  style="text-align: center;width: 50px;" >类型</th>
						<th  style="text-align: center;width: 50px;" >优先级</th>
						<th  style="text-align: center;width: 80px;" >上级资源</th>
						<th  style="text-align: center;width: 80px;" >操作</th>
					</tr>
				</thead>
										
				<tbody >
					  <tr v-for="ur in dataList">
					  	 <td class='center' style="width: 30px;"><label><input type='checkbox' name='check' v-bind:value="ur.id" /><span class="lbl"></span></label></td>
					 	 <td style="text-align: left;" >{{ ur.projectName}}</td>
					 	 <td style="text-align: left;" >{{ur.name}}</td>
					 	 <td style="text-align: left;word-break:break-all; word-wrap:break-all;" v-bind:title ="ur.resKey" >{{ur.resKey}}</td>
					 	 <td style="text-align: left;word-break:break-all; word-wrap:break-all;" v-bind:title ="ur.resUrl" >{{ur.resUrl}}</td>
					 	 <td v-show="ur.type == 0" style="text-align: center;" ><font color="red">目录</font></td>
					 	 <td v-show="ur.type == 1" style="text-align: center;" ><font color="blue">菜单</font></td>
					 	 <td v-show="ur.type >= 2" style="text-align: center;" >按钮</td>
					 	 <td style="text-align: center;" >{{ur.level}}</td>
					 	  <td v-show="ur.parentName =='' " style="text-align: left;" >顶级菜单</td>
					 	  <td v-show="ur.parentName !='' " style="text-align: left;" >{{ur.parentName}}</td>
					 	 <td class='center' style="text-align: center;width: 30px;" >
					 	 	<div class='hidden-phone visible-desktop btn-group'>
						 	 	<a style="text-align: center;"  class='btn btn-mini btn-info' title="详情" v-on:click="detail(ur.id,0 )" ><i class="icon-zoom-in"></i></a>
						 	 	<a style="text-align: center;"  class='btn btn-mini btn-info' title="编辑"  v-on:click="detail(ur.id,1 )"><i class='icon-edit'></i></a>
						 	 	<a style="text-align: center;"  class='btn btn-mini btn-danger' v-on:click="deleteResourcesById(ur.id)" title="删除"><i class='icon-trash'></i></a>
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
	                        "id":"${ur.id?c!""}",
	                        "name":"${ur.name!""}",
	                        "parentId":"${ur.parentId!""}",
	                        "parentName":"${ur.parentName!""}",
	                        "resKey":"${ur.resKey!""}",
	                        "type":"${ur.type!""}",
	                        "resUrl":"${ur.resUrl!""}",
	                        "level":"${ur.level?c}",
	                        "description":"${ur.description!""}",
	                        "proId":"${ur.proId!""}",
	                        "projectName":"${ur.projectName!""}",
	                    }<#if ur_has_next>,</#if>
	                    </#list>
                    ]
	            },
				methods: {
					//编辑
					detail: function(id,typeKey) {
						var locationUrl = window.location.href;
						var locationUrl  = locationUrl.split("?")[0];
						var name =$.trim(vm1.name);
						var url = locationUrl+"?name="+name+"&pageNo=${pageInfo.pageNum}"; 
						var url = encodeURIComponent(url);
						window.location.href = "/auth/resources/getById.html?resourcesId="+id+"&typeKey="+typeKey+"&returnUrl="+url; 
					},
					deleteResourcesById: function(id) {//单个删除
						bootbox.confirm("删除会删除它本身和子节点，你确定要删除吗！删除后不可恢复！",function(result) {
					             if(result)
								 $.post("/auth/resources/deleteById.html?resourcesId="+id,function(data){
									if(data==false){
										bootbox.alert("删除失败，当前资源正在被某个角色使用！");
									}else{
										 	location.href="/auth/resources/list.html";
									}
								 });
								});
					}
					
				}
})
	        
	        
	 var vm1 = new Vue({
        el: '#search',
        data: {
            name: '${resources.name!'' }',
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
               var proId = $("#proId");
               var str = "";
               proId.find("option").each(function () {
				   str += "<option value='"+$(this).val()+"'>"+$(this).text()+"</option>"
				   $(this).remove();
               });
               proId.append(str);

            },
            add: function() {  //新增资源
              	var locationUrl = window.location.href;
				var locationUrl  = locationUrl.split("?")[0];
				var name =$.trim(vm1.name);
				var url = locationUrl+"?name="+name+"&pageNo=${pageInfo.pageNum}"; 
				window.location.href="/auth/resources/toAdd.html?returnUrl="+url;	
            },
            deleteByIds: function() {  //全部删除
		         var ids="";
				 $("#dataId").find(":checkbox:checked").each(function(){
				       var val = $(this).val();
				       if(!('on' == val)){
					       ids+=val+",";
				       }
				  });
				if(ids==""){
					bootbox.confirm(" 请选择你要删除的项！");
				}else{
					bootbox.confirm("你确定要全部删除吗！删除后不可恢复！",function(result){
						if(result){
						$.post("/auth/resources/deleteAll.html?idStr="+ids,function(json){
							bootbox.alert(json.json,function(result){
						    	location.href="/auth/resources/list.html";
							})
						});
					  }
				   });
				};
            }
        }
    })
    //全选或者全不选
function selectAllCheckBox(){
				var chose;
				if(document.getElementById("choseAll").checked){
					chose = document.getElementById("choseAll").checked;
				}
				var checkboxArray = document.getElementsByName("check");
				if(checkboxArray != null){
					for(var i = 0; i < checkboxArray.length; i++){
						checkboxArray[i].checked = chose;
					};
				};
			}

//点击删除时是否有勾选
function result(){
		var checks = document.getElementsByName("check");
		for(var i = 0; i < checks.length; i++){
			if(checks[i].checked == true){
				return false;
			};
		};
	}
		</script>
		<#include "../../common/foot.ftl" >
	</body>
</html>