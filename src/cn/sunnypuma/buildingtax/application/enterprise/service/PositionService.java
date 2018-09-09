package cn.sunnypuma.buildingtax.application.enterprise.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;

import cn.sunnypuma.buildingtax.application.common.model.base.EnterpriseInfo;
import cn.sunnypuma.buildingtax.application.common.model.base.PositionInfo;
import cn.sunnypuma.buildingtax.util.StringUtil;

public class PositionService {
	private static final PositionInfo dao = new PositionInfo().dao();
	
	//public final List<String> positions = ["科长","科室主任","副科长"];
	
	/**
	 * 保存
	 * @param enterpriseInfo
	 * @return
	 */
	public boolean save(PositionInfo positioninfo) {
		//这里可对 对象进行处理
		//例如主键生成
			String id = positioninfo.get("position_id");
			if(id == null || id.isEmpty()) {
				id = "position_"+System.currentTimeMillis()+StringUtil.getRandomStr(6);
			}
			return positioninfo.save();
	}
	/**
	 * 不带分页
	 * @return
	 */
	public List<PositionInfo> all() {
		return dao.find("select * from taxdb.taxdb_position_info where status = ?",0);
	}
	
	/**
	 * 带分页
	 * @return
	 */
	public Page<PositionInfo> all(int page, int limit) {
		return dao.paginate(page,limit,"select * ","from taxdb.taxdb_position_info where status = ?",0);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id){
		return dao.deleteById(id);
	}
	
	/**
	 * id查找
	 * @param id
	 * @return
	 */
	public PositionInfo findById(String id){
		return dao.findById(id);
	}
	
	/**
	 * 更新职位信息
	 * @param positionInfo
	 * @return
	 */
	public boolean updatePosition(PositionInfo positionInfo) {
		String id = positionInfo.getPositionId();
		if (id != null) {
			return positionInfo.update();
		}
		return false;
	}
	
	/**
	 * 添加职位
	 * @param positionInfo
	 * @return
	 */
	public boolean savePosition(PositionInfo positionInfo) {
			String id = positionInfo.get("position_id");
			if(id == null || id.isEmpty()) {
				id = "position_"+System.currentTimeMillis()+StringUtil.getRandomStr(6);
			}
			return positionInfo.save();
	}
}
