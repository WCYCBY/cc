
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
<form id="form" action="/auth/pManager/addData.html" method="post" style="width:98%;padding-left:20px;">
		<input name="type" id="type" type="hidden" value="0"/>
		<div class="row"style="margin-left:0px;">
	        <h6 class="col-sm-9">
            	权限管理系统>项目管理>项目新增
	        </h6>
	    </div>
		<table class="table table-bordered table-hover definewidth m10" style="border:0;width:45%;">
			<tr>	
					<td style="width:20px;line-height:32px;">
								<font color="red">*</font>项目名称：
					</td>
					<td style="text-align: left" width="84%">
							<input style="height: 30px;width: 200px" name="name" id="name" datatype="*"/>
							<span style="color: #999;" class="Validform_checktip">输入内容为将要显示的项目名称！</span>
					</td>
				</tr>
				<tr>	
					<td  style="width:20px;line-height:32px;" >
								<font color="red">*</font>项目KEY：
					</td>
					<td style="text-align: left">
							<input style="height: 30px;width: 200px" name="uniqueKey" id="uniqueKey" datatype="checkKey" errormsg="资源key不能为空或格式错误！" />
							<span class="Validform_checktip" style="color: #999;">项目的英文标示！</span>
					</td>
				</tr>
				<tr>	
					<td style="width:20px;line-height:32px;" >
								<font color="red">*</font>项目URL：
					</td>
					<td style="text-align: left">
						<input style="height: 30px;width: 200px" id="url" name="url" datatype="urll" errormsg="url格式不对！"/>
						<span class="Validform_checktip" style="color: #999;">资源URL（例:www.lenglianmajia.com）</span>
					</td>
				</tr>
				
				<tr>	
					<td style="width:20px;line-height:32px;">
								<font color="red">*</font>排序编号：
					</td>
					<td style="text-align: left">
						<input style="height: 30px;width: 200px" id="orderNumber" name="orderNumber" datatype="orderNumber" errormsg="排序编号格式不对！" />
						<span class="Validform_checktip" style="color: #999;">排序编号为数字。</span>
					</td>
				</tr>
				
				
				<tr>	
					<td style="width:20px;line-height:32px;">
								项目描述：
					</td>
					<td style="text-align: left">
						<input style="height: 30px;width: 400px" name="description"/>
					</td>
				</tr>
				<tr>	
					<td style="width:20px;line-height:32px;">
								图标：
					</td>
					<td  style="text-align: left">
						<input type="radio" class="ace"  name="icon" value="nav-home"/>
						<span class="lbl"></span>
						<img title="权限中心" src="/img/qx.png" width="30px" height="30px">|
						
						<input type="radio" class="ace"   name="icon" value="qx.png"/>
						<span class="lbl"></span>
						<img title="会员中心" src="/img/hy.png" width="30px" height="30px">|
						
						<input type="radio" class="ace"    name="icon" value="hy.png"/>
						<span class="lbl"></span>
						<img  title="订单中心" src="/img/dd.png" width="30px" height="30px">|
						
						<input type="radio" class="ace"    name="icon" value="dd.png"/>
						<span class="lbl"></span>
						<img title="资源中心" src="/img/zy.png" width="30px" height="30px">|
						<input type="radio" class="ace"    name="icon" value="zy.png"/>
						<span class="lbl"></span>
						<img title="发布中心" src="/img/cms.png" width="30px" height="30px">|
						<input type="radio" class="ace"    name="icon" value="cms.png"/>
						<span class="lbl"></span>
						<img title="消息中心" src="/img/xx.png" width="30px" height="25px">|
						<input type="radio" class="ace"    name="icon" value="xx.png"/>
					</td>
				</tr>
		</table>
		<div style="margin-left:100px;margin-top: 10px;">
			<input type="submit" value="保　存" class="btn btn-mini btn-primary"/>　
			<input type="button" style="margin-left:0px;" value="返　回" class="btn btn-mini btn-danger" onclick="back()"/>
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
<script src="/js/project/add.js"></script>