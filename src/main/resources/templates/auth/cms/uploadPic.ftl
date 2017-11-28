
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "../../common/top.ftl" >
</head>
<style type="text/css">

#mask {
	display: none;
	position: absolute;
	top: 60%;
	left: 50%;
	width: 420px;
	padding: 15px 20px;
	line-height: 30px;
	margin-left: -160px;
	border: 2px solid #FFF;
	background-color: #FFF;
	z-index: 1002;
	font-family:"微软雅黑";
	border-radius: 5px;
}
#mask div {
	padding-bottom:20px;
}

.popMask_black_con{
  display: none;
  width: 100%;
  height: 100%;
  background: #000;
  background: rgba(0,0,0,.5);
  filter: alpha(opacity=50);
  position: fixed;
  _position: absolute;
  z-index: 999;
  left: 0;
  top: 0;
}
._mask_upload{
	margin-left:55px;
}
.mot_btn_box {
	padding: 0px 10px;
	height: 30px;
	line-height: 30px;
	border: 1px solid #d6d6d6;
	border-radius: 5px;
	background: #fff;
	vertical-align: middle;
	display: inline-block;
	margin-right: 10px;
	font-size: 12px;
	cursor: pointer;
	_height: 25px;
	_line-height: 25px;
	_padding-top: 6px;
}
</style>
<div id="mask_bg"></div>
<div id="mask"><!-- 弹出的上传文件对话框 -->
	<form action="/cms/columnContentCtl/upload.html" id="maskForm"
		  method="POST" enctype="multipart/form-data" target="hidden_frame">
		<table>
			<tr>
				<td>选择文件：</td>
				<td>
					<input type="file" name="file" id="file" style="width:190px;"/>
				</td>
			</tr>
			<tr>
				<td>图片连接：</td>
				<td>
				<input style="width:180px;" type="text"   name="linkUrl" id="linkUrl" placeholder="如:http://www.baidu.com"/>
				</td>
			</tr>
		</table>
		<input type="hidden" name="outputWidth" id="outputWidth"/>
		<input type="hidden" name="outputHeight" id="outputHeight"/>
		<input type="hidden" name="path" id="path"/>
		<input type="hidden" name="zoom" id="zoom"/>
		<input type="hidden" name="type" id="filetype"/>
		<a href="javascript:;"  class="mot_btn_box _mask_upload">保存</a>
		<a href="javascript:;"  class="mot_btn_box" onclick="hidMask();">取消</a>
	</form>
	<iframe style="display: none" name='hidden_frame' id="hidden_frame"></iframe>
</div>
<!-- 此处是搞什么用：遮罩层 -->
<div class="popMask_black_con"></div>
</html>
		<script type="text/javascript">
$(function() {
		$("#filetype").val($("#pictype").val());
		
		var temp = "goodsResource|carResource|warehouseResource";
		//------  绑定 提交form事件 
		$("._mask_upload").on("click", function() {
			if(!$("#file").val()){
				if(!$("#path").val()){
					bootbox.alert("请选择图片！");
				}else{
					setValues();//将图片路径和图片连接地址添加到对应的图片位里边
					//当path有值得时候表示修改数据，这个时候不需要在上传一次图片直接关闭
					hidMask()
				}
				return;
			}
			
			//限制图片上传格式
			var nowFile=$("#file").val();
			if(!nowFile.endWith(".jpg") && !nowFile.endWith(".png") && !nowFile.endWith(".gif")){
				bootbox.alert("上传失败,仅支持jpg,png,gif格式上传！",function(res){
					if(res){
						return;
					}
				});
			}
			
			 var reg=/^(http|https):\/\/[A-Za-z0-9-]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\:+!]*([^<>])*$/;
			 
			 if($("#linkUrl").val()!='如:http://www.baidu.com') 
				 if(!reg.test($("#linkUrl").val())&&$("#linkUrl").val()!=""){
					 bootbox.alert("链接不合法！");
					  return;
				  }
			
		
			$("#maskForm").submit();//提交form 上传文件
		});
	});
	
	/**
	 * 弹出对话框方法
	 * @param hid
	 * @param ad
	 */
	function myMask() {
		var top = ($(window).height() - $("#mask").height()) / 2;
		var scrollTop = $(document).scrollTop();
		$("#mask").css("top", parseInt(top + scrollTop) + "px");
		$("#mask").fadeIn(200);
		$(".popMask_black_con").show();
	}
	/**
	 * 遮罩层隐藏、返回服务端执行结果到特定元素
	 * 服务端返回会调用该函数方法
	 * @param msg  		服务端返回字符串说明
	 * @param stat 		上传请求是否成功  etc true:成功  false :失败
	 */
	function callback(msg) {
		if (msg) {
			$("#path").val(msg);
		}else if(msg=="nopic"){
			
		}else {
			bootbox.alert("上传失败,支持jpg,jpeg,bmp,png格式上传！");
		}
		setValues();//将图片路径和图片连接地址添加到对应的图片位里边
		hidMask()
	}
	
	String.prototype.endWith=function(endStr){
		  var d=this.length-endStr.length;
		  return (d>=0&&this.lastIndexOf(endStr)==d)
		}
	
	/**
	 * 隐藏弹出框  方法
	 */
	function hidMask(){
		$(".popMask_black_con").hide();
		$("#maskForm")[0].reset();//重置form
		$("#mask").fadeOut(200);
	}
		
	  
</script>