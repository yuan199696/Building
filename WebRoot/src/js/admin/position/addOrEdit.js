//编辑、添加页面
var InfoId ,//接受id信息
	flag,
	saveNewField,
	deleteNewField,
	deleteExtendInfoField,
	pageData,
	extendFields = [],
	tempInput; //标识编辑或添加


InfoId = getQueryString('bid');
if (InfoId) {
	flag = "edit";
} else {
	flag = "add";
}

layui.use(['layedit','form', 'element','laydate'], function(){
	
	var form = layui.form;
	var element = layui.element;
	var layedit = layui.layedit;
	var laydate = layui.laydate;
	
	//初始化日期
	laydate.render({
		elem: '#create_time' 
	});
	laydate.render({
		elem: '#update_time' 
	});
	initRadioInput("authorized");
	form.render("radio");


	
	layedit.set({
		uploadImage: {
			url: '/Building/admin/uploadImage'
		}
	});
	
	var layeditInstance = layedit.build('position_detail'); //建立编辑器
	
	
	/****************** 函数实现代码区域 start ******************/
	
	/**
	 * 
	 */
	function getPosData(){
		$.ajax({
			url:"/Building/enterprise/allPos",
			dataType:"json",
			success: function(data){
				if (data && data.code == 0){
					renderPosField(data.data);
				}
				
			},
			error: function(){
				
			}
		})
	}
	
	function renderPosField(poss){
		if (poss && poss.length > 0) {
			var str = "";
			var sel = $("#include_pos_container");
			for (var i = 0; i < poss.length; i++) {
				str += '<input type="checkbox" title="'+ poss[i].position_name +'" name="'+ poss[i].position_id +'"/>';
			}
			sel.append($(str));
			form.render("checkbox");
		}
	}
	
	
	
	/**
	 * 
	 */
/*	function getDatafromServer(){
		var result = null;
		if (InfoId) {
			$.ajax({
				url:"/Building/enterprise/getPosByID?bid="+InfoId,
				async:false,
				dataType:"json",
				success: function(res){
					if (res && res.code == 0) {
						result = res.data;
					} else {
						layer.alert(res.msg);
					}
				},
				error: function(err){
					layer.alert(err);
				}
			})
		}
		return result;
	}
	*/
	
	
	/**
	 * 编辑
	 */
	function doEdit(){
		
		var inputs = document.body.querySelectorAll("input");
		var selects = document.body.querySelectorAll("select");
		var res = callService("/Building/enterprise/getPosById?bid="+InfoId);
		
		if (!res) {
			return false;
		}
		pageData = res;
		if (res && inputs) {
			for(var i = 0; i < inputs.length; i++){
//				if (inputs[i].name == "userAvatar") {
//					
//					$("#preview_image").html($('<img width="300" src="'+res["user_avatar"]+'" alt=""/>'))
//					continue;
//				}
				for (var item in res) {
					if (inputs[i].name == camelCase(item)) {
						
						renderInput(inputs[i], res[item]);
						if(inputs[i].type != "radio") {
							
							break;
						}
					}
				}
				if (inputs[i].name == "positionId") {
					inputs[i].setAttribute("readonly", 'readonly');
				}
			}
			//form.render("radio");
		}
		
//		if (res && selects){
//			for(var i = 0; i < selects.length; i++){
//				
//				for (var item in res) {
//					if (selects[i].name == camelCase(item)) {
//						
//						renderSelect(selects[i], res[item]);
//						break;
//					}
//				}
//			}
//			form.render("select");
//		}
	}

	/**
	 * 
	 */
	function doAdd(){
		
	}



	/**
	 * 
	 */
	function imagePreview(){
		var prev = $("#preview_image"); 
		
		if(typeof FileReader==='undefined'){ 
			prev.html("抱歉，你的浏览器不支持图片预览"); 
		}
	}

	/**
	 * 
	 */
	function doAddEdit(){
		if (flag == "edit") {
			doEdit();
			$("form").attr("action","/Building/enterprise/updatePosition");
			
		} else if (flag == "add"){
			doAdd();
		}
		
		imagePreview();
	}
	
	/****************** 函数实现代码区域 end ******************/
	  
	
	  
	//监听提交
	form.on('submit(formDemo)', function(data){
		var extendFieldsData = {};
		
		$("#position_detail").val(layedit.getContent(layeditInstance));
		if ( extendFields.length > 0) {
			for (var i = 0;i < extendFields.length; i++) {
				extendFieldsData[extendFields[i]] = $("input[name="+ extendFields[i] +"]").val();
			}
		}
		$("input[name=extendField]").val(JSON.stringify(extendFieldsData));
	});
	  
	  //获取hash来切换选项卡，假设当前地址的hash为lay-id对应的值
	var layid = location.hash.replace(/^#test1=/, '');
	element.tabChange('test1', layid); //假设当前地址为：http://a.com#test1=222，那么选项卡会自动切换到“发送消息”这一项
	  
	//监听Tab切换，以改变地址hash值
	element.on('tab(test1)', function(){
		location.hash = 'test1='+ this.getAttribute('lay-id');
	});
	
	/**
	 * 函数调用区
	 */
	getPosData();
	doAddEdit();
});

/**
 * 获取url中的参数
 * @param name
 * @returns
 */
/*function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", 'i'); // 匹配目标参数
	var result = window.location.search.substr(1).match(reg); // 对querystring匹配目标参数
	if (result != null) {
		return decodeURIComponent(result[2]);
	} else {
		return null;
  }
}
*/
