
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
	
		if($("#uniqueKey").val()==""){
	        $("#uniqueKey").tips({
	            side:3,
	            msg:'输入项目key',
	            bg:'#AE81FF',
	            time:2
	        });
	
	        $("#uniqueKey").focus();
	        return false;
	    }
		var atleast=3,atmax=50;
		var getAnsiLength = function(b,ansi) {
		if (!(typeof b == 'string') || !ansi) {
				return b.length;
			}
			var regex=/^[a-zA-Z]{1}[0-9a-zA-Z_]{0,}$/;
			var a=b.match(/[^\x00-\x80]/g);
			return b.length+(a?a.length:0);
		};
		var key = $("#uniqueKey").val();
		var len=getAnsiLength(key,true);
		if(len<atleast){
			 $("#uniqueKey").tips({
		            side:3,
		            msg:"项目KEY不能少于"+atleast+"个字符",
		            bg:'#AE81FF',
		            time:2
		        });
		
		        $("#uniqueKey").focus();
		        return false;
		}else if(len>atmax){
			 $("#uniqueKey").tips({
		            side:3,
		            msg:"项目KEY不能多于"+atmax+"个字符",
		            bg:'#AE81FF',
		            time:2
		        });
		
		        $("#uniqueKey").focus();
		        return false;
		}
		
		
		 var userName = $('#uniqueKey').val(); 
			var errorCode;
			$.ajax({
		   		async:false, 
		   		type : "POST",
		   		data:{key: userName},
		   		url: "/auth/pManager/changeNameCheck.html",
		   		dataType:'json',
		   		success: function(json){
		           	errorCode=json.responseText;
		    	},
		    	error:function(json){
		    		errorCode=json.responseText;
		    	}
		   	});
			if(errorCode == "SYS_0001" || errorCode == "ERROR"){
				 $("#uniqueKey").tips({
		            side:3,
		            msg:'项目key已被使用',
		            bg:'#AE81FF',
		            time:2
		        });
				
				$("#uniqueKey").focus();
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