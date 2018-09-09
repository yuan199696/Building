/**
 * 公共方法以及变量
 */
var Glayer;

layui.use(['form'], function(){
	Glayer = layer;
});

var confs = {
	staff: [{id: "user_id", name: "员工ID"},
	         {id: "user_name", name: "姓名"},
	         {id: "heslo", name: "密码"},	
	         {id: "identifyid", name:"身份证"},
	         {id: "birthday", name:"出生年月"},
	         {id: "authorized", name:"是否编制"},
	         {id: "hiredate", name: "入职日期"},
	         {id: "telephone", name: "电话"},
	         {id: "email", name: "邮箱"},
	         {id: "user_enterprise", name: "单位"},
	         {id: "user_position", name: "职位"},
	         {id: "user_src", name:"来源"},
	         {id: "user_type", name:"类型"},
	         {id: "address", name:"地址"},
	         {id: "hobby", name:"爱好"},
	         {id: "create_time", name: "录入时间"},
	         {id: "update_time", name: "最近修改时间"},
	         {id: "create_user", name: "创建人"},
	         {id: "update_user", name: "最近修改人"},
	         {id: "comment", name: "备注"}],
     building: [{ id: "building_name", name: "大楼名称"},
	            { id: "building_id", name: "大楼编号"},
     			{ id: "building_position", name: "大楼位置"},
     			{ id: "building_direction", name: "大楼朝向"},
     			{ id: "building_date", name: "建楼时间"},
     			{ id: "floor", name: "楼层"},
     			{ id: "floor_area", name: "楼层面积"},
     			{ id: "description", name: "描述"},
     			{ id: "comment", name: "备注"}],
      room: [{ id: "room_id", name: "房间ID"},
     			{ id: "room_num", name: "房间号"},
     		    { id: "room_building", name: "所属大楼"},
     		    { id: "room_unit", name: "单元号"},
     		    { id: "room_floor", name: "房间楼层"},
     		    { id: "room_area", name: "房间面积"},
     		    { id: "room_pictures", name: "房间图片"},
     		    { id: "room_host", name: "房间使用者"},
     		    { id: "isusing", name: "房间使用情况"},
     		    { id: "room_type", name: "房型"},
     		    { id: "detail", name: "房间信息"},
     		    { id: "room_direction", name: "房间朝向"}],
     enterprise: [{ id: "enterprise_id", name: "企业ID"},
     	     		{ id: "enterprise_name", name: "企业名称"},
     	     		{ id: "enterprise_property", name: "企业性质"},
     	     	    { id: "enterprise_scale", name: "规模"},
     	     	    { id: "homepage", name: "企业主页"},
     	     	    { id: "enterprise_avatar", name: "企业logo"},
     	     	    { id: "establishment_time", name: "成立时间"},
     	     	    { id: "email", name: "企业邮箱"},
     	    	    { id: "enterprise_bref", name: "企业简介"},
     	   		    { id: "create_time", name: "建立时间"}],
     	position: [{ id: "position_id", name: "职位ID"},
     	      	  		{ id: "position_name", name: "职位名称"},
     	      	    	{ id: "type", name: "职位类型"},
     	      	   	    { id: "create_time", name: "创建时间"},
     	      	   	    { id: "status", name: "状态"},
     	          	    { id: "comment", name: "备注"},
     	   	     	    { id: "create_user", name: "创建人"},
         	     	    { id: "update_user", name: "更新人"}],
         admin: [{ id: "admin_id", name: "管理员ID"},
              	      { id: "admin_name", name: "管理员名称"},
              	      { id: "heslo", name: "密码"},
              	      { id: "admin_tel", name: "手机号"},
              	      { id: "admin_email", name: "邮箱"},
              	      { id: "comment", name: "备注"},
              	      { id: "create_time", name: "创建时间"},
              	      { id: "status", name: "状态"}],
          role: [{ id: "role_id", name: "角色ID"},
                    { id: "role_name", name: "角色名称"},
                    { id: "role_type", name: "角色类型"},
                    { id: "status", name: "状态"},
                    { id: "create_time", name: "创建时间"},
                    { id: "comment", name: "备注"},
                    { id: "create_user", name: "创建人"},
                    { id: "update_user", name: "更新人"}],
           permission: [{ id: "permission_id", name: "权限ID"},
                       { id: "permission_name", name: "权限名称"},
                       { id: "permission_code", name: "权限编码"},
                       { id: "permission_type", name: "权限类型v"},
                       { id: "create_time", name: "创建时间"},
                       { id: "comment", name: "备注"},
                       { id: "permission_detail", name: "更多信息"},
                       { id: "create_user", name: "创建人"},
                       { id: "update_user", name: "更新人"}]
   	      	    	  
	};


//为配置单选复选数据
var _radio_data = {
		authorized: [
                    {name:"authorized", title:"是", value:"0"},
                    {name:"authorized", title:"否", value:"1"}
               ]
}

/**
 * 
 * @param id
 */
