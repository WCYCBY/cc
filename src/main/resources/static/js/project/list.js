
//删除
function del(id){
   bootbox.confirm("您确定要删除该条项目记录吗？删除后不可恢复，且同时会删除该项目下所有菜单及按钮！", function(result) {
		if(result) {
			$.ajax({
		   		async:false, 
		   		type : "POST",
		   		data:{id: id},
		   		url: "/auth/pManager/checkProRole.html",
		   		dataType:'json',
		   		success: function(data){
		   			//data为真表示有关联的角色，即该项目不能删除
		   			if(!data){
		   				window.location.href="/auth/pManager/del.html?id="+id;
		   			}else{
		   				bootbox.alert("有关联资源，该项目暂时不可删除！");
		   			}
		    	}
		   	});
		}
	});
}

//新增项目
function addPro(){
	var url = encodeURIComponent(window.location.href);
	window.location.href="/auth/pManager/add.html?returnUrl="+url;	
}

//编辑项目
function edit(id){
	var url = encodeURIComponent(window.location.href);
	window.location.href="/auth/pManager/edit.html?id="+id+"&returnUrl="+url;
}

    var vm = new Vue({
        el: '#app',
        data: {
            name: $("#pName").val(),
            uniqueKey:$("#pKey").val()
        },
        // 在 `methods` 对象中定义方法
        methods: {
            search: function() {
                // // 方法内 `this` 指向 vm
                var name = $.trim(this.name);
                var uniqueKey = $.trim(this.uniqueKey);
                $("#name").val(name);
                $("#uniqueKey").val(uniqueKey);
                $("#fenye").submit();
            },
            clear: function() {
               this.name='';
               this.uniqueKey='';
            }
        }
    })

$(top.hangge());

$(function(){
        $(".pagination").createPage({
            totalPage:$("#pages").val(),
            currPage:$("#pageNum").val(),
            rowCount:$("#total").val(),
            backFn:function(p){
               //console.log("回调函数："+p);
               queryLine(p);
            }
        });
 })
 
 function queryLine(n){
	n = isNaN(n)?1:n;
	$("#pageNo").val(n);
	$("#fenye").submit();
}