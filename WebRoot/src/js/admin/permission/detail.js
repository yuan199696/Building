//detail页面

var lang = window.top.getLang();
lang = lang ? lang : "en_US";

var extendFieldInfo;

layui.use(['form', 'element'], function(){
  var form = layui.form;
  var element = layui.element;
  
  //获取hash来切换选项卡，假设当前地址的hash为lay-id对应的值
  var layid = location.hash.replace(/^#test1=/, '');
  element.tabChange('test1', layid); //假设当前地址为：http://a.com#test1=222，那么选项卡会自动切换到“发送消息”这一项
  
  //监听Tab切换，以改变地址hash值
  element.on('tab(test1)', function(){
    location.hash = 'test1='+ this.getAttribute('lay-id');
  });
  
});


$(document).ready(function(){
	
	getData();
	//getExtendInfoField("/Building/building/allExtendInfoField", "detail", extendFieldInfo);
	
});

function getData(){
	
	var bid = getQueryString('bid');
	var toDat = {"bid":bid};
	var getdata = callService("/Building/admin/getPermissionById","post",toDat);
	if (getdata) {
		
		if (getdata.extend_field) {
			extendFieldInfo = JSON.parse(getdata.extend_field);
		}
		
		if (getdata && getdata.building_bref) {
			renderBref(getdata.building_bref);
		}
		renderFormDTU(getdata,"permission");
	}
}