function initRadioInput(id) {
	var radio_auth = $("#"+id);
	if (_radio_data[id]) {
		var str = "";
		for (var i = 0; i < _radio_data[id].length; i++) {
			str += '<input type="radio" name="' + _radio_data[id][i].name 
				+ '" value="'
				+ _radio_data[id][i].value 
				+ '" title="'
				+ _radio_data[id][i].title
				+'" >'
		}
		radio_auth.append($(str));
	}
}

/**
 * 图片预览
 */
function imgRender(){ 
	var input = event && event.currentTarget; 
    var file = input && input.files[0]; //获取file对象
    var prev = $("#preview_image");
    var temp;
    if (file) {

    	tempInput = input.cloneNode(true);
    	if(!/image\/\w+/.test(file.type)){ 
	        alert("文件必须为图片！"); 
	        return false; 
	    } 
    	    
	    var reader = new FileReader();
	    reader.readAsDataURL(file); //调用readAsDataURL方法来读取选中的图像文件
	    reader.onload = function(e){ 
	    	prev.html($('<img  width="300" src="'+this.result+'" alt=""/>')); 
	    } 
    } else if(tempInput){
    	
    	input.remove();
    	$("#preview_input").append(tempInput);
    }
}

/**
 * 
 * @param str
 * @returns
 */
function camelCase(str){
	var rdashAlpha = /_([a-z]|[0-9])/ig,
	fcamelCase = function( all, letter ) {
		return ( letter + "" ).toUpperCase();
	};
	
	return str.replace( rdashAlpha, fcamelCase );
}

/**
 * 
 * @param cont 下拉框
 * @param data 数据
 */
function renderSelect(cont, data){
	var isExist = false;
	if (cont){
		var opt = cont.options;
		if (opt && opt.length > 0) {
			for (var i = 0; i < opt.length; i++) {
				if (opt[i].value == data) {
					opt[i].selected = true;
					isExist = true;
					break;
				}
			}
			if (!isExist) {
				cont.add(new Option(data,data));
			}
		} else {
			cont.add(new Option(data,data));
		}
	}
}

/**
 * 
 * @param cont
 * @param data
 */
function renderInput(cont, data){
	
	if (cont){
		switch (cont.type){
		case "text":
			renderItemText(cont, data);
			break;
		case "password":
			renderItemPass(cont, data);
			break;
		case "checkbox":
			renderItemCheckbox(cont, data);
			break;
		case "radio":
			renderItemRadio(cont, data);
			break;
		case "file":
			renderItemFile(cont, data);
			break;
		default:
			break;
		}
	}
}

/**
 * 
 * @param cont
 * @param data
 */
function renderItemText(cont, data){
	if (cont) {
		cont.value = data;
	}
}

/**
 * 
 * @param cont
 * @param data
 */
function renderItemPass(cont, data){
	if (cont) {
		cont.value = data;
	}
}

/**
 * 
 * @param cont
 * @param data
 */
function renderItemCheckbox(cont, data){
	
}

/**
 * 
 * @param cont
 * @param data
 */
function renderItemFile(cont, data){
	
}

/**
 * 
 * @param cont
 * @param data
 */
function renderItemRadio(cont, data){
	if (cont && cont.value == data) {
		cont.checked=true;
	}
}

/**
 * callService
 * @param url
 * @param type
 * @param data
 * @param asyn
 * @param callbak
 * @param fail
 * @returns {Boolean}
 */
function callService(url, type, data, asyn, callbak, fail){
	
	var res;
	if (!url) {
		return false;
	}
	if (type && type != "get") {
		$.ajax({
			url: url,
			type:type,
			data:data,
			dataType:"json",
			async: false,
			success:function(data){
				
				if (data && data.code == 0) {
					res = data.data;
				}
			},
			error: function(err){
				Glayer.alert("Request ERR: >>" + err);
			}
		})
	} else {
		$.ajax({
			url: url,
			type:"get",
			dataType:"json",
			async: false,
			success:function(data){
				if (data && data.code == 0) {
					res = data.data;
				}
			},
			error: function(err){
				Glayer.alert("Request ERR: >>" + err);
			}
		})
	}
	return res;
}

/**
 * 获取url参数
 * @param name
 * @returns
 */
function getQueryString(name) {
	if (!name || name == "") {
		return null
	}
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", 'i'); // 匹配目标参数
	var result = window.location.search.substr(1).match(reg); // 对querystring匹配目标参数
	if (result != null) {
		return decodeURIComponent(result[2]);
	} else {
	    return null;
	}
}

/**
 * 渲染详情页面表单
 * @param data
 * @returns {Boolean}
 */
function renderFormDTU(data, showcfg){
	
	if (!data || data.length < 1 || !showcfg) {
		return false;
	}
	var formItems = "";
	var detailForm = $("#detail_form");
	
	$.each(confs[showcfg], function(index, conf){
		formItems += '<div class="layui-form-item">'
			+ '<label class="layui-form-label">'+ conf.name +'</label>'
			+ '<div class="layui-input-block">'
			+ '<div class="input-block-label" id="">'+ data[conf.id] +'</div>'
			+ '</div>'
			+ '</div>'
	});
	$("#user_avatar_img").attr("src", data["user_avatar"]);
	detailForm.append(formItems);
}

/**
 * renderbref
 * @param bref
 */
