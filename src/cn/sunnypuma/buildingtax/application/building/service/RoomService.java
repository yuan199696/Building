package cn.sunnypuma.buildingtax.application.building.service;

import com.jfinal.plugin.activerecord.Page;

import cn.sunnypuma.buildingtax.application.common.model.base.BuildingInfo;
import cn.sunnypuma.buildingtax.application.common.model.base.RoomInfo;
import cn.sunnypuma.buildingtax.util.StringUtil;

public class RoomService {
	private static final RoomInfo dao = new RoomInfo().dao();
	
	/**
	 * 添加房间
	 * @param roomInfo
	 * @return
	 */
	public boolean saveRoom(RoomInfo roomInfo) {			
		String id = roomInfo.get("room_id");
		if(id == null || id.isEmpty()) {
			id = "room_"+System.currentTimeMillis()+StringUtil.getRandomStr(6);
		}
		return roomInfo.save();
	}

	public Page<RoomInfo> all(int page, int limit) {
		return dao.paginate(page,limit,"select * ","from taxdb.taxdb_room_info where status = ?",0);
	}
	
	public RoomInfo findById(String id) {
		return dao.findById(id);
	}
	
	/**
	 * 更新房间信息
	 * @param roomInfo
	 * @return
	 */
	public boolean updateRoom(RoomInfo roomInfo) {
		String id = roomInfo.getRoomId();
		if (id != null) {
			return roomInfo.update();
		}
		return false;
	}
	
	public boolean deleteById(String id) {
		return dao.deleteById(id);
	}
}
