//编辑、添加页面
var InfoId ,//接受id信息
	flag,
	pageData,
	tempInput,
	extendFields = []; //标识编辑或添加

	InfoId = getQueryString('bid');
	if (InfoId) {
		flag = "edit";
	} else {
		flag = "add";
	}

/**
 * 删除
 * @param evt
 */
/*function deleteExtendInfoField (evt){
	
	deleteExtendIFD(evt,'/Building/building/deleteExtendInfoField?fieldId=');
}*/

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
	
	var layeditInstance = layedit.build('admin_detail'); //建立编辑器
	/****************** 函数实现代码区域 start ******************/
	/**
	 * 
	 */

	
	/**
	 * 
	 */
	function getAdminData(){
		$.ajax({
			url:"/Building/admin/all",
			dataType:"json",
			success: function(data){
				if (data && data.code == 0){
					renderAdminField(data.data);
				}
				
			},
			error: function(){
				
			}
		})
	}
	
	function renderAdminField(poss){
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
	 * 编辑
	 */
	function doEdit(){
		
		var inputs = document.body.querySelectorAll("input");
		var selects = document.body.querySelectorAll("select");
		var res = callService("/Building/admin/getAdminById?bid="+InfoId);
		
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
				if (inputs[i].name == "adminId") {
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
			
			$("form").attr("action","/Building/admin/updateAdmin");
			doEdit();
		} else if (flag == "add"){
			doAdd();
		}
		
		imagePreview();
	}
	
	/****************** 函数实现代码区域 end ******************/
	//监听提交
	form.on('submit(formDemo)', function(data){
		var extendFieldsData = {};
		
		$("#admin_detail").val(layedit.getContent(layeditInstance));
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
	
	getAdminData();
	doAddEdit();
	//var extValues = pageData && pageData.extend_field && JSON.parse(pageData.extend_field);
	//getExtendInfoField("/Building/building/allExtendInfoField", flag, extValues);
	//bindAddNewField("/Building/building/addNewField");
});