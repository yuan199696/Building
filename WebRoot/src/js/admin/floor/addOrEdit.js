//编辑、添加页面

var buildingId ,//接受id信息
	flag; //标识编辑或添加

layui.use(['layedit','form', 'element','laydate'], function(){
  var form = layui.form;
  var element = layui.element;
  
  //配置富文本框
  var layedit = layui.layedit;
  //日期选择
  var laydate = layui.laydate;
  laydate.render({
    elem: '#create_time' //指定元素
  });
  
  layedit.set({
	  uploadImage: {
	    url: '/admin/uploadImage'
	  }
  })
  var layeditInstance = layedit.build('building_bref'); //建立编辑器

  //监听提交
  form.on('submit(formDemo)', function(data){
    $("#building_bref").val(layedit.getContent(layeditInstance));
	 
//    layer.open({type:1
//    	,content:layedit.getContent(layeditInstance)});
//    return false;
  });
  
  //获取hash来切换选项卡，假设当前地址的hash为lay-id对应的值
  var layid = location.hash.replace(/^#test1=/, '');
  element.tabChange('test1', layid); //假设当前地址为：http://a.com#test1=222，那么选项卡会自动切换到“发送消息”这一项
  
  //监听Tab切换，以改变地址hash值
  element.on('tab(test1)', function(){
    location.hash = 'test1='+ this.getAttribute('lay-id');
  });
  
  $(".retenback").on("click", function(){
	 window.location.href="./index.html" 
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

function doEdit(){
	
}

function doAdd(){
	
}

function getData(){
	
}

$(document).ready(function(){
	
	buildingId = getQueryString('bid');
	if (buildingId) {
		
		flag = "edit";
		doEdit();
	} else {
		
		flag = "add";
		doAdd()
	}
	
});

