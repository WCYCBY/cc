
function back(){
	var url = $("#returnUrl").val();
	window.location.href=url;
}

$("form").submit(function(e){
	var name = $("#name").val();
	if(name ==""){
        $("#name").tips({
            side:3,
            msg:'输入工厂名称',
            bg:'#AE81FF',
            time:2
        });

        $("#name").focus();
        return false;
    }
	
	var getAnsiLength = function(b,ansi) {
		if (!(typeof b == 'string') || !ansi) {
			return b.length;
		}
		var regex=/^[\u2E80-\u9FFF]+$/;
		if(!regex.test(b)){
			return "error";
		}
		var a=b.match(/[^\x00-\x80]/g);
		return b.length+(a?a.length:0);
	};
	var atleast=5,atmax=18;
	var len=getAnsiLength(name,true);
	if(len == "error"){
		$("#name").tips({
            side:3,
            msg:'输入工厂名称有误',
            bg:'#AE81FF',
            time:2
        });
		$("#name").focus();
        return false;
	}else if(len<atleast){
		$("#name").tips({
            side:3,
            msg:'不能少于5个字符',
            bg:'#AE81FF',
            time:2
        });
		$("#name").focus();
        return false;
	}else if(len>atmax){
		$("#name").tips({
            side:3,
            msg:'不能多于18个字符',
            bg:'#AE81FF',
            time:2
        });
		$("#name").focus();
        return false;
	}

	
	var description = $("#description").val();
	if(description.length > 40){
		$("#description").tips({
			side:3,
            msg:'工厂描述太长！',
            bg:'#AE81FF',
            time:2
		});
		
		$("#description").focus();
	        return false;
	}
	
})