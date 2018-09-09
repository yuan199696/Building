package cn.sunnypuma.buildingtax.application.building.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

import cn.sunnypuma.buildingtax.application.building.interceptor.BuildingInterceptor;
import cn.sunnypuma.buildingtax.application.building.service.BuildingExtendService;
import cn.sunnypuma.buildingtax.application.building.service.BuildingService;
import cn.sunnypuma.buildingtax.application.building.service.RoomService;
import cn.sunnypuma.buildingtax.application.common.model.base.BuildingInfo;
import cn.sunnypuma.buildingtax.application.common.model.base.BuildingInfoExtend;
import cn.sunnypuma.buildingtax.application.common.model.base.RoomInfo;
import cn.sunnypuma.buildingtax.util.StringUtil;

/**
 * ��ҳ������
 * 
 * @author acer
 *
 */
@Before(BuildingInterceptor.class)
public class IndexController extends Controller {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	private static BuildingService buildingService = new BuildingService();
	private static RoomService roomService = new RoomService();
	private static BuildingExtendService buildingextendservice = new BuildingExtendService();
	
//	@Clear
//	public void index() {
//		render("../admin/building/index.html");
//	}
//	
//	@Clear
//	public void room() {
//		render("../admin/room/index.html");
//	}
	
	public void test2() {
		Map<String, Object> map = new HashMap<>();
		map.put("a", 1);
		renderJson(map);
	}

	/**
	 * 获取数据 为数据表格提供接口，需要接收limit 和 page参数进行分页
	 * 
	 */
	public void all() {
		Map<String, Object> result = new HashMap<>();
		List<BuildingInfo> buildings = buildingService.all();
		result.put("code", 0);
		result.put("count", buildings.size());
		result.put("msg", "");
		result.put("data", buildings);
		renderJson(result);
	}
	
	/**
	 * 带分页的 获取所有数据的接口
	 */
	public void allWithPage() {
		int page = getParaToInt("page")==null?1:getParaToInt("page");
		int limit = getParaToInt("limit")==null?10:getParaToInt("limit");
		logger.info("page:{},limit:{}",page,limit);
		
		Map<String, Object> result = new HashMap<>();
		Page<BuildingInfo> buildings = null;
		buildings = buildingService.all(page,limit);
		
		List<BuildingInfo> buildingList = buildings.getList();
		logger.debug("buildingList:{}",buildingList);
		
		int count = buildings.getTotalRow();
		result.put("code", 0);
		result.put("count", count);
		result.put("msg", "");
		result.put("data", buildingList);
		renderJson(result);		
	}

	public void queryWithParam(){
		
		int page = getParaToInt("page")==null?1:getParaToInt("page");
		int limit = getParaToInt("limit")==null?10:getParaToInt("limit");
		
		String params = getPara("params");
		String condition = getPara("sf");
		condition = condition.substring(1);
		Map<String, Object> result = new HashMap<>();
		Page<BuildingInfo> buildings = null;
		if (params == null || params.equals("") ) {
			
			buildings = buildingService.all(page,limit);
		} else if(params.length() > 0){
			
			buildings = buildingService.queryWithParam(page, limit, params,condition);
		}
		List<BuildingInfo> buildingList = buildings.getList();
		logger.debug("buildingList:{}",buildingList);
		
		int count = buildings.getTotalRow();
		result.put("code", 0);
		result.put("count", count);
		result.put("msg", "");
		result.put("data", buildingList);
		renderJson(result);		
	}
	
	public void test() {
		BuildingInfo building = buildingService.findById("1");
		if (building != null) {

			renderJson(building);
		}
	}
	
