
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" href="/css/cms/style.css" />
<link rel="stylesheet" type="text/css" href="/css/cms/validFormStyle.css" />
<link rel="stylesheet" type="text/css" href="/css/cms/bui-min.css"/>
	<#include "../../common/top.ftl" >
	<style>
		.row{border-bottom: 1px solid #d5d5d5}
	</style>
</head>
<body>
<form id="formData" style="width:98%;padding-left:20px;">
		<input type="hidden" id="id" name="id" value="${column.id }" /> 
		<input type="hidden" name="name" value="${column.name }" />
		<#if  column.name=='index_news_img'>
			<input type="hidden"  id="pictype"  value="news" />
		</#if>
		<#if  column.name=='index_banner'>
			<input type="hidden"   id="pictype"  value="banner" />
		</#if>
		<#if  column.name=='index_car_img'>
			<input type="hidden"   id="pictype"  value="carImg" />
		</#if>
		<#if  column.name=='index_goods_img'>
			<input type="hidden"   id="pictype"  value="goodsImg" />
		</#if>
		<#if  column.name=='index_store_img'>
			<input type="hidden"   id="pictype"  value="storeImg" />
		</#if>
		<div class="row"style="margin-left:0px;">
	        <h6 class="col-sm-9">
            	权限管理系统>CMS管理>编辑模板
	        </h6>
	    </div>
		<div class="form_horizontal" style="margin-top:20px;">
			<div style="height: 30px;">
			         模块名称：${column.note}
			</div>
			<div class="form_horizontalBox">
				<div class="setImg clearfix checkForm wrapBox">
					<div class="setImg_hint cr_999">
					
					<#if  column.name=='index_banner'>
						注意：如果尺寸修改过大，可能导致前台页面显示不正常（为保证banner图的显示效果，请使用
						<span style="color: red;">1920*350dpi</span>大小图片）
					 </#if>
					<#if  column.name=='index_news_img'>
						注意：如果尺寸修改过大，可能导致前台页面显示不正常（为保证新闻图的显示效果，请使用
						<span style="color: red;">320*220dpi</span>大小图片）
					 </#if>
					 <#if  column.name=='index_car_img' || column.name=='index_goods_img' || column.name=='index_store_img'>
						注意：如果尺寸修改过大，可能导致前台页面显示不正常（为保证新闻图的显示效果，请使用
						<span style="color: red;">390*148dpi</span>大小图片）
					 </#if>
					</div>

	<table style="margin-left: 130px;">
					<tr style="height: 30px;">
					<#if  upperLimitPic gte 1>
										<td >
											<div class="setImg_box" data-index="0">
												<div class="setImg_tit clearfix" style="position: relative;">
													<div style="width:35px; height:18px; position:absolute; left:38px; top:8px;background:${(index1.remark)!'' }"></div>
									 					<span>图片1<span>
														 </span></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" value="背景色" class="adminsubmit fr"> 
										<#if (index1.picUrl)?? && (index1.picUrl) != ''>
												<input type="button" value="编辑" class="adminsubmit fr" /> 
												<input type="button" value="移除" class="adminsubmit fr" /> 
										<#else>
												<input type="button" value="添加" class="adminsubmit fr" /> 
										</#if>
													
													<input type="hidden" name="columnContents[0].id" value="${(index1.id)!'' }" />
													 <input type="hidden" name="columnContents[0].picLinkHttp"
														id="columnContents[0].picLinkHttp" class="_picLinkHttp" value="${(index1.picLinkHttp)!'' }" /> 
													<input type="hidden" name="columnContents[0].picUrl" class="_picUrl" value="${(index1.picUrl)!'' }" /> 
													<input type="hidden" class="_menucolumncode" name="columnContents[0].menucolumncode"
														id="columnContents[0].menucolumncode" value="${(index1.menucolumncode)!'' }" />
													<input type="hidden" name="columnContents[0].picOrderIndex" value="1" />
													<input type="hidden" name="columnContents[0].remark" value="${(index1.remark)!'' }" />
														
												</div>
												<div class="setImg_frame setImg_frameImg">
									<#if (index1.picUrl)?? && (index1.picUrl) != ''>
												<img class="lazy" src="${PicServer}${(index1.picUrl)!''}"
														style="width:278px;height:160px;" />
										<#else>
													<img class="lazy" src="${PicServer}yellow.jpg" style="width:278px;height:160px;" />
										</#if>
													
												</div>
											</div>
										</td>
		                          </#if>
						
							<#if  upperLimitPic gte 2>
							<td style="margin-left: 130px;">
								<div class="setImg_box" data-index="1">
									<div class="setImg_tit clearfix" style="position: relative;">
										<div
											style="width:35px; height:18px; position:absolute; left:38px; top:8px;background:${(index2.remark)!'' }"></div>
											<span>图片2<span>
										 </span></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<input	type="button" value="背景色" class="adminsubmit fr"> 
										<#if (index2.picUrl)?? && (index2.picUrl) != ''>
												<input type="button" value="编辑" class="adminsubmit fr" /> 
												<input type="button" value="移除" class="adminsubmit fr" /> 
										<#else>
												<input type="button" value="添加" class="adminsubmit fr" /> 
										</#if>
									
										<input type="hidden" name="columnContents[1].id" value="${(index2.id)!'' }" />
										<input type="hidden" name="columnContents[1].picLinkHttp" id="columnContents[1].picLinkHttp" class="_picLinkHttp" value="${(index2.picLinkHttp)!'' }" />
										<input type="hidden" name="columnContents[1].picUrl" class="_picUrl" value="${(index2.picUrl)!'' }" /> 
										<input type="hidden" class="_menucolumncode" name="columnContents[1].menucolumncode" id="columnContents[1].menucolumncode" value="${(index2.menucolumncode)!'' }" />
										<input type="hidden" name="columnContents[1].picOrderIndex" value="2" />
										<input type="hidden" name="columnContents[1].remark" value="${(index2.remark) !''}" /> 
									</div>
									<div class="setImg_frame setImg_frameImg">
									<#if (index2.picUrl)?? && (index2.picUrl) != ''>
														<img class="lazy" src="${PicServer}${(index2.picUrl)!''}"
											style="width:278px;height:160px;"/>
										<#else>
													<img class="lazy" src="${PicServer}yellow.jpg" style="width:278px;height:160px;" />
										</#if>
								
									</div>
								</div>
							</td>
                            </#if>
					<#if  upperLimitPic gte 3>
							<td style="margin-left: 130px;">
								<div class="setImg_box" data-index="2">
									<div class="setImg_tit clearfix" style="position: relative;">
										<div
											style="width:35px; height:18px; position:absolute; left:38px; top:8px;background:${(index3.remark)!'' }"></div>
											<span>图片3<span>
											 </span></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<input	type="button" value="背景色" class="adminsubmit fr"> 
										<#if (index3.picUrl)?? && (index3.picUrl) != ''>
												<input type="button" value="编辑" class="adminsubmit fr" /> 
												<input type="button" value="移除" class="adminsubmit fr" /> 
										<#else>
												<input type="button" value="添加" class="adminsubmit fr" /> 
										</#if>
									
										<input type="hidden" name="columnContents[2].id" value="${(index3.id)!'' }" />
										<input type="hidden" name="columnContents[2].picLinkHttp" id="columnContents[2].picLinkHttp" class="_picLinkHttp" value="${(index3.picLinkHttp)!'' }" />
										<input type="hidden" name="columnContents[2].picUrl" class="_picUrl" value="${(index3.picUrl)!'' }" /> 
										<input type="hidden" class="_menucolumncode" name="columnContents[2].menucolumncode" id="columnContents[2].menucolumncode" value="${(index3.menucolumncode)!'' }" />
										<input type="hidden" name="columnContents[2].picOrderIndex" value="3" />
										<input type="hidden" name="columnContents[2].remark" value="${(index3.remark) !''}" /> 
									</div>
									<div class="setImg_frame setImg_frameImg">
									<#if (index3.picUrl)?? && (index3.picUrl) != ''>
														<img class="lazy" src="${PicServer}${(index3.picUrl)!''}"
											style="width:278px;height:160px;"/>
										<#else>
													<img class="lazy" src="${PicServer}yellow.jpg" style="width:278px;height:160px;" />
										</#if>
								
									</div>
								</div>
							</td>
                            </#if>
                            </tr>
                            <tr >
                            <#if  upperLimitPic gte 4>
							<td style="margin-left: 30px;">
								<div class="setImg_box" data-index="3">
									<div class="setImg_tit clearfix" style="position: relative;">
										<div
											style="width:35px; height:18px; position:absolute; left:38px; top:8px;background:${(index4.remark)!'' }"></div>
											<span>图片4<span>
											</span></span>&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="button" value="背景色" class="adminsubmit fr">
									<#if (index4.picUrl)?? && (index4.picUrl)!= ''>
												<input type="button" value="编辑" class="adminsubmit fr" />
												<input type="button" value="移除" class="adminsubmit fr" /> 
										<#else>
												<input type="button" value="添加" class="adminsubmit fr" /> 
										</#if>
							
										<input type="hidden" name="columnContents[3].id" value="${(index4.id)!'' }" /> 
										<input type="hidden" name="columnContents[3].picLinkHttp" id="columnContents[3].picLinkHttp" class="_picLinkHttp"
											value="${(index4.picLinkHttp) !''}" /> 
										<input type="hidden" name="columnContents[3].picUrl" class="_picUrl" value="${(index4.picUrl)!'' }" /> 
										<input type="hidden" class="_menucolumncode" name="columnContents[3].menucolumncode"
											id="columnContents[3].menucolumncode" value="${(index4.menucolumncode)!'' }" />
										<input type="hidden" name="columnContents[3].picOrderIndex" value="4" />
										<input type="hidden" name="columnContents[3].remark" value="${(index4.remark)!'' }" /> 
									</div>
									<div class="setImg_frame setImg_frameImg">
									<#if (index4.picUrl)?? && (index4.picUrl)!= ''>
												<img class="lazy" src="${PicServer}${(index4.picUrl)!''}"
											style="width:278px;height:160px;" />
										<#else>
													<img class="lazy" src="${PicServer}yellow.jpg" style="width:278px;height:160px;" />
										</#if>
									</div>
								</div>
								</div>
							</td>
						</#if>
					
								<#if  upperLimitPic gte 5>
									<td style="margin-left: 30px;">
										<div class="setImg_box" data-index="4">
											<div class="setImg_tit clearfix" style="position: relative;">
												<div
													style="width:35px; height:18px; position:absolute; left:38px; top:8px;background:${(index5.remark)!'' }"></div>
													<span>图片5<span>
													</span></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<input type="button" value="背景色" class="adminsubmit fr"> 
										<#if (index5.picUrl)?? && (index5.picUrl) != ''>
												<input type="button" value="编辑" class="adminsubmit fr" /> 
												<input type="button" value="移除" class="adminsubmit fr" /> 
										<#else>
												<input type="button" value="添加" class="adminsubmit fr" /> 
										</#if>
										
												<input type="hidden" name="columnContents[4].id" value="${(index5.id)!'' }" />
												 <input type="hidden" name="columnContents[4].picLinkHttp"
													id="columnContents[4].picLinkHttp" class="_picLinkHttp" value="${(index5.picLinkHttp)!'' }" /> 
												<input type="hidden" name="columnContents[4].picUrl" class="_picUrl" value="${(index5.picUrl)!'' }" /> 
												<input type="hidden" class="_menucolumncode" name="columnContents[4].menucolumncode"
													id="columnContents[4].menucolumncode" value="${(index5.menucolumncode)!'' }" />
												<input type="hidden" name="columnContents[4].picOrderIndex" value="5" />
												<input type="hidden" name="columnContents[4].remark" value="${(index5.remark)!'' }" />
											</div>
											<div class="setImg_frame setImg_frameImg">
											<#if (index5.picUrl)?? && (index5.picUrl) != ''>
												<img class="lazy" src="${PicServer}${(index5.picUrl)!''}"
												style="width:278px;height:160px;" />
										<#else>
													<img class="lazy" src="${PicServer}yellow.jpg" style="width:278px;height:160px;" />
										</#if>
												
											</div>
										</div>
									</td>
		                          </#if>
								  
								 <#if  upperLimitPic gte 6>
									<td style="margin-left: 30px;">
										<div class="setImg_box" data-index="4">
											<div class="setImg_tit clearfix" style="position: relative;">
												<div
													style="width:35px; height:18px; position:absolute; left:38px; top:8px;background:${(index6.remark)!'' }"></div>
									<span>图片6<span>
									</span></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" value="背景色" class="adminsubmit fr"> 
										<#if (index6.picUrl)?? && (index6.picUrl) != ''>
												<input type="button" value="编辑" class="adminsubmit fr" /> 
												<input type="button" value="移除" class="adminsubmit fr" /> 
										<#else>
												<input type="button" value="添加" class="adminsubmit fr" /> 
										</#if>
												
												<input type="hidden" name="columnContents[5].id" value="${(index6.id)!'' }" />
												 <input type="hidden" name="columnContents[5].picLinkHttp"
													id="columnContents[5].picLinkHttp" class="_picLinkHttp" value="${(index6.picLinkHttp)!'' }" /> 
												<input type="hidden" name="columnContents[5].picUrl" class="_picUrl" value="${(index6.picUrl)!'' }" /> 
												<input type="hidden" class="_menucolumncode" name="columnContents[5].menucolumncode"
													id="columnContents[5].menucolumncode" value="${(index6.menucolumncode)!'' }" />
												<input type="hidden" name="columnContents[5].picOrderIndex" value="6" />
												<input type="hidden" name="columnContents[5].remark" value="${(index6.remark)!'' }" />
											</div>
											<div class="setImg_frame setImg_frameImg">
										<#if (index6.picUrl)?? && (index6.picUrl) != ''>
												<img class="lazy" src="${PicServer}${(index6.picUrl)!''}"
													style="width:278px;height:160px;" />
										<#else>
													<img class="lazy" src="${PicServer}yellow.jpg" style="width:278px;height:160px;" />
										</#if>
												
											</div>
										</div>
									</td>
		                          </#if>
					</tr>

					<tr>
 							<#if  upperLimitPic gte 7>
									<td style="margin-left: 30px;">
										<div class="setImg_box" data-index="4">
											<div class="setImg_tit clearfix" style="position: relative;">
												<div
													style="width:35px; height:18px; position:absolute; left:38px; top:8px;background:${(index7.remark)!'' }"></div>
											<span>图片7<span>
											</span></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="button" value="背景色" class="adminsubmit fr"> 
										<#if (index7.picUrl)?? && (index7.picUrl) != ''>
												<input type="button" value="移除" class="adminsubmit fr" /> 
												<input type="button" value="编辑" class="adminsubmit fr" /> 
										<#else>
												<input type="button" value="添加" class="adminsubmit fr" /> 
										</#if>
										
												<input type="hidden" name="columnContents[6].id" value="${(index7.id)!'' }" />
												 <input type="hidden" name="columnContents[6].picLinkHttp"
													id="columnContents[6].picLinkHttp" class="_picLinkHttp" value="${(index7.picLinkHttp)!'' }" /> 
												<input type="hidden" name="columnContents[6].picUrl" class="_picUrl" value="${(index7.picUrl)!'' }" /> 
												<input type="hidden" class="_menucolumncode" name="columnContents[6].menucolumncode"
													id="columnContents[6].menucolumncode" value="${(index7.menucolumncode)!'' }" />
												<input type="hidden" name="columnContents[6].picOrderIndex" value="7" />
												<input type="hidden" name="columnContents[6].remark" value="${(index7.remark)!'' }" />
											</div>
											<div class="setImg_frame setImg_frameImg">
											<#if (index7.picUrl)?? && (index7.picUrl) != ''>
												<img class="lazy" src="${PicServer}${(index7.picUrl)!''}"
													style="width:278px;height:160px;" />
										<#else>
													<img class="lazy" src="${PicServer}yellow.jpg" style="width:278px;height:160px;" />
										</#if>
												
											</div>
										</div>
									</td>
		                          </#if>

								 <#if  upperLimitPic  gte 8>
										<td style="margin-left: 30px;">
											<div class="setImg_box" data-index="4">
												<div class="setImg_tit clearfix" style="position: relative;">
													<div style="width:35px; height:18px; position:absolute; left:38px; top:8px;background:${(index8.remark)!'' }">
													</div>
													<span>图片8<span>
												</span></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="button" value="背景色" class="adminsubmit fr">
										<#if (index8.picUrl)?? && (index8.picUrl) != ''>
												<input type="button" value="编辑" class="adminsubmit fr" /> 
												<input type="button" value="移除" class="adminsubmit fr" /> 
										<#else>
												<input type="button" value="添加" class="adminsubmit fr" /> 
										</#if>
									 
													<input type="hidden" name="columnContents[7].id" value="${(index8.id)!'' }" />
													 <input type="hidden" name="columnContents[7].picLinkHttp"
														id="columnContents[7].picLinkHttp" class="_picLinkHttp" value="${(index8.picLinkHttp)!'' }" /> 
													<input type="hidden" name="columnContents[7].picUrl" class="_picUrl" value="${(index8.picUrl)!'' }" /> 
													<input type="hidden" class="_menucolumncode" name="columnContents[7].menucolumncode"
														id="columnContents[7].menucolumncode" value="${(index8.menucolumncode)!'' }" />
													<input type="hidden" name="columnContents[7].picOrderIndex" value="8" />
													<input type="hidden" name="columnContents[7].remark" value="${(index8.remark)!'' }" />
												</div>
												<div class="setImg_frame setImg_frameImg">
										<#if (index8.picUrl)?? && (index8.picUrl) != ''>
												<img class="lazy" src="${PicServer}${(index8.picUrl)!''}"
														style="width:278px;height:160px;" />
										<#else>
												<img class="lazy" src="${PicServer}yellow.jpg" style="width:278px;height:160px;" /> 
										</#if>
													
												</div>
											</div>
										</td>
		                          </#if>
							 <#if  upperLimitPic == 9>
										<td style="margin-left: 30px;">
											<div class="setImg_box" data-index="4">
												<div class="setImg_tit clearfix" style="position: relative;">
													<div style="width:35px; height:18px; position:absolute; left:38px; top:8px;background:${(index9.remark)!'' }"></div>
									<span>图片9<span></span></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" value="背景色" class="adminsubmit fr"> 
										<#if (index9.picUrl)?? && (index9.picUrl) != ''>
												<input type="button" value="编辑" class="adminsubmit fr" /> 
												<input type="button" value="移除" class="adminsubmit fr" /> 
										<#else>
												<input type="button" value="添加" class="adminsubmit fr" /> 
										</#if>
													
													<input type="hidden" name="columnContents[8].id" value="${(index9.id)!'' }" />
													 <input type="hidden" name="columnContents[8].picLinkHttp"
														id="columnContents[8].picLinkHttp" class="_picLinkHttp" value="${(index9.picLinkHttp)!'' }" /> 
													<input type="hidden" name="columnContents[8].picUrl" class="_picUrl" value="${(index9.picUrl)!'' }" /> 
													<input type="hidden" class="_menucolumncode" name="columnContents[8].menucolumncode"
														id="columnContents[8].menucolumncode" value="${(index9.menucolumncode)!'' }" />
													<input type="hidden" name="columnContents[8].picOrderIndex" value="9" />
													<input type="hidden" name="columnContents[8].remark" value="${(index9.remark)!'' }" />
												</div>
												<div class="setImg_frame setImg_frameImg">
									<#if (index9.picUrl)?? && (index9.picUrl) != ''>
												<img class="lazy" src="${PicServer}${(index9.picUrl)!''}"
														style="width:278px;height:160px;" />
										<#else>
													<img class="lazy" src="${PicServer}yellow.jpg" style="width:278px;height:160px;" />
										</#if>
													
												</div>
											</div>
										</td>
		                          </#if>
					</tr>
				</table>
					<div align="center" style="margin-top: 20px;">
								<input type="button" onclick="addData()" value="保存" class="adminsubmit" />
							 <input type="button" value="返回" onclick="back()" class="adminsubmit" /></td>
					</div>
				</div>
			</div>
	</form>
	<#include "./uploadPic.ftl">
</body>
</html>
	<!--common.js  -->
<script  src="/js/cms/ckform.js"></script>
<script  src="/js/cms/common.js"></script>
<script  src="/js/cms/page.js"></script>
<script  src="/js/cms/bui-min.js"></script>
<script  src="/js/cms/title.js"></script>
<script  src="/js/cms/Validform_v5.3.2_min.js"></script>
<script  src="/js/cms/ueditor.config.js"></script>
<script  src="/js/cms/ueditor.all.js"></script>
<script  src="/js/cms/zh-cn.js"></script>
<!--common.js    end-->
<script type="text/javascript">window.jQuery || document.write("<script src='/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="/js/bootstrap.min.js"></script>
		<script src="/js/ace-elements.min.js"></script>
		<script src="/js/ace.min.js"></script>
		
		<script type="text/javascript" src="/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<!-- 引入 -->
		<script type="text/javascript" src="/js/jquery.tips.js"></script><!--提示框-->
		<script type="text/javascript" src="/js/mjIndex/page.js"></script><!--分页-->
		<script type="text/javascript" src="/js/mjIndex/vue.min.js"></script><!--vue.js-->
		<script type="text/javascript">
$(document).ready(function(){
			exec_main("编辑图片");
			$(".setImg").find("input[type='button']").click(function(){
				if($(this).val()=="编辑" || $(this).val() == '添加'){
					var img=$(this).parent().parent().find("img");
					// 轮播图 固定尺寸 960* 566					
					$("#outputWidth").val(1920);
					$("#outputHeight").val(350);
					$("#zoom").val(false);
					$("#path").val($(this).parent().parent().find("input[name$=picUrl]").val());
					if($(this).parent().parent().find("input[name$=picLinkHttp]").val())
					   $("#linkUrl").val($(this).parent().parent().find("input[name$=picLinkHttp]").val());
					obj=$(this);
					myMask();
				}else if($(this).val()=="移除"){
					 if(bootbox.confirm("确认要移除吗？")){
							$(this).parent().parent().find("img").attr("src","/img/yellow.jpg");
							$(this).parent().find("input[name$=picLinkHttp]").val("");
							$(this).parent().find("input[name$=picUrl]").val("");
								if (!checkIsOk()) {
								return;
							}
					 }	
				}else{
					obj=$(this);//将触发点击事件的按钮对象保存起来
					var imgOffset=$(this).parent().parent().find("img").offset();//获取图片的坐标
					
					var str = '<div style="display:none;position:absolute;" id="bgDiv">';
					str += '<input type="text" style="width:10px;color:gray" value="#" disabled="disabled"/>';
					str += '<input type="text" id="bgcolor" maxlength="6"/></div>';
					
					$('body').append(str);
					
					$("#bgDiv").css({"left":imgOffset.left,"top":imgOffset.top}).show("fast");//显示出背景色输入框
					$("#bgcolor").val($(this).parent().find("input[name$=remark]").val().replace("#",""));//假如有背景色，显示出背景色
					$("#bgcolor").focus().blur(function(){
						if(($(this).val().length==3)||($(this).val().length==6)||($(this).val().length==0)){
							$("#bgDiv").remove();
							$(obj).parent().find("input[name$=remark]").val("#"+$(this).val());
							$(obj).parent().find("div").css("background","#"+$(this).val());
							$(this).val("");
							obj=null;
						}else{
							$(this).focus();
							bootbox.alert("色值只能是3位或者6位！");
						}
					});
				}
			});
			$(".setImg").find("input[name$=picUrl]").each(function(){
				if(!$(this).val()){
					$(this).parent().parent().find("img").attr("src","/img/yellow.jpg");
				}	
			});
		});
		//为图片位赋值
		function setValues() {
			obj.parent().parent().find("input[name$=picUrl]").val(
					$("#path").val());
			obj.parent().parent().find("img").attr("src",
					"${PicServer}" + $("#path").val());
			if ($("#linkUrl").val() == '如:http://www.baidu.com')
				obj.parent().parent().find("input[name$=picLinkHttp]").val('');
			else
				obj.parent().parent().find("input[name$=picLinkHttp]").val(
						$("#linkUrl").val());
			obj = null;
		}

		function checkIsOk() {
			var isOk = true;
			var notSum = 0;//空白的图片数
			var upperLimitPic=${upperLimitPic};
			var lowerLimitPic=${lowerLimitPic};
			if(upperLimitPic!=lowerLimitPic){
				$(".checkForm").find("._picUrl").each(function(i) {
					if ($(this).val() == "") {
						notSum = parseInt(notSum + 1);
					}
				});
				if (isOk && notSum > upperLimitPic-lowerLimitPic) {
					bootbox.alert("至少上传${lowerLimitPic}张图片！");
					isOk = false;
				}
				return isOk;
			}else{
				$(".checkForm").find("._picUrl").each(function(i) {
					if ($(this).val() == "") {
						bootbox.alert("至少上传${lowerLimitPic}张图片！");
						isOk=false;	
					}
				});
				return isOk;
			}	
		}
	  
	  var flag = true;
						function addData() {
							if (!checkIsOk()) {
								return;
							}
							if ($("#contentMark").text().trim().length > 100) {
								return;
							}
							if (flag) {
								flag = false;
								$.ajax({
											type : 'post',
											url : '/cms/columnContentCtl/insertInfo.html',
											data : $("#formData").serialize(),
											cache : false,
											dataType : 'json',
											success : function(data) {
												bootbox.confirm(data.msg,function(result){
													if(result){
														if (data.code == '1') {
															window.location.href="/mj/column/columnList.html";
														}
													}
												});
												
												flag = true;
											}
										});
							}
						}
						function check(obj) {
							var val = $(obj).text().trim().length;
							if (val > 100) {
								$(obj).text($(obj).text().substring(0, 100))
							}
						}
						function back(){
							window.location.href="/mj/column/columnList.html";
						}
	  
</script>