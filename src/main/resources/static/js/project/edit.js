
function back(){
		var url = $("#returnUrl").val();
		window.location.href=url;
	}
	$("form").submit(function(e){
	  if($("#name").val()==""){
	        $("#name").tips({
	            side:3,
	            msg:'输入项目名称',
	            bg:'#AE81FF',
	            time:2
	        });
	
	        $("#name").focus();
	        return false;
	    }
	
	    if($("#url").val()==""){
	        $("#url").tips({
	            side:3,
	            msg:'输入项目url',
	            bg:'#AE81FF',
	            time:2
	        });
	
	        $("#url").focus();
	        return false;
	    }
	    if($("#orderNumber").val()==""){
	        $("#orderNumber").tips({
	            side:3,
	            msg:'输入排序编号',
	            bg:'#AE81FF',
	            time:2
	        });
	
	        $("#orderNumber").focus();
	        return false;
	    }
	    if(isNaN($("#orderNumber").val())){
	        $("#orderNumber").tips({
	            side:3,
	            msg:'排序编号格式不正确',
	            bg:'#AE81FF',
	            time:2
	        });
	
	        $("#orderNumber").focus();
	        return false;
	    }
	    
	});
	