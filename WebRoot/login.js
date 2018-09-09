//暂时未用到

layui.use(['form','jquery'], function(){
  var form = layui.form;
  var $ = layui.jquery;

	//提交
	form.on('submit(LAY-user-login-submit)', function(obj){
	
		//请求登入接口
		$.ajax({
			url:"doLogin"
			,data: obj.field
			,type:'post'
		    ,done: function(res){
		    
		      //请求成功后，写入 access_token
		      layui.data(setter.tableName, {
		        key: setter.request.tokenName
		        ,value: res.data.access_token
		      });
		      
		      //登入成功的提示与跳转
		      layer.msg('登入成功', {
		        offset: '15px'
		        ,icon: 1
		        ,time: 1000
		      }, function(){
		        location.href = '/front/index.html'; //后台主页
		      });
		    }
		})
		  
	});
	
	$("#LAY-user-get-vercode").on("click", function(){
		var _t = new Date().getTime();
		$(this).attr("src","verifycode?_t="+_t);
	})
});
