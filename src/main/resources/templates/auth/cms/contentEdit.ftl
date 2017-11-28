
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
<form id="form" action="/mj/content/editContent.html" method="post" style="width:98%;padding-left:20px;">
	<input name="columsId" id="columsId" type="hidden" value="${content.columsId}"/>
	<input name="id" id="id" type="hidden" value="${content.id}"/>
		<table class="table table-hover definewidth m10">
				<tr>	
					<td style="text-align: left;" width="120">
								<font color="red">*</font>标题：
					</td>
					<td style="text-align: left">
							<input style="height: 30px;width: 200px" value="${content.title!''}" name="title" id="title" datatype="*" errormsg="标题不能为空"/>
							<span style="color: #999;" class="Validform_checktip">（请输入英文小写字母或下划线）</span>
					</td>
				</tr>
				<tr>	
					<td  style="text-align: left;" >
								<font color="red">*</font>内容：
					</td>
					<td style="text-align: left" height="300px">
							<div style="width: 1000px"  id="content" dataType="content" errormsg="内容不能为空！" nullmsg="内容不能为空！">
								<script id="container" class="" name="content" type="text/plain">
${content.content!''}
								</script>	
							</div>
				 			<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>	
					<td  style="text-align: left;" >
								<font color="red">*</font>状态：
					</td>
					<td style="text-align: left">
							<input name="status" type="radio" value="1" class="ace" <#if content.status == 1> checked="checked"</#if>><span class="lbl"></span>发布中
							<input name="status" type="radio" value="2" class="ace" <#if content.status == 2> checked="checked"</#if>><span class="lbl"></span>已屏蔽
					</td>
				</tr>
				<tr>	
					<td  style="text-align: left;" >
								<font color="red">*</font>是否置顶：
					</td>
					<td style="text-align: left">
							<input name="isTop" type="radio" value="1" class="ace" <#if content.isTop == 1> checked="checked"</#if> /><span class="lbl"></span>是
							<input name="isTop" type="radio" value="2" class="ace" <#if content.isTop == 2> checked="checked"</#if> /><span class="lbl"></span>否
					</td>
				</tr>
				
		</table>
		<div style="margin-left:159px;">
			<input type="button" v-on:click="save" value="保　存" class="btn btn-mini btn-primary"/>　
			<input type="button" v-on:click="preview" value="预	览" class="btn btn-mini btn-primary"/>　
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
<script type="text/javascript" src="/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="/js/ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="/js/ueditor/lang/zh-cn/zh-cn.js"></script>
<script>
$(function () {     

	var ue = UE.getEditor('container');

	var vue = new Vue({
		el:'#form',
		methods:{
			save:function(){
				var name = $('#title').val();
				  if($("#title").val()==""){
				        $("#title").tips({
				            side:3,
				            msg:'标题不能为空',
				            bg:'#AE81FF',
				            time:2
				        });
						
				        $("#title").focus();
				        return false;
				    }
				  	if(!ue.hasContents()){
				  		$("#container").tips({
				            side:3,
				            msg:'内容不能为空',
				            bg:'#AE81FF',
				            time:2
				        });
					}
				$("#form").submit();
			},
			preview:function(){
				var name = $('#title').val();
				  if($("#title").val()==""){
				        $("#title").tips({
				            side:3,
				            msg:'标题不能为空',
				            bg:'#AE81FF',
				            time:2
				        });
						
				        $("#title").focus();
				        return false;
				    }
				  	if(!ue.hasContents()){
				  		$("#container").tips({
				            side:3,
				            msg:'内容不能为空',
				            bg:'#AE81FF',
				            time:2
				        });
					}
				  	$.ajax({
		     			url:"/mj/content/previewContent.html",
		     			type:'POST',
		     			data: $("#form").serialize(),
		     			success:function(ret){
		         				//window.open("http://www.baidu.com");
		     				window.open(ret,"name","features")

		     			}
		     		});
			}
	
	
		}
	});
});
function back(){
	window.history.back(-1); 
	}
	
	
</script>