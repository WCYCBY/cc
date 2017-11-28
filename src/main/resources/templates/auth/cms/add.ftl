
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
.table th, .table td{border:0;}
</style>
</head>
<body>
<form id="form" action="/mj/column/addColumn.html" method="post" style="width:98%;padding-left:20px;">
		<div class="row"style="margin-left:0px;">
	        <h6 class="col-sm-9">
            	权限管理系统>CMS管理>新增版块
	        </h6>
	    </div>
		<table class="table table-hover definewidth m10" style="width: 45%;">
				<tr>	
					<td style="text-align: left;">
								<font color="red">*</font>版块名称：
					</td>
					<td style="text-align: left" width="74%">
							<input style="height: 30px;width: 200px" name="name" id="name" datatype="*" errormsg="版块名称不能为空"/>
							<span style="color: #999;" class="Validform_checktip">（请输入英文小写字母或下划线）</span>
					</td>
				</tr>
				<tr>	
					<td  style="text-align: left;" >
								<font color="red">*</font>版块说明：
					</td>
					<td style="text-align: left">
							<input style="height: 30px;width: 200px" name="note" id="note" datatype="checkKey" errormsg="版块说明不能为空" />
							<span class="Validform_checktip" style="color: #999;">请输入中文说明</span>
					</td>
				</tr>
				<tr>	
					<td  style="text-align: left;" >
								<font color="red">*</font>版块类型：
					</td>
					<td style="text-align: left">
							<input name="type" type="radio" value="1" class="ace" checked="checked"><span class="lbl"></span>图片
							<input name="type" type="radio" value="2" class="ace" checked="checked"><span class="lbl"></span>新闻类型
					</td>
				</tr>
				
				<tr>	
					<td  style="text-align: left;">
								<font color="red">*</font>图片数量下限：
					</td>
					<td style="text-align: left">
						<input style="height: 30px;width: 200px" name="lowerLimitPic" id="lower" datatype="checkKey" errormsg="版块说明不能为空" />
					</td>
				</tr>
				<tr>	
					<td  style="text-align: left;">
								<font color="red">*</font>图片数量上限：
					</td>
					<td style="text-align: left">
						<input style="height: 30px;width: 200px" name="upperLimitPic" id="upper" datatype="checkKey" errormsg="版块说明不能为空" />
					</td>
				</tr>
				<tr>	
					<td  style="text-align: left;">
						<font color="red">*</font>板块模板(ftl)：
					</td>
					<td style="text-align: left">
							<textarea name="ftl" id="ftl" style="height:100px; weight:400px;" rows="10" cols="134" datatype="*" nullmsg="板块模板不能为空"></textarea>
					</td>
				</tr>
				
		</table>
		<div style="margin-left:159px;">
			<input type="button" v-on:click="save" value="保　存" class="btn btn-mini btn-primary"/>　
			<input type="button" style="margin-left:0px;" value="返　回" class="btn btn-mini btn-danger" onclick="back()"/>
		</div>
		
		
</form>
</body>
</html>
<#include "../../common/foot.ftl" >
<script src="/js/jquery-1.11.1.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/ace-elements.min.js"></script>
<script src="/js/ace.min.js"></script>
<script type="text/javascript" src="/js/jquery.tips.js"></script>
<script src="/js/bootbox.min.js"></script>
<script type="text/javascript" src="/js/mjIndex/vue.min.js"></script>
<script>

function back(){
	window.history.back(-1); 
	}

var vue = new Vue({
	el:'#form',
	methods:{
		save:function(){
			var name = $('#name').val();
			  if($("#name").val()==""){
			        $("#name").tips({
			            side:3,
			            msg:'版块名称不能为空',
			            bg:'#AE81FF',
			            time:2
			        });
					
			        $("#name").focus();
			        return false;
			    }
			  var en_ = /^[a-z_]{1,30}$/;
			  
			  if(!en_.test(name)){
				  $("#name").tips({
			            side:3,
			            msg:'版块名称由小写英文字母或下划线组成！最大长度为30位！',
			            bg:'#AE81FF',
			            time:2
			        });
				  $("#name").focus();
			        return false;
				}
			  
			  var errorCode;
			  $.ajax({
					async : false,
					type : "POST",
					data : {
						name : name
					},
					url : "/mj/column/columnNameCheck.html",
					dataType : 'json',
					success : function(json) {
						errorCode = json;
					}
				});
			 	 if (errorCode == "1111") {
			 		$("#name").tips({
			            side:3,
			            msg:'版块名称已存在',
			            bg:'#AE81FF',
			            time:2
			        });
			 		return false;
				}
				if($("#note").val()==""){
			        $("#note").tips({
			            side:3,
			            msg:'版块说明不能为空',
			            bg:'#AE81FF',
			            time:2
			        });
			
			        $("#note").focus();
			        return false;
			    }
				
				
				
				
				if($("#lower").val()=="" & $("input[name='type']:checked").val()==1){
			        $("#lower").tips({
			            side:3,
			            msg:'图片数量下限不能为空',
			            bg:'#AE81FF',
			            time:2
			        });
			
			        $("#lower").focus();
			        return false;
			    }
				if($("#upper").val()=="" & $("input[name='type']:checked").val()==1){
			        $("#upper").tips({
			            side:3,
			            msg:'图片数量上限不能为空',
			            bg:'#AE81FF',
			            time:2
			        });
			
			        $("#upper").focus();
			        return false;
			    }
				
				var lowerLimit=$("#lower").val();
				var upperLimit=$("#upper").val();
				var pattern=/^[1-9]{1,1}$/;
				if(!pattern.test(lowerLimit) & $("input[name='type']:checked").val()==1){
					 $("#lower").tips({
				            side:3,
				            msg:'请输入合法数字！最小下限为1',
				            bg:'#AE81FF',
				            time:2
				        });
					 return false;
				}
				if(!pattern.test(upperLimit) & $("input[name='type']:checked").val()==1){
					 $("#upper").tips({
				            side:3,
				            msg:'请输入合法数字！最大上限为9！',
				            bg:'#AE81FF',
				            time:2
				        });
					 return false;
				}
				if(lowerLimit>upperLimit & $("input[name='type']:checked").val()==1){
					$("#lower").tips({
			            side:3,
			            msg:'图片数量下限不能大于上限！',
			            bg:'#AE81FF',
			            time:2
			        });
					return false;
				}
				
				
				
				if($("#ftl").val()==""){
			        $("#ftl").tips({
			            side:3,
			            msg:'ftl模板不能为空',
			            bg:'#AE81FF',
			            time:2
			        });
			
			        $("#ftl").focus();
			        return false;
			    }
				
				
				
			$("#form").submit();
		}
	}
})

	
</script>