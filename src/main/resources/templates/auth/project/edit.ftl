
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
.table-bordered th, .table-bordered td{border:0px;}
.row{border-bottom: 1px solid #d5d5d5}
.table-hover tbody tr:hover td, .table-hover tbody tr:hover th{background:#fff;}
</style>
<!-- 日期框 -->
</head>
<body>
<form id="form" action="/auth/pManager/update.html" method="post" style="width:98%;padding-left:20px;">
		<input name="id" type="hidden" value="${authProjectQuery.id?c }"/>
		<div class="row"style="margin-left:0px;">
	        <h6 class="col-sm-9">
            	权限管理系统>项目管理>项目编辑
	        </h6>
	    </div>
		<table class="table table-bordered table-hover definewidth" style="border:0;width:45%;">
			<tr>	
					<td style="width:20px;line-height:32px;">
								<font color="red">*</font>项目名称：
					</td>
					<td style="text-align: left" width="84%">
							<input style="height: 30px;width: 200px" name="name" id="name" datatype="*" value="${authProjectQuery.name!''}"/>
							<span style="color: #999;" class="Validform_checktip">输入内容为将要显示的项目名称！</span>
					</td>
				</tr>
				<tr>	
					<td style="width:20px;line-height:32px;">
								<font color="red">*</font>项目KEY：
					</td>
					<td style="text-align: left">
							<input style="height: 30px;width: 200px" name="uniqueKey" id="uniqueKey"  disabled="disabled" value="${authProjectQuery.uniqueKey!''}" />
							<span class="Validform_checktip" style="color: #999;">不可修改的！</span>
					</td>
				</tr>
				<tr>	
					<td style="width:20px;line-height:32px;">
								<font color="red">*</font>项目URL：
					</td>
					<td style="text-align: left">
						<input style="height: 30px;width: 200px" id="url" name="url" datatype="urll" errormsg="url格式不对！" value="${authProjectQuery.url!''}"/>
						<span class="Validform_checktip" style="color: #999;">资源URL（例:www.lenglianmajia.com）</span>
					</td>
				</tr>
				
				<tr>	
					<td style="width:20px;line-height:32px;">
								<font color="red">*</font>排序编号：
					</td>
					<td style="text-align: left">
						<input style="height: 30px;width: 200px" id="orderNumber" name="orderNumber" datatype="orderNumber" errormsg="排序编号格式不对！" value="${authProjectQuery.orderNumber}"/>
						<span class="Validform_checktip" style="color: #999;">排序编号为数字。</span>
					</td>
				</tr>
				
				<tr>	
					<td style="width:20px;line-height:32px;">
								项目描述：
					</td>
					<td style="text-align: left">
						<input style="height: 30px;width: 400px" name="description" value="${authProjectQuery.description!''}"/>
					</td>
				</tr>
				<tr>	
					<td style="width:20px;line-height:32px;">
								图标：
					</td>
					<td style="text-align: left">
						
					<input type="radio"  name="icon" value="nav-home" <#if authProjectQuery.icon == 'nav-home'> checked="checked"</#if>/>
					<span class="lbl"></span>
						<img title="权限系统图片" src="/img/llmj_qx.png" width="30px" height="30px">|
						<input  type="radio"  name="icon" value="nav-glxt" <#if authProjectQuery.icon == 'nav-glxt'> checked="checked"</#if>/>
						<span class="lbl"></span>
						<img title="CMS系统图片" src="/img/llmj_glxt.png" width="30px" height="30px">|
						<input type="radio"  name="icon" value="nav-order" <#if authProjectQuery.icon == 'nav-order'> checked="checked"</#if> />
						<span class="lbl"></span>
						<img title="订单系统图片" src="/img/order.png" width="30px" height="30px">|
						<input type="radio"  name="icon" value="nav-cw"  <#if authProjectQuery.icon == 'nav-cw'> checked="checked"</#if> />
						<span class="lbl"></span>
						<img title="财务系统图片" src="/img/llmj_cw.png" width="30px" height="30px">|
						<input type="radio"  name="icon" value="nav-rz" <#if authProjectQuery.icon == 'nav-rz'> checked="checked"</#if> />
						<span class="lbl"></span>
						<img title="认证系统图片" src="/img/llmj_rz.png" width="30px" height="30px">|
						<input type="radio"  name="icon" value="nav-zy" <#if authProjectQuery.icon == 'nav-zy'> checked="checked"</#if>/>
						<span class="lbl"></span>
						<img title="资源系统图片" src="/img/llmj_zy.png" width="30px" height="30px">|
						
						<input type="radio"  name="icon" value="nav-time" <#if authProjectQuery.icon == 'nav-time' > checked="checked"</#if>/>
						<span class="lbl"></span>
						<img title="定时任务系统图片" src="/img/llmj_time.png" width="30px" height="30px">|
						
					</td>
				</tr>
		</table>
		<div style="margin-left:100px;margin-top: 10px;">
			<input type="submit" value="保　存" class="btn btn-mini btn-primary"/>　
			<input type="button" value="返　回" class="btn btn-mini btn-danger" onclick="back()"/>　　　　
		</div>
		<input id="returnUrl" type="hidden" value="${returnUrl!''}"/>
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
<script src="/js/project/edit.js"></script>