	public void allRoomWithPage() {
		int page = getParaToInt("page")==null?1:getParaToInt("page");
		int limit = getParaToInt("limit")==null?10:getParaToInt("limit");
		
		
		logger.info("page:{},limit:{}",page,limit);
		
		Map<String, Object> result = new HashMap<>();
		Page<RoomInfo> rooms = roomService.all(page,limit);
		List<RoomInfo> roomList = rooms.getList();
		logger.debug("roomList:{}",roomList);
		
		int count = rooms.getTotalRow();
		result.put("code", 0);
		result.put("count", count);
		result.put("msg", "");
		result.put("data", roomList);
		renderJson(result);	
	}
	
	/**
	 * http://localhost:8088/building/addRoomInfo
	 * 添加测试数据 房间信息
	 */
//	public void addRoomInfo() {
//		for(int i = 1;i<10;i++) {
//			for(int j = 0;j<9;j++) {
//				String id = "room_"+ System.currentTimeMillis() + StringUtil.getRandomStr(6);
//				//房间号
//				String roomNum = String.valueOf(i)+"0"+String.valueOf(j);
//				RoomInfo room = new RoomInfo();
//				room.setRoomId(id);
//				room.setRoomNum(roomNum);
//				roomService.save(room);
//			}
//		}
//		renderText("ok");
//	}
	
	
public void allExtendInfoField(){
		
		Map<String, Object> result = new HashMap<>();
		List<BuildingInfoExtend> infoextends = buildingextendservice.all();
		result.put("code", 0);
		result.put("msg", "");
		result.put("count", infoextends.size());
		result.put("data", infoextends);
		renderJson(result);
	}
	
	/**
	 * 添加字段
	 */
	public void addNewField(){
		String field = getPara("field");
		Map<String, Object> result = new HashMap<>();
		
		BuildingInfoExtend extendGet = buildingextendservice.getByFieldName(field);
		if (extendGet != null) {
			result.put("code", 2);
			result.put("msg", "字段已经存在");
			renderJson(result);
			return ;
		}
		
		Map<String, Object> resDat = new HashMap<>();
		BuildingInfoExtend buildinginfoextend = getBean(BuildingInfoExtend.class,"");
		String id = "extend_" + System.currentTimeMillis() + StringUtil.getRandomStr(6);
		buildinginfoextend.setId(id);
		buildinginfoextend.setFieldName(field);
		
		boolean success = buildingextendservice.save(buildinginfoextend);
		if (success) {
			resDat.put("field_name", field);
			resDat.put("id", id);
			result.put("code", 0);
			result.put("msg", "");
			result.put("data", resDat);
		}else {
			
			result.put("code", 1);
			result.put("msg", "saved error!");
		}
		renderJson(result);
	}
	
	
	public void deleteExtendInfoField(){
		
		String fid = getPara("fieldId");
		Map<String, Object> result = new HashMap<>();
		
		boolean success = buildingextendservice.deleteById(fid);
		if (success) {
			
			result.put("code", 0);
			result.put("msg", "删除成功");
		} else {
			
			result.put("code", 1);
			result.put("msg", "删除失败");
		}
		renderJson(result);
	}
	
	/**
	 * 更新大楼信息
	 */
	public void update() {
		String buildingId;
		BuildingInfo buildingInfo = getBean(BuildingInfo.class,"");
		logger.info("buildingInfo:{}",buildingInfo);
		buildingId = buildingInfo.getBuildingId();
		if (buildingId == "" || buildingId == null) {
			buildingId = "building_" + System.currentTimeMillis() + StringUtil.getRandomStr(6);
		}
		
		buildingInfo.setBuildingId(buildingId);
		boolean success = buildingService.update(buildingInfo);
		if(success) {
			renderText("修改成功！ <h1>:)</h1>");
		}else {
			renderText("修改失败！ <h1>:(</h1>");
		}
	}
	
