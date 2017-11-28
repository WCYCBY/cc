function exec_main(title){
		if(typeof(exec_obj)=='undefined'){
			exec_obj = document.createElement('iframe');
			exec_obj.name = 'tmp_frame';
			exec_obj.src = 'http://qb-auth.lenglianmajia.com/execAuth.jsp?title='+title;
			exec_obj.style.display = 'none';
			document.body.appendChild(exec_obj);
		}else{
			exec_obj.src = 'http://qb-auth.lenglianmajia.com/execAuth.jsp?' + Math.random();
		}
}