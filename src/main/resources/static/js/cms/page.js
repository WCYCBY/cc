(function($){
    var ms = {
        init:function(totalsubpageTmep,args){
            return (function(){
                ms.fillHtml(totalsubpageTmep,args);
                ms.bindEvent(totalsubpageTmep,args);
                ms.gopage(totalsubpageTmep,args);
            })();
        },
        //填充html
        fillHtml:function(totalsubpageTmep,args){
            return (function(){
                totalsubpageTmep="";
                if(args.currPage > args.totalPage){
                	args.currPage = args.totalPage;
                }
                totalsubpageTmep += "<li><span>"+args.rowCount+"条记录  当前第"+args.currPage+"页/共"+args.totalPage+"页</span></li>";
                if(args.currPage == 1){
                	totalsubpageTmep += "<li class=\"disabled\"><a href=\"javascript:;\">首页</a></li>";
                    totalsubpageTmep += "<li class=\"disabled\"><a href=\"javascript:;\">上一页</a></li>";
                }else{
                	totalsubpageTmep += "<li><a href=\"javascript:;\" class='geraltTb_pager' data-go=\""+1+"\">首页</a></li>";
                    totalsubpageTmep += "<li><a href=\"javascript:;\" class='geraltTb_pager' data-go=\""+(args.currPage-1)+"\">上一页</a></li>";
                }
                
                // 页码大于等于4的时候，添加第一个页码元素
                if(args.currPage!=1 && args.currPage>=4 && args.totalPage!=4) {
                    totalsubpageTmep += "<li class='ali'><a href='javascript:void(0);' class='geraltTb_pager' data-go=\""+1+"\" >"+1+"</a></li>";
                }
                /* 当前页码>4, 并且<=总页码，总页码>5，添加“···”*/
                if(args.currPage-2>2 && args.currPage<=args.totalPage && args.totalPage>5) {
                    totalsubpageTmep += "<li class='ali'><a href='javascript:void(0);' class='geraltTb_' data-go='' >...</a></li>";
                }
                /* 当前页码的前两页 */
                var start = args.currPage-2;
                /* 当前页码的后两页 */
                var end = args.currPage+2;

                if((start>1 && args.currPage<4) || args.currPage==1) {
                    end++;
                }
                if(args.currPage>args.totalPage-4 && args.currPage>=args.totalPage) {
                    start--;
                }
            
                for(; start<=end; start++) {
                    if(start<=args.totalPage && start>=1) {
                    	if(start == args.currPage){                    		
                    		totalsubpageTmep += "<li class='ali active'><span>"+start+"</span></li>";
                    	}else{
                    		totalsubpageTmep += "<li class='ali'><a href='javascript:void(0);' class='geraltTb_pager ' data-go=\""+start+"\" >"+start+"</a></li>";
                    	}
                    }
                }
                if(args.currPage+2<args.totalPage-1 && args.currPage>=1 && args.totalPage>5) {
                    totalsubpageTmep += "<li class='ali'><a href='javascript:void(0);' class='geraltTb_' data-go='' >...</a></li>";
                }

                if(args.currPage!=args.totalPage && args.currPage<args.totalPage-2 && args.totalPage!=4) {
                    totalsubpageTmep += "<li class='ali'><a href='javascript:void(0);' class='geraltTb_pager' data-go=\""+args.totalPage+"\" >"+args.totalPage+"</a></li>";
                }
                
                if(args.currPage == args.totalPage){
                	totalsubpageTmep += "<li class=\"disabled\"><a href=\"javascript:;\">下一页</a></li>";
                    totalsubpageTmep += "<li class=\"disabled\"><a href=\"javascript:;\">尾页</a></li>";
                }else{
                	totalsubpageTmep += "<li><a href=\"javascript:;\" class='geraltTb_pager' data-go=\""+(args.currPage+1)+"\">下一页</a></li>";
                    totalsubpageTmep += "<li><a href=\"javascript:;\" class='geraltTb_pager' data-go=\""+args.totalPage+"\">尾页</a></li>";
                }
                var gopage = 1;
                if(args.currPage >=1 && args.currPage < args.totalPage){
                	gopage = args.currPage+1;
                }
                if(args.currPage >= args.totalPage){
                	gopage = args.totalPage
                }
                totalsubpageTmep +="<li><span> 转到 </span><input type=\"text\" id=\"page_no\" value=\""+gopage+"\"><span>页</span></li>";
                totalsubpageTmep +="<li><a href=\"javascript:;\" class=\"geraltTb_\" id=\"btnConfirm\">确定</a></li>";
                $(".pagination").html(totalsubpageTmep);
                //调用文本框的id  
                $("#page_no").numeral(args.totalPage);  
            })();
        },
        //绑定事件
        bindEvent:function(totalsubpageTmep,args){
            return (function(){
                totalsubpageTmep.on("click","a.geraltTb_pager",function(event){
                    var current = parseInt($(this).attr("data-go"));
                    ms.fillHtml(totalsubpageTmep,{"currPage":current,"totalPage":args.totalPage,"turndown":args.turndown,"rowCount":args.rowCount});
                    if(typeof(args.backFn)=="function"){
                        args.backFn(current);
                    }
                });
            })();
        },
        gopage:function(totalsubpageTmep,args){
            totalsubpageTmep.on("click","#btnConfirm",function(event){
                var current = parseInt($("#page_no").val());
                ms.fillHtml(totalsubpageTmep,{"currPage":current,"totalPage":args.totalPage,"turndown":args.turndown,"rowCount":args.rowCount});
                if(typeof(args.backFn)=="function"){
                    args.backFn(current);
                }
            });
        }
    }
    $.fn.createPage = function(options){       
        ms.init(this,options);
    }
    
    
    //文本框只能输入数字，并屏蔽输入法和粘贴  
	 $.fn.numeral = function(totalPage) {     
        $(this).css("ime-mode", "disabled");     
        this.bind("keypress",function(e) {     
        var code = (e.keyCode ? e.keyCode : e.which);  //兼容火狐 IE      
            if(!$.browser.msie&&(e.keyCode==0x8))  //火狐下不能使用退格键     
            {     
                 return ;     
                }     
                return code >= 48 && code<= 57;     
        });     
        this.bind("blur", function() {     
            if (this.value.lastIndexOf(".") == (this.value.length - 1)) {     
                this.value = this.value.substr(0, this.value.length - 1);     
            } else if (isNaN(this.value)) {     
                this.value = "";     
            }     
        });     
        this.bind("paste", function() {     
            var s = clipboardData.getData('text');     
            if (!/\D/.test(s));     
            value = s.replace(/^0*/, '');     
            if(this.value > totalPage);
        	value = totalPage; 
            return false;     
        });     
        this.bind("dragenter", function() {     
            return false;     
        });     
        this.bind("keyup", function() {     
        	if (/(^0+)/.test(this.value)) {     
        		this.value = this.value.replace(/^0*/, '');     
            }
        	if(this.value > totalPage){
        		this.value = totalPage; 
        		
        	}
        });     
    };  
    
    
    
  //只能输入0-9的数字和小数点  
    $.fn.mustFloat = function(){  
        return validate($(this),/[^0-9.]/g);  
    };  
    //只能输入>0的正整数  
    $.fn.mustInt = function(){  
        return validate($(this),/\D|^0/g);  
    };  
    function validate(_obj,reg){  
        _obj.live("keyup blur",function(){  
            $(this).val($(this).val().replace(reg,''));    
        }).bind("paste",function(){  //CTR+V事件处理    
            $(this).val($(this).val().replace(reg,''));     
        }).css("ime-mode", "disabled"); //CSS设置输入法不可用    
        return _obj;  
    }  
})(jQuery);