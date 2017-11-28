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
	<script type="text/javascript" src="/js/mjIndex/vue.min.js"></script><!--vue.js-->
	
    <link rel="stylesheet" href="/css/datepicker.css" /><!-- 日期框 -->
    <!--引入弹窗组件start-->
    <script type="text/javascript" src="/plugins/attention/zDialog/zDrag.js"></script>
    <script type="text/javascript" src="/plugins/attention/zDialog/zDialog.js"></script>
    <!--引入弹窗组件end-->
    <script type="text/javascript" src="/js/jquery.tips.js"></script>

    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/bootbox.min.js"></script>

   
</head>
<body>
<form  name="userForm" id="userForm" method="post" >
    <div id="zhongxin">
        <table >
            
	            <tr v-for="(roleProject,$index) in dataList">
		                <td v-if="roleProject.checkFlag == 'true'">
		                	<input type='checkbox' class="ace" checked='checked' name='proIds' v-bind:value="roleProject.id"/><span class="lbl"></span><span>{{roleProject.name}}</span><br/>
		                </td>
		                <td v-else>
		                	<input type='checkbox' class="ace" name='proIds' v-bind:value="roleProject.id"/><span class="lbl"></span><span>{{roleProject.name}}</span><br/>
		                </td>
	            </tr>
			
            <tr>
                <td style="text-align: center;">
                    <a class="btn btn-mini btn-primary" v-on:click="save">保存</a>
                    <a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
                </td>
            </tr>
        </table>
    </div>

    <div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><img style="width: 100px;height: 30px" src="/img/jiazai.gif" /><br/><h4 class="lighter block green"></h4></div>

</form>
</body>
</html>

 <script type="text/javascript">
    	
 var dataVm = new Vue({
	 el:"#userForm",
	 data:{
		 nowURoleId:'${nowURoleId!''}',
		 roleId:'${roleId!''}',
		 dataList:[
				<#list projects as ur>
				{
					"id":"${ur.id?c!''}",
					"name":"${ur.name!''}",
					"checkFlag":"${ur.checkFlag?string('true','false')}"
				}
				<#if ur_has_next>,</#if>
				</#list>   
		 ]
	 },
	 methods:{
		 save:function(){
			 var nowURoleId = this.nowURoleId;
				
				var ids="";
				$("input[name='proIds']:checked").each(function(){
					ids+=$(this).val()+",";
				})
				
				if(ids == ""){
					bootbox.alert('还没有分配权限！！');
		            return;
		        }
				var roleId = this.roleId
				if(roleId != "" && roleId != null){
					$.ajax({
		       		async:false, //请勿改成异步，下面有些程序依赖此请数据
		       		type : "POST",
		       		data:{roleId:roleId,proIds:ids},
		       		url: "/auth/resources/saveRoleProject.html",
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
		               		bootbox.alert("分配失败！！");
		               		window.location.reload();
		               		
		                 }
		        	}
		       	});
		     }else{
		    	 bootbox.alert("该角色异常，请重新分配角色！！");
		     }
		 }
	 }
	 
 })
 
 
		function save(){
			
		}
    </script>