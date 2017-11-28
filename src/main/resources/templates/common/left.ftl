<#setting number_format="#">
<script type="text/javascript" src="/js/mjIndex/vue.min.js"></script><!--vue.js-->
<div id="sidebar" class="menu-min">
    <ul class="nav nav-list" id="slidebarVue">
        <li class="active" id="fhindex">
            <a href="/auth/background/index.html"><i class="icon-home"></i><span>后台首页</span></a>
        </li>
    <#--vue menus start   ////////////////////////////////////////////////////////////////-->

            <li v-for="ms in menus" v-bind:id="['z'+ms.pid]" class="dropdown-toggle" style="padding-left:5px;">
                <a class="dropdown-toggle hj" style="cursor:pointer;">
                    <img v-bind:src="ms.icon"></i>
                    <span>{{ms.text}}</span>
                    <b class="arrow icon-angle-down"></b>
                </a>
               <ul class="submenu dk" style="display: none;">
                    <li v-for="mu in ms.menu" class="" v-bind:id="['z'+mu.id]">
                        <a class="dropdown-toggle" style="cursor:pointer;">
                            <i class="icon-play" style="zoom:0.5"></i>
                            {{mu.text}}
                        </a>
                       <ul class="submenu submenu1 hide" >
                            <li v-for="item in mu.items" class="" v-bind:id="['z'+item.id]">
                                <a style="cursor:pointer;" target="mainFrame" v-on:click="siMenuVue(item.id,'lm1',item.text,item.href)">
                                    <i class="icon-play" style="zoom:0.5"></i>
                                    {{item.text}}
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </li>

    <#--vue menus start   ////////////////////////////////////////////////////////////////-->
    </ul>
</div>

<script type="text/javascript">
   var vm  = new Vue({
       el: '#slidebarVue',
       data: {
           hh:"1",
           "menus": [
           		<#if menus?? && (menus?size > 0) >
	                <#list menus as ms>
	                    {
	                        "pid": "${ms.id!""}",
	                        "text": "${ms.text!""}",
	                        "icon":"/img/${ms.icon!""}",
	                        "menu": [
	                        	<#if ms.menu?? && (ms.menu?size > 0) >
		                            <#list ms.menu as mu>
		                                {
		                                    "id":"${mu.id!""}",
		                                    "text":"${mu.text!""}",
		                                    "items": [
		                                    	<#if mu.items?? && (mu.items?size > 0) >
			                                        <#list mu.items as item>
			                                            {
			                                                "id":"${item.id!""}",
			                                                "text":"${item.text!""}",
			                                                "href":"${item.href!""}"
			                                            }<#if item_has_next>,</#if>
			                                        </#list>
			                                    </#if>
		                                    ]
		                                }<#if mu_has_next>,</#if>
		                            </#list>
		                       </#if>
	                        ]
	                    }<#if ms_has_next>,</#if>
	                </#list>
	          </#if>
           ]
       },
       methods: {
    	   
           siMenuVue: function (id,fid,MENU_NAME,MENU_URL) {
        	   if( ${factoryCode!""} != 0){
	        	   if(MENU_URL.indexOf("?") < 0){
		               siMenu("z"+id,fid,MENU_NAME,MENU_URL + "?factoryCode=" + ${factoryCode!""} + "&factoryCodeSearch=" + ${factoryCode!""});
	        	   }else{
	        		   siMenu("z"+id,fid,MENU_NAME,MENU_URL + "&factoryCode=" + ${factoryCode!""} + "&factoryCodeSearch=" + ${factoryCode!""});
	        	   }
        	   }else{
        		   siMenu("z"+id,fid,MENU_NAME,MENU_URL);
        	   }
        	   
           }
       }
   })

</script>
