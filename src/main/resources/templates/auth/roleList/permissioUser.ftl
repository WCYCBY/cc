<!DOCTYPE html>
<html lang="en">
<head>
    <title></title>
    <meta charset="utf-8" />

    <meta name="description" content="overview & stats" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <!-- basic styles -->
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


    <script type="text/javascript" src="/js/jquery-1.11.1.js"></script>

    <link rel="stylesheet" href="/css/datepicker.css" /><!-- 日期框 -->
    <!--引入弹窗组件start-->
    <script type="text/javascript" src="/plugins/attention/zDialog/zDrag.js"></script>
    <script type="text/javascript" src="/plugins/attention/zDialog/zDialog.js"></script>
    <!--引入弹窗组件end-->
    <script type="text/javascript" src="/js/jquery.tips.js"></script>

    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/bootbox.min.js"></script>
    <script src="/js/ligerUI/base.js" type="text/javascript"></script>
	<link href="/js/ligerUI/ligerui-tree.css" rel="stylesheet" type="text/css" />
	<script src="/js/ligerUI/ligerTree.js" type="text/javascript"></script>
	<script type="text/javascript" src="/js/mjIndex/vue.min.js"></script><!--vue.js-->

    
</head>
<body>
	<input type="hidden" id="perRoleId" name="perRoleId"/>
    <div id="ccR" >
	 	<div id="content"  style='width:100%;  '>
	 		<ul id='tree1'></ul> 
	 	</div>
	</div>
	<div style="text-align: center;" id="consa">
		<a class="btn btn-mini btn-primary" v-on:click="save">保存</a>
 	   <a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
	</div>
	
    <div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><img style="width: 100px;height: 30px" src="/img/jiazai.gif" /><br/><h4 class="lighter block green"></h4></div>
</body>
</html>
<script type="text/javascript">
	var dataVm = new Vue({
		el:'#ccR',
		mounted:function(){
			  this.$nextTick(function () {
		         this.treeList();
			  })
		  },
		methods:{
			treeList: function(){
					$("#ccR").html("<div id='content'><ul id='tree1'></ul> </div>");
					$("#perRoleId").val(${id});
					var tree;
					manager=null;
		     	  	var data = [];
		     	  	
			     	  <#list allRes as res >
			     	  	var flag = false;
			     	  		<#list resources as our >
			     	  			if(${our.id?c} == ${res.id?c}){
			     	  				data.push({ fid: '${res.id?c}',pfid:'${res.parentId?c}', fname:'${res.name}',ischecked: true});
			     	  				flag = true;
			     	  			}
			     	  		 </#list>
			     	  		 if(!flag){
			     	  			data.push({ fid: '${res.id?c}' , pfid:'${res.parentId?c}', fname:'${res.name}'});
			     	  		 }
			     	  		 
			     	  </#list>	
		     	  	
		     	  	
		     	  	tree=$("#tree1").ligerTree({ 
		            	data:data, 
		                textFieldName: 'fname', 
		                attribute: ['fid', 'fname', 'pfid'],  
		                idFieldName :'fid',
		                parentIDFieldName :'pfid',
		                checkbox: true,
		                nodeWidth:400//手动加上，改宽度

		            });
		     	  	
		     	  	manager = $("#tree1").ligerGetTreeManager();
				}
		  }
			
	})
	
	
	var vue=new Vue({
		el:'#consa',
		methods:{
			save:function(){
				var nowURoleId = '${nowUser.roleId}' + "";
				var roleId =$("#perRoleId").val();
				var fids="";
				var notes = manager.getChecked();
			     for (var i = 0; i < notes.length; i++)
			     {
			    	 var str = this.appendFid(notes[i].data.fid,notes[i].data.fid+",");
			    	 fids += str;
			     }
			    
			     if(fids == ""){
			    	 bootbox.alert("还没有分配权限！");
			         return;
			     }
			     if(roleId != ""){
			    	 $.ajax({
			       		async:false, //请勿改成异步，下面有些程序依赖此请数据
			       		type : "POST",
			       		data:{roleId:roleId,rescId:fids},
			       		url: "/auth/resources/saveRoleRescours.html",
			       		dataType:'json',
			       		success: function(json){
			       			
			       			if(json == "1000"){
			       				
			       				bootbox.confirm("分配成功,必须重新登录才生效！！", function (result) {  
			       	                if(result && nowURoleId == roleId) {  
			       	                	
			       	                	window.location.href="/auth/login/logout.html";
			       	                }else{
			       	                	top.Dialog.close();
			       	                }
			       	        
			       	            });
			               	}else if(json == "1001"){
			               		bootbox.alert("分配失败，请重新分配！");
			               		window.location.reload();
			                 };
			        		}
			       	});
			     }else{
			    	 bootbox.alert("该用户还没有分配角色或用户的角色被删除了，请重新分配角色！！");
			     }
			},
			appendFid:function(fid,fids){
			    var pfid = $("li [fid='"+fid+"']").attr("pfid");
			    if(pfid != "" && pfid != null && pfid != undefined){
			        fids += pfid + ",";
			        fids = this.appendFid(pfid,fids);
			    }
				return fids;
			}
		}
	})
    
		
		
    </script>