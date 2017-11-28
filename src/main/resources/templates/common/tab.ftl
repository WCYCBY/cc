<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>">

    <script type="text/javascript" src="/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="/plugins/tab/js/framework.js"></script>
    <link href="/plugins/tab/css/import_basic.css" rel="stylesheet" type="text/css"/>
    <link  rel="stylesheet" type="text/css" id="skin" prePath="/plugins/tab/" />
    <script type="text/javascript" charset="utf-8" src="/plugins/tab/js/tab.js"></script>
</head>

<body>
<div id="tab_menu" style="height:30px;padding-left:20px;"></div>
<div style="width:100%;">
    <div id="page" style="width:100%;height:100%;"></div>
</div>
</body>
<script type="text/javascript" >
	
	//检测tab个数超过8个关闭
	function doubiTab() {
		var dbTabTotal = $("#tab_menu .tab_item").length;
		if(dbTabTotal >= 8) {
			$("#tab_menu .tab_item").eq(1).find(".tab_close").click();
		}
	}
	
    function tabAddHandler(mid,mtitle,murl){
        tab.update({
            id :mid,
            title :mtitle,
            url :murl,
            isClosed :true
        });
        tab.add({
            id :mid,
            title :mtitle,
            url :murl,
            isClosed :true
        });

        tab.activate(mid);
    }
    var tab;
    $( function() {

        tab = new TabView( {
            containerId :'tab_menu',
            pageid :'page',
            cid :'tab1',
            position :"top"
        });
        tab.add( {
            id :'tab1_index1',
            title :"主页",
            url :"/auth/backgrounk/loginDefault.html",
            isClosed :false
        });

    });

    function cmainFrameT(){
        var hmainT = document.getElementById("page");
        var bheightT = document.documentElement.clientHeight;
        hmainT .style.width = '100%';
        hmainT .style.height = (bheightT  - 51) + 'px';
    }
    cmainFrameT();
    window.onresize=function(){
        cmainFrameT();
    };

</script>
</html>

