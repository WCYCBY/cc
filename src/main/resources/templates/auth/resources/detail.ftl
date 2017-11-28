
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "../../common/top.ftl" >
</head>
<body>
<form action="/auth/resources/update.html" method="post" style="width:98%;padding-left:20px;">
	<input type="hidden" name="id" value="${resources.id}">
		<input type="hidden" id="parentId" name="parentId" value="${resources.parentId}">
		<div class="row"style="margin-left:0px;">
	        <h6 class="col-sm-9">
            	权限管理>资源列表>资源详情
	        </h6>
	    </div>	
		<table id="detail" class="table table-striped table-bordered table-hover definewidth" style="width: 50%">
		 <tr v-for="ur in dataList">
		 			<td width:20px;>
		 				<div align="center">1</div>
					</td>
					<td>
						<div class="STYLE1" >
								所属项目：
						</div>
					</td>
					<td >
						<div align="left" class="STYLE1"  style="padding-left:10px;padding-right: 20px;">
					${resources.projectName !''}
						</div>
					</td>
				</tr>
			<tr>	
					<td width:20px;>
		 				<div align="center">2</div>
					</td>
					<td>
						<div class="STYLE1" >
								上级资源：
						</div>
					</td>
					<td >
						<div class="STYLE1"  style="padding-left:10px;">
						<#if  resources.parentId = 0> 
						顶级菜单
						<#else> 
						<#list resLists as key>
								<#if  key.id = resources.parentId> 
							  		${key.name !''}
							  	</#if>		
						</#list>
						</#if>
						</div>
					</td>
				</tr>
			<tr>	
					<td width:20px;>
		 				<div align="center">3</div>
					</td>
					<td>
						<div class="STYLE1" >
								资源名称：
						</div>
					</td>
					<td >
						<div class="STYLE1"  style="padding-left:10px;padding-right: 20px;">
						${resources.name !''}
						</div>
					</td>
				</tr>
				<tr>	
					<td width:20px;>
		 				<div align="center">4</div>
					</td>
					<td>
						<div class="STYLE1" >
								资源KEY：
						</div>
					</td>
					<td >
						<div class="STYLE1"  style="padding-left:10px;">
							${resources.resKey !''}
						</div>
					</td>
				</tr>
				<tr>	
					<td width:20px;>
		 				<div align="center">5</div>
					</td>
					<td>
						<div class="STYLE1">
								资源URL：
						</div>
					</td>
					<td>
						<div class="STYLE1"  style="padding-left:10px;">
						${resources.resUrl !''}
						</div>
					</td>
				</tr>
				<tr>	
					<td width:20px;>
		 				<div align="center">6</div>
					</td>
					<td>
						<div class="STYLE1" >
								资源类型：
						</div>
					</td>
					<td >
						<div class="STYLE1"  style="padding-left:10px;">
						<#if  resources.type == '0'> 
						  目录
						<#elseif  resources.type =='1'> 
						  菜单
						<#elseif  resources.type == '2'> 
						  按扭
						<#else> 
						  按扭
						</#if>
						</div>
					</td>
				</tr>
				<tr>	
					<td width:20px;>
		 				<div align="center">7</div>
					</td>
					<td>
						<div class="STYLE1" >
								优先级：
						</div>
					</td>
					<td >
						<div class="STYLE1"  style="padding-left:10px;">
						${resources.level !''}
						</div>
					</td>
				</tr>
				<tr>	
					<td width:20px;>
		 				<div align="center">8</div>
					</td>
					<td>
						<div class="STYLE1" >
								资源描述：
						</div>
					</td>
					<td >
						<div class="STYLE1"  style="padding-left:10px;">
						${resources.description !''}
						</div>
					</td>
				</tr>
		</table>
		<div>
			 <input id="backBt" type="button" value="返　回" class="btn btn-mini btn-primary" onclick="back()"/>
		</div>
			<#include "../../common/foot.ftl" >
</form>
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
   //返回
     	function back(){
     		var url = '${returnUrl}';
     		window.location.href=url;
     	}
</script>
	</body>
</html>