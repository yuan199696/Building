package cn.sunnypuma.buildingtax.application.building.service;
/**
 * 
 * @author pangPython
 *
 */

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

import cn.sunnypuma.buildingtax.application.common.model.base.BuildingInfo;
import cn.sunnypuma.buildingtax.util.StringUtil;


public class BuildingService { 
	private static final BuildingInfo dao = new BuildingInfo().dao();
	/**
	 * 不带分页
	 * @return
	 */
	public List<BuildingInfo> all() {
		return dao.find("select * from taxdb.taxdb_building_info where status = ?",0);
	}
	/**
	 * 带分页
	 * @param page
	 * @param limit
	 * @return
	 */
	public Page<BuildingInfo> all(int page,int limit) {
		return dao.paginate(page,limit,"select * ","from taxdb.taxdb_building_info where status = ?",0);
	}
	/**
	 * 带分页 带参数
	 * @param page
	 * @param limit
	 * @param params 
	 * @return
	 */
	public Page<BuildingInfo> queryWithParam(int page,int limit, String params, String con) {
		String sql = "from taxdb.taxdb_building_info where building_"+ con +" like '%" +params+ "%'";
		return dao.paginate(page,limit,"select * ",sql);
	}
	
	/**
	 * 大楼信息查询
	 * @param id
	 * @return
	 */
	public BuildingInfo findById(String id) {
		return dao.findById(id);
	}

	/**
	 * 删除大楼
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id) {
		return dao.deleteById(id);
	}
	
	/**
	 * 添加大楼
	 * @param buildingInfo
	 * @return
	 */
	public boolean save(BuildingInfo buildingInfo) {
			
		String id = buildingInfo.get("building_id");
		if(id == null || id.isEmpty()) {
			id = "building_"+System.currentTimeMillis()+StringUtil.getRandomStr(6);
		}
		return buildingInfo.save();
	}
	
	/**
	 * 更新大楼信息
	 * @param buildingInfo
	 * @return
	 */
	public boolean update(BuildingInfo buildingInfo) {
		String id = buildingInfo.getBuildingId();
		if (id != null) {
			return buildingInfo.update();
		}
		return false;
	}
}