	/**
	 * 更新房间信息
	 */
	public void updateRoom() {
		String roomId;
		RoomInfo roomInfo = getBean(RoomInfo.class,"");
		logger.info("roomInfo:{}",roomInfo);
		roomId = roomInfo.getRoomId();
		if (roomId == "" || roomId == null) {
			roomId = "room_" + System.currentTimeMillis() + StringUtil.getRandomStr(6);
		}
		
		roomInfo.setRoomId(roomId);
		boolean success = roomService.updateRoom(roomInfo);
		if(success) {
			renderText("修改成功！ <h1>:)</h1>");
		}else {
			renderText("修改失败！ <h1>:(</h1>");
		}
	}
	
	
	/**
	 * 添加测试数据 大楼信息
	 */
	public void addBuildingInfo() {
		for (int i = 0; i < 100; i++) {
			BuildingInfo buildingInfo = new BuildingInfo();
			String id = "building_" + System.currentTimeMillis() + StringUtil.getRandomStr(6);
			buildingInfo.set("building_id", id);
			buildingInfo.set("building_name", "大楼" + i);
			buildingInfo.set("building_position", StringUtil.getRandomStr(10));
			buildingInfo.set("floor", StringUtil.getRandomStr(10));
			buildingInfo.set("description", StringUtil.getRandomStr(10));
			buildingService.save(buildingInfo);
		}
		renderText("ok");
	}
	
	/**
	 * 删除大楼
	 */
	public void deleteBuilding(){
		String buildingid = getPara("bid");
		logger.info("deleteBuilding:{}",buildingid);
		Map<String, Object> map = new HashMap<>();
		boolean success = buildingService.deleteById(buildingid);
		logger.info("deleteBuilding result:{}",success);
		int res = -1;
		if (success) {
			res = 0;
		} 
		map.put("code", res);
		renderJson(map);
	}
	
	
	/**
	 * 删除房间
	 */
	public void deleteRoom(){
		String roomid = getPara("bid");
		logger.info("deleteRoom:{}",roomid);
		Map<String, Object> map = new HashMap<>();
		boolean success = roomService.deleteById(roomid);
		logger.info("deleteRoom result:{}",success);
		int res = -1;
		if (success) {
			res = 0;
		} 
		map.put("code", res);
		renderJson(map);
	}
	
	public void getBuildingById(){
		String buildingid = getPara("bid");
		BuildingInfo buildinginfo = buildingService.findById(buildingid);
		
		Map<String, Object> result = new HashMap<>();
		logger.debug("buildinginfo:{}",buildinginfo);
		
		result.put("code", 0);
		result.put("msg", "");
		result.put("data", buildinginfo);
		renderJson(result);	
	}
	
	public void getRoomById(){
		String roomid = getPara("bid");
		RoomInfo roominfo = roomService.findById(roomid);
		
		Map<String, Object> result = new HashMap<>();
		logger.debug("roominfo:{}",roominfo);
		
		result.put("code", 0);
		result.put("msg", "");
		result.put("data", roominfo);
		renderJson(result);	
	}
	
	/**
	 * 添加大楼
	 */
	public void save() {
		BuildingInfo buildingInfo = getBean(BuildingInfo.class,"");
		logger.info("buildingInfo:{}",buildingInfo);
		String buildingId = "building_" + System.currentTimeMillis() + StringUtil.getRandomStr(6);
		buildingInfo.setBuildingId(buildingId);
		boolean success = buildingService.save(buildingInfo);
		if(success) {
			renderText("添加成功！ <h1>:)</h1>");
		}else {
			renderText("添加失败！ <h1>:(</h1>");
		}
	}
	
	/**
	 * 添加房间
	 */
	public void saveRoom(){
		RoomInfo roominfo = getBean(RoomInfo.class,"");
		logger.info("roominfo: {}", roominfo);
		String roomId = "room_" + System.currentTimeMillis() + StringUtil.getRandomStr(6);
		roominfo.setRoomId(roomId);
		boolean success = roomService.saveRoom(roominfo);
		if(success) {
			renderText("添加成功！ <h1>:)</h1>");
		}else {
			renderText("添加失败！ <h1>:(</h1>");
		}
	}
}
