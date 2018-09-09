
var lang = window.top.getLang();
lang = lang ? lang : "en_US";

//detail页面

layui.use(['form', 'element'], function(){
  var form = layui.form;
  var element = layui.element;

  //监听提交
  form.on('submit(formDemo)', function(data){
    layer.msg(JSON.stringify(data.field));
    return false;
  });
  
  //获取hash来切换选项卡，假设当前地址的hash为lay-id对应的值
  var layid = location.hash.replace(/^#test1=/, '');
  element.tabChange('test1', layid); //假设当前地址为：http://a.com#test1=222，那么选项卡会自动切换到“发送消息”这一项
  
  //监听Tab切换，以改变地址hash值
  element.on('tab(test1)', function(){
    location.hash = 'test1='+ this.getAttribute('lay-id');
  });
  
});

//获取url中的参数
function getQueryString(name) {
  var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", 'i'); // 匹配目标参数
  var result = window.location.search.substr(1).match(reg); // 对querystring匹配目标参数
  if (result != null) {
    return decodeURIComponent(result[2]);
  } else {
    return null;
  }
}

$(document).ready(function(){
	
	getData();
	
});

function getData(){
	var bid = getQueryString('bid');
	$.ajax({
		url: '/Building/building/getBuildingById'
		,type:'post'
		,data:{"bid":bid}
//		,dataType:'json'
		,success: function(data){
			if (data && data.code == 0) {
				if (data.data && data.data.building_bref) {
					renderBref(data.data.building_bref);
				}
				renderForm(data.data);
			}
		}
	})
}

function renderBref(bref){
	$("#building_bref_container").html($(bref));
}

function renderForm(data){
	
	if (!data || data.length < 1) {
		return false;
	}
	var formItems = "";
	var detailForm = $("#detail_form");
	var confs = {
			building: [
	           			{
	        				id: "building_id",
	        				name: "大楼编号"
	        			},
	        			{
	        				id: "building_name",
	        				name: "大楼名称"
	        			},
	        			{
	        				id: "building_position",
	        				name: "大楼位置"
	        			},
	        			{
	        				id: "building_direction",
	        				name: "大楼朝向"
	        			},
	        			{
	        				id: "building_date",
	        				name: "建楼时间"
	        			},
	        			{
	        				id: "floor",
	        				name: "楼层"
	        			},
	        			{
	        				id: "description",
	        				name: "描述"
	        			},
	        			{
	        				id: "comment",
	        				name: "备注"
	        			}]
	};
	
	$.each(confs["building"], function(index, conf){
		formItems += '<div class="layui-form-item">'
			+ '<label class="layui-form-label">'+ conf.name +'</label>'
			+ '<div class="layui-input-block">'
			+ '<div class="input-block-label" id="">'+ data[conf.id] +'</div>'
			+ '</div>'
			+ '</div>'
	});
	
	detailForm.append(formItems);
	/*$.getJSON("../../src/conf/conf.json", function(c){
		var confs = c[0][lang]["building"];
		$.each(confs, function(conf){
			formItems += '<div class="layui-form-item">'
				+ '<label class="layui-form-label">'+ conf.name +'</label>'
				+ '<div class="layui-input-block">'
				+ '<div id="">'+ data[conf.id] +'</div>'
				+ '</div>'
				+ '</div>'
		});
		
		detailForm.append(formItems);
	});*/
}


