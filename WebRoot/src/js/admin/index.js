//JavaScript代码区域

layui.use(['table','form','element'], function(){
  var table = layui.table;
  var element = layui.element;
  var form = layui.form;
  var $ = layui.$;
  
  var	maintb = "#main_table",
  		height = 560,
  		titleadd,
  		titledetail,
  		contentadd,
  		contentdetail,
  		popupHeight = "100%",
  		currentNav,
  		popupWidth;
  
  /********************** 函数实现区域 start *********************/
  function renderUser(el, h,url){
	  table.render({
		    elem: el
		    ,height: h
		    ,url: url 
		    ,page: true 
		    ,cols: [[
		      {type:'checkbox', fixed: 'left'}
		      ,{field: 'user_id', title: '员工ID', width:150, sort: true, fixed: 'left'}
		      ,{field: 'user_name', title: '姓名', width:200, sort: true}
		      ,{field: 'position', title: '职位级别', width:200}
		      ,{field: 'telephone', title: '电话', width:200, sort: true} 
		      ,{field: 'email', title: 'Email', width: 200}
		      ,{field: 'comment', title: '备注', width: 100}
		      //这里想加一列单位信息，目的在列表页面就能展示员工单位方便查看，可是这是在这个table里，员工表里没有单位字段
		      ,{fixed: 'right', width:178, align:'center', toolbar: '#barDemo'}
		    ]]
		  });
		  titleadd = "员工添加/编辑";
		  titledetail = "员工信息";
		  contentadd = '../admin/user/addOrEdit.html';
		  contentdetail = '../admin/user/detail.html';
  }
  
  function renderBuilding(el, h,url){
	  table.render({
		    elem: el
		    ,height: h
		    ,url: url
		    ,page: true 
		    ,cols: [[ 
		      {type:'checkbox', fixed: 'left'}
		      ,{field: 'building_id', title: '楼宇标识ID', width:150, sort: true, fixed: 'left'}
		      ,{field: 'building_name', title: '楼宇名称', width:200}
		      ,{field: 'building_position', title: '楼宇位置', width:200} 
		      ,{field: 'floor', title: '楼层高度', width:200}
		      ,{field: 'floor_area', title: '楼层面积', width:200, sort: true}
		      ,{field: 'comment', title: '备注', width: 100}
		      ,{fixed: 'right', width:178, align:'center', toolbar: '#barDemo'}
		    ]]
		  });
		  titleadd = "楼宇添加/编辑";
		  titledetail = "楼宇信息";
		  contentadd = '../admin/building/addOrEdit.html';
		  contentdetail = '../admin/building/detail.html';
  }
  
  function renderRoom(el, h,url){
	  table.render({
		    elem: el
		    ,height: h
		    ,url: url
		    ,page: true 
		    ,cols: [[ 
		      {type:'checkbox', fixed: 'left'}
		      ,{field: 'room_id', title: '房间标识ID', width:150, sort: true, fixed: 'left'}
		      ,{field: 'room_num', title: '房间编号', width:200}
		      ,{field: 'room_area', title: '面积', width:200, sort: true}
		      ,{field: 'room_unit', title: '单元', width:200} 
		      ,{field: 'roomt_host', title: '使用者', width: 200}
		      ,{field: 'comment', title: '备注', width: 100}
		      ,{fixed: 'right', width:178, align:'center', toolbar: '#barDemo'}
		    ]]
		  });
		  titleadd = "房间添加/编辑";
		  titledetail = "房间信息";
		  contentadd = '../admin/room/addOrEdit.html';
		  contentdetail = '../admin/room/detail.html';
  }
  
  function renderAdmin(el, h,url){
	  table.render({
		    elem: el
		    ,height: h
		    ,url: url
		    ,page: true
		    ,cols: [[ 
		      {type:'checkbox', fixed: 'left'}
		      ,{field: 'admin_id', title: '管理员ID', width:150, sort: true, fixed: 'left'}
		      ,{field: 'admin_name', title: '姓名', width:200, sort: true}
		      ,{field: 'admin_passwd', title: '密码', width:200}
		      ,{field: 'admin_tel', title: '电话', width:200, sort: true} 
		      ,{field: 'admin_email', title: 'Email', width: 200}
		      ,{field: 'create_time', title: '创建日期', width: 200}
		      ,{field: 'comment', title: '备注', width: 100}
		      ,{fixed: 'right', width:178, align:'center', toolbar: '#barDemo'}
		    ]]
		  });
		  titleadd = "管理员添加/编辑";
		  titledetail = "管理员信息";
		  contentadd = '../admin/admin/addOrEdit.html';
		  contentdetail = '../admin/admin/detail.html';
  }
  
  function renderEnterprise(el, h,url){
	  table.render({
		    elem: el
		    ,height: h
		    ,url: url
		    ,page: true
		    ,cols: [[ //表头
		      {type:'checkbox', fixed: 'left'}
		      ,{field: 'enterprise_id', title: '企业ID', width:150, sort: true, fixed: 'left'}
		      ,{field: 'enterprise_name', title: '企业名称', width:200}
		      ,{field: 'enterprise_property', title: '企业性质', width:200}
		      ,{field: 'enterprise_scale', title: '企业规模', width:200}
		      ,{field: 'description', title: '描述', width:200, sort: true}
		      ,{field: 'comment', title: '备注', width: 100}
		      ,{fixed: 'right', width:178, align:'center', toolbar: '#barDemo'}
		    ]]
		  });
		  titleadd = "企业添加/编辑";
		  titledetail = "企业信息";
		  contentadd = '../admin/enterprise/addOrEdit.html';
		  contentdetail = '../admin/enterprise/detail.html';
  }
  
  function renderPosition(el, h,url){
	  table.render({
		    elem: el
		    ,height: h
		    ,url: url
		    ,page: true
		    ,cols: [[ //表头
		      {type:'checkbox', fixed: 'left'}
		      ,{field: 'position_id', title: '职位ID', width:150, sort: true, fixed: 'left'}
		      ,{field: 'position_name', title: '职位名称', width:200}
		      ,{field: 'description', title: '描述', width:200}
		      ,{field: 'create_time', title: '创建时间', width:200}
		      ,{field: 'create_user', title: '创建人', width:200}
		      ,{field: 'comment', title: '备注', width: 100}
		      ,{fixed: 'right', width:178, align:'center', toolbar: '#barDemo'}
		    ]]
		  });
		  titleadd = "职位添加/编辑";
		  titledetail = "职位信息";
		  contentadd = '../admin/position/addOrEdit.html';
		  contentdetail = '../admin/position/detail.html';
  }
  
  function renderRole(el, h,url){
	  table.render({
		    elem: el
		    ,height: h
		    ,url: url
		    ,page: true
		    ,cols: [[ //表头
		      {type:'checkbox', fixed: 'left'}
		      ,{field: 'role_id', title: '角色ID', width:150, sort: true, fixed: 'left'}
		      ,{field: 'role_name', title: '角色名称', width:200}
		      ,{field: 'role_type', title: '角色类型', width:200}
		      ,{field: 'status', title: '状态', width:200}
		      ,{field: 'create_time', title: '创建时间', width:200}
		      ,{field: 'comment', title: '备注', width: 100}
		      ,{field: 'create_user', title: '创建人', width:200}
		      ,{field: 'update_user', title: '更新人', width:200}
		      ,{fixed: 'right', width:178, align:'center', toolbar: '#barDemo'}
		    ]]
		  });
		  titleadd = "角色添加/编辑";
		  titledetail = "角色信息";
		  contentadd = '../admin/role/addOrEdit.html';
		  contentdetail = '../admin/role/detail.html';
  }
  
  function renderPermission(el, h,url){
	  table.render({
		    elem: el
		    ,height: h
		    ,url: url
		    ,page: true
		    ,cols: [[ //表头
		      {type:'checkbox', fixed: 'left'}
		      ,{field: 'permission_id', title: '权限ID', width:150, sort: true, fixed: 'left'}
		      ,{field: 'permission_name', title: '权限名称', width:200}
		      ,{field: 'permission_code', title: '权限编码', width:200}
		      ,{field: 'permission_type', title: '权限类型', width:200}
		      ,{field: 'create_time', title: '创建时间', width: 100}
		      ,{field: 'comment', title: '备注', width: 100}
		      ,{field: 'create_user', title: '创建人', width:200}
		      ,{field: 'update_user', title: '更新人', width:200}
		      ,{fixed: 'right', width:178, align:'center', toolbar: '#barDemo'}
		    ]]
		  });
		  titleadd = "权限添加/编辑";
		  titledetail = "权限信息";
		  contentadd = '../admin/permission/addOrEdit.html';
		  contentdetail = '../admin/permission/detail.html';
  }
  /********************** 函数实现区域 end *********************/
  
  /********************** 事件绑定 start ***********************/
  	//building
  $('#building_manage').on('click',function(){
	  
	  currentNav = "building";
	  renderBuilding(maintb,height,'/Building/building/allWithPage');
  });
  
  	//room
	$('#room_manage').on('click',function(){
		
		currentNav = "room";
		renderRoom(maintb,height,'/Building/building/allRoomWithPage');
	});
	
	//admin
	$('#admin_manage').on('click',function(){
		
		currentNav = "admin";
		renderAdmin(maintb,height,'/Building/admin/allWithPage');
	});
	
	//user
	$('#user_manage').on('click',function(){
		
		currentNav = "user";
		renderUser(maintb,height,'/Building/user/allWithPage');
	});
	
	//enterprise
	$('#enterprise_manage').on('click',function(){
		
		currentNav = "enterprise";
		renderEnterprise(maintb,height,'/Building/enterprise/allWithPage');
	});
	
	$('#position_manage').on('click',function(){
		
		currentNav = "position";
		renderPosition(maintb,height,'/Building/enterprise/allPosWithPage');
	});
	
	//role
	$('#role_manage').on('click',function(){
		
		currentNav = "role";
		renderRole(maintb,height,'/Building/admin/allRoleWithPage');
	});
	
	//permission
	$('#permission_manage').on('click',function(){
		
		currentNav = "permission";
		renderPermission(maintb,height,'/Building/admin/allPermissionWithPage');
	});

	$("#top_search_btn").on("click", function(){
		var searchFilter = $("#filter_select").val()|| "username";
		var search = $.trim($("#top_search_input").val());
		switch(searchFilter){
		case "username":
		case "usertelephone":
			renderUser(maintb,height,'/Building/user/queryWithParam?params='+search+'&sf='+searchFilter);
			break;
		case "ename":
			renderBuilding(maintb,height,'/Building/building/queryWithParam?params='+search+'&sf='+searchFilter);
			break;
		case "bname":
			renderBuilding(maintb,height,'/Building/building/queryWithParam?params='+search+'&sf='+searchFilter);
			break;
		default:
			break;
		}
		
	});
	
	$("#top_search_input").on("keypress", function(){
		if (event && event.keyCode == 13){
			$("#top_search_btn").click();
		}
	});
	
	/********************** 事件绑定 end ***********************/
	
	table.on('checkbox(demo)', function(obj){
		console.log(obj)
	});
  //监听工具条
	table.on('tool(demo)', function(obj){
	  
    var data = obj.data;
    var curId = function(){
    	for(item in obj.data){
    		if (item.indexOf('id') > 0){return obj.data[item];}
    	}
    }();
    var params = "?bid="+curId;
    if(obj.event === 'detail'){
    	layer.config({
    		skin:'popup-window'
    	})
    	layer.open({
    		title:titledetail
    		,type:2
    		,content:[contentdetail+params]
    		,area:[popupWidth,popupHeight]
    		,offset:["0","200px"]
    	});
    	
      
    } else if(obj.event === 'del'){
    	var bid = data.building_id;
    	
      layer.confirm('真的删除行么', function(index){
    	  var url = "";
    	  switch(currentNav) {
    	  case "building":
    		  url = '/Building/building/deleteBuilding?bid='+curId;
    		  break;
    		  
    	  case "admin":
    		  url = '/Building/admin/deleteAdmin?bid='+curId;
    		  break;
    		  
    	  case "user":
    		  url = '/Building/user/deleteUser?bid='+curId;
    		  break;
    		  
    	  case "enterprise":
    		  url = '/Building/enterprise/deleteEnterp?bid='+curId;
    		  break;
    		  
    	  case "position":
    		  url = '/Building/enterprise/deletePos?bid='+curId;
    		  break;
    		  
    	  case "room":
    		  url = '/Building/building/deleteRoom?bid='+curId;
    		  break;
    		  
    	  case "role":
    		  url = '/Building/admin/deleteRole?bid='+curId;
    		  break;
    	  case "permission":
    		  url = '/Building/admin/deletePermission?bid='+curId;
    		  break;
    	  default :
    		  break;
    	  }
    	//ajax请求后台删除
    	  $.get(
    		url
    		,function(data,status){
    			
    			if(data.code == 0){
    				obj.del();
    				layer.msg('删除成功！', {
    					  icon: 1,
    					  time: 2000 //2秒关闭（如果不配置，默认是3秒）
    					}, function(){
    					  //do something
    					});   
    			}else{
    				layer.msg('删除失败！', {
    					  icon: 2,
    					  time: 2000 //2秒关闭（如果不配置，默认是3秒）
    					}, function(){
    					  //do something
    					});  
    			}
    		}
    	  );
        
        layer.close(index);
      });
    } else if(obj.event === 'edit'){
    	
    	layer.config({
			skin:'popup-window'
		});
		layer.open({
			title:titleadd
			,type:2
			,content:[contentadd+params]
			,area:[popupWidth,popupHeight]
			,offset:["0","200px"]
		});
	 }
  });
  
  var active = {
    getCheckData: function(){ //获取选中数据
      var checkStatus = table.checkStatus('idTest')
      ,data = checkStatus.data;
      layer.alert(JSON.stringify(data));
    }
    ,getCheckLength: function(){ //获取选中数目
      var checkStatus = table.checkStatus('idTest')
      ,data = checkStatus.data;
      layer.msg('选中了：'+ data.length + ' 个');
    }
    ,isAll: function(){ //验证是否全选
      var checkStatus = table.checkStatus('idTest');
      layer.msg(checkStatus.isAll ? '全选': '未全选')
    }
  };
  
  $('.demoTable .layui-btn').on('click', function(){
    var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });

  popupWidth = $(window).width() - 200;
  popupWidth = (typeof(popupWidth) == 'number' && popupWidth > 0)?popupWidth+"px":1320+"px";
  
  /*********  添加按钮点击事件********/
  $("#add_btn").on("click", function(){
	  layer.open({
		title:titleadd
		,type:2
		,content:[contentadd]
		,area:[popupWidth,popupHeight]
	  	,offset:["0","200px"]
	});
  });
  
  setTimeout(function(){
	  $('#building_manage').click();
  },100)
});