function renderBref(bref){
	
	$("#edt_bref_container").html($(bref));
}

/**
 * 
 */
function bindAddNewField(url){
	$("#new_field_btn").on("click", function(evt){
		evt.preventDefault();
		var str = '';
		str += '<div class="layui-form-item">'
			+ '<label class="layui-form-label">字段名称</label>'
			+ '<div class="layui-input-block">'
			+ '<input type="text" class="layui-input" style="width: 330px;margin-right: 10px;display: inline-block;">'
			+ '<button  title="保存" onclick="saveNewField(this)" data-url="'
			+ url
			+'" class="layui-btn"><i class="layui-icon layui-icon-ok-circle"></i></button><button title="删除"  onclick="deleteNewField(this)" class="layui-btn layui-btn-danger"><i class="layui-icon"></i></button>'
			+ '</div>'
			+ '</div>';
		evt && $(evt.currentTarget).closest(".layui-form-item").after($(str));
	});
}

/**
 * 渲染extend
 * @param data
 * @returns {Boolean}
 */
function renderExtendDTU(serData, curExt) {
	
	if (!serData || serData.length < 1) {
		return false;
	}
	var formItems = "";
	var detailForm = $("#detail_form");
	
	$.each(serData, function(index, ext){
		formItems += '<div class="layui-form-item">'
			+ '<label class="layui-form-label">'+ ext.field_name +'</label>'
			+ '<div class="layui-input-block">'
			+ '<div class="input-block-label" id="">'+ ((curExt && curExt[ext.id])?curExt[ext.id]:"") +'</div>'
			+ '</div>'
			+ '</div>'
	});
	
	detailForm.append(formItems);
}

/**
 * 
 */
function renderExtendEDT(serData, curExt){
	
	if (!serData || serData.length < 1) {
		return false;
	}
	
	var formItems = "";
	$.each(serData, function(index, ext){
		
		extendFields && extendFields.push(ext.id);
		
		formItems += '<div class="layui-form-item">'
			+ '<label class="layui-form-label">'+ ext.field_name +'</label>'
			+ '<div class="layui-input-block">'
			+ '<input type="text" class="layui-input" style="display: inline-block;width: calc( 100% - 30px);margin-right: 5px;" name="'+ ext.id +'" value="'+ ((curExt && curExt[ext.id])?curExt[ext.id]:'') +'">'
			+ '<span title="删除该字段" style="font-size:22px;cursor: pointer;" onclick="deleteExtendInfoField(this)"><i class="layui-icon layui-icon-delete"></i></span>'
			+ '</div>'
			+ '</div>';
	});
	
	$("#new_field_btn").closest(".layui-form-item").before($(formItems));
}

/**
 * @param url
 * @param flag
 * @param values
 * @returns void
 */
function getExtendInfoField(url, flag, values){

	var haha = "new_field_btn";
	var getdata = callService(url);
	if (getdata) {
		if (flag && flag == 'detail') {
			
			renderExtendDTU(getdata, values);
		} else {
			
			renderExtendEDT(getdata, values);
		}
		
	}
}

/**
 * 
 * @param evt
 * @returns {Boolean}
 */
function saveNewField(evt){
	
	event.preventDefault();
	var url = $(evt).data('url') || "";
	var data ={}
	data.field = $(evt).prev().val();
	if (!data.field || data.field.length <=0) {
		Glayer.alert("请填写需要新增的字段名称！");
		return false;
	}
	
	$.ajax({
		url:url,
		type:"post",
		data:data,
		dataType:"json",
		success: function(data){
			if (data && data.code == 0 && data.data){
				var serData = data.data;
				if (Object.prototype.toString.call(data.data).indexOf('Array') <= 0) {
					serData = new Array(data.data);
				}
				renderExtendEDT(serData);
				extendFields && extendFields.push(data.data.id);
				evt && $(evt).closest(".layui-form-item").remove();
			} else {
				
				Glayer.alert(data.msg);
			}
		},
		error: function(){
			
		}
	})
}

/**
 * 
 * @param evt
 */
function deleteNewField(evt){
	event.preventDefault();
	evt && $(evt).closest(".layui-form-item").remove();
}

/**
 * 
 * @param evt
 * @param cbUrl
 */
function deleteExtendIFD(evt,cbUrl){
	if (!evt) {	return false;}
	layer.confirm('删除该字段可能会影响到已有数据，确认删除么', function(index){
		var inpItem = $(evt).closest(".layui-form-item");
		var inp = inpItem && inpItem.find("input");
		if (!inp || !inp.attr("name") || inp.attr("name").length <= 0) {
			
			layer.close(index);
			return false;
		}
		cbUrl && $.get(
    		cbUrl+inp.attr("name")
    		,function(data,status){
    			
    			if(data.code == 0){
    				inpItem.remove();
    				layer.msg(data.msg, {
    					  icon: 1,
    					  time: 2000 
    					}, function(){
    					});   
    			}else{
    				layer.msg(data.msg, {
    					  icon: 2,
    					  time: 2000 
    					}, function(){
    					});  
    			}
    		}
    	  );
        layer.close(index);
      });
}