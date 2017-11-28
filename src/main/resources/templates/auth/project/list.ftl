
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#include "../../common/auth_css.ftl"/>
<!-- 日期框 -->
</head>
<body>

	<div class="container-fluid" id="main-container">
		<div id="page-content" class="clearfix">
			<div class="row-fluid">
				<div class="row-fluid">
					<form id="fenye" name="fenye" action="/auth/pManager/list.html" method="post">
						<table>
							<tr  id="app">
								<td>
									<span class="input-icon">
										<input type="text" v-model="name" id="name" name="name" placeholder="项目名称" />
										<i id="nav-search-icon" class="icon-search"></i>
									</span>
								</td>
								<input type="hidden" id="pageNo" name="pageNo">
								<td>
									<span class="input-icon">
										<input class="inputStyle " autocomplete="off" type="text" v-model="uniqueKey" id="uniqueKey" name="uniqueKey"  placeholder="项目KEY" />
										<i id="nav-search-icon" class="icon-search inputIcon"></i>
									</span>
								</td>
								<td style="vertical-align:top;">
									<button class="btn btn-small btn-primary" v-on:click="search" title="搜索">
										搜索
									</button>
								</td>
								<td style="vertical-align:top;">
									<input type="button" value="重置" class="btn btn-small btn-primary" v-on:click="clear" />
								</td>
								<#if nodeAuths??>
									<#if nodeAuths.auth_loc_pManager_list_add??>
										<td style="vertical-align:top;">
											<input type="button" value="新增" class="btn btn-small btn-primary" onclick="addPro()" />
										</td>
									</#if>
								</#if>
							</tr>
						</table>
						
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th  style="text-align: center;" >序号</th>
									<th  style="text-align: center;" >项目名称</th>
									<th  style="text-align: center;" >项目KEY</th>
									<th  style="text-align: center;" >项目URL</th>
									<th  style="text-align: center;" >排序编号</th>
									<th  style="text-align: center;" >创建时间</th>
									<th  style="text-align: center;" >创建人</th>
									<th  style="text-align: center;" >项目描述</th>
									<th  style="text-align: center;" >操作</th>
								</tr>
							</thead>
							<tbody>
								<#list list as key>
									<tr>
										<td style="text-align: center;">
											${key_index+1 }
										</td>
										<td style="text-align: center;">
											${key.name!'' }
										</td>
										<td style="text-align: center;">
											${key.uniqueKey!'' }
										</td>
										<td style="text-align: center;">
											${key.url!'' }
										</td>
										<td style="text-align: center;">
											${key.orderNumber!'' }
										</td>
										<td style="text-align: center;">
											${key.createTime?string("yyyy-MM-dd HH:mm:ss")} 
										</td>

										<td style="text-align: center;">
											${key.createUserName!'' }
										</td>
										<td>
											${key.description!'' }
										</td>
										<td style="text-align: center;">
											<div class='hidden-phone visible-desktop btn-group'>
												<#if nodeAuths.auth_loc_pManager_list_edit??>
													<a class='btn btn-mini btn-info' title="编辑" onclick="edit(${key.id?c})">
													<i class='icon-edit'></i>
													</a> 
												</#if>
												<!-- <a class='btn btn-mini btn-info' title="您不能编辑">
													<i class='icon-edit'></i>
												</a> -->
												<!-- <a class='btn btn-mini btn-danger' title="不能删除">
													<i class='icon-trash'></i>
												</a> -->
												<#if nodeAuths.auth_loc_pManager_list_delete??>
													<a class='btn btn-mini btn-danger' title="删除" onclick="del(${key.id?c})">
														<i class='icon-trash'></i>
													</a>
												</#if>
											</div>
										</td>
										
									<tr/>
								</#list>
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
	<input type="hidden" id="pName" name="pName" value="${authProject.name!'' }">
	<input type="hidden" id="pKey" name="pKey" value="${authProject.uniqueKey!'' }">
	<input type="hidden" id="pages" name="pages" value="${pageInfo.pages}">
	<input type="hidden" id="pageNum" name="pageNum" value="${pageInfo.pageNum}">
	<input type="hidden" id="total" name="total" value="${pageInfo.total}">
	
	<#include "../../common/foot.ftl" >
</body>
</html>
<script src="/js/jquery-1.11.1.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/ace-elements.min.js"></script>
<script src="/js/ace.min.js"></script>
<script src="/js/bootbox.min.js"></script>
<script type="text/javascript" src="/js/mjIndex/page.js"></script><!--分页-->
<script type="text/javascript" src="/js/mjIndex/vue.min.js"></script><!--vue.js-->
<script src="/js/project/list.js"></script>