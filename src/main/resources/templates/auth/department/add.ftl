
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
<form id="form" action="/auth/department/addDepartment.html" method="post" style="width:98%;padding-left:20px;">
		<div class="row"style="margin-left:0px;">
	        <h6 class="col-sm-9">
            	权限管理系统>用户管理>新增工厂信息
	        </h6>
	    </div>
		<table class="table table-bordered table-hover definewidth"border="0" class="table table-bordered"
			cellpadding="0" cellspacing="1" style="border:0;width:50%;">
			<tr>
				<td style="text-align: left;width:20px;line-height:32px;">
					<div align="left"  ><font color="red">*</font>工厂名称</div></td>
				<td style="text-align: left;line-height:35px;" width="84%">
					<div align="left"    style="padding-left:10px;">
						<input style="height: 30px;width: 200px" name="name" id="name"  class="form-control"
						datatype="checkName" errormsg="用户名至少5个字符,最多18个字符！"/>
						<span class="Validform_checktip">请输入汉字。</span>
					</div>
				</td>
				
			</tr>
			<tr>
				<td>
					<div align="left"  >描述：</div></td>
				<td>
					<div align="left"   style="padding-left:10px;">
						<input style="height: 30px;width: 300px" id="description" name="description"  datatype="checkDes" errormsg="工厂描述太长！"  class="form-control" />
						<span class="Validform_checktip"></span>
					</div>
				</td>
			</tr>
		</table>
		<div style="margin-left:123px;margin-top: 10px;">
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
<script src="/js/department/add.js"></script>