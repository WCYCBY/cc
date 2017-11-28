$("form").submit(function(e){
	  if($("#proId").val()==0){
	        $("#proId").tips({
	            side:3,
	            msg:'请选择资源所属的项目!',
	            bg:'#AE81FF',
	            time:2
	        });
	        $("#proId").focus();
	        return false;
	    }
	  var item = $(":radio:checked");
	  var len=item.length;
	  if(len==0){
		  $("#typeT").tips({
	            side:3,
	            msg:'请选择资源类型!',
	            bg:'#AE81FF',
	            time:2
	        });
	        $("#typeT").focus();
	        return false;
	    }
	  if($("#name").val()==""){
	        $("#name").tips({
	            side:3,
	            msg:'输入资源名称',
	            bg:'#AE81FF',
	            time:2
	        });
	
	        $("#name").focus();
	        return false;
	    }
	  
	  //资源key校验
		var parttrn = /^[A-Za-z]+$/;
		var resKeyUp=$("#resKey").val();
		var resKey = $('#initKey').val()+"_"+resKeyUp;
		var reskeys = $("#reskeys").val();
		var errorCode;
	  if(!parttrn.test(resKeyUp)){
	        $("#resKey").tips({
	            side:3,
	            msg:'资源key不能为空或格式错误',
	            bg:'#AE81FF',
	            time:2
	        });
	        $("#resKey").focus();
	        return false;
	    }
if(resKeyUp!=reskeys){
	  $.ajax({
	   		async:false, 
	   		type : "POST",
	   		data:{resKey: resKey},
	   		url: "/auth/resources/changeResKey.html",
	   		dataType:'json',
	   		success: function(json){
	           	errorCode=json;
	    	}
	   	});
}
		if(errorCode == "1001"){
			  $("#resKey").tips({
		            side:3,
		            msg:'资源key已存在!',
		            bg:'#AE81FF',
		            time:2
		        });
		        $("#resKey").focus();
		        return false;
		}
	  //资源url"urll" : /^([\w\.\/\%\&\-\=\:\?\&])+$/,
		
	  	var parttrn = /^([\w\.\/\%\&\-\=\:\?\&])+$/;
	  	var resUrl=$("#resUrl").val();
	  if(!parttrn.test(resUrl)){
	        $("#resUrl").tips({
	            side:3,
	            msg:'资源url不能为空或格式错误',
	            bg:'#AE81FF',
	            time:2
	        });
	        $("#resUrl").focus();
	        return false;
	    }
//优先级	   
	  if($("#level").val()=="" || isNaN($("#level").val())){
	        $("#level").tips({
	            side:3,
	            msg:'优先级为空或者格式不正确',
	            bg:'#AE81FF',
	            time:2
	        });
	        $("#level").focus();
	        return false;
	    }
	    
	   
	});
