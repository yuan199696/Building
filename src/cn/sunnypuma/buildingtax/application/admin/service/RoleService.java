package cn.sunnypuma.buildingtax.application.admin.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;

import cn.sunnypuma.buildingtax.application.common.model.base.BuildingInfo;
import cn.sunnypuma.buildingtax.application.common.model.base.PermissionInfo;
import cn.sunnypuma.buildingtax.application.common.model.base.PositionInfo;
import cn.sunnypuma.buildingtax.application.common.model.base.RoleInfo;
import cn.sunnypuma.buildingtax.util.StringUtil;

/**
 * @author 凡
 * @version 2018年8月18日 下午9:50:08
 */
public class RoleService {
	private static final RoleInfo dao = new RoleInfo().dao();
	
	/**
	 * 查询所有角色
	 * @return
	 * 
	 */
	public List<RoleInfo> all(){
		
		return dao.find("select * from taxdb.taxdb_role_info where status = ?",0);
	}
	
	/**
	 * 查询所有角色，带分页
	 * 
	 * @param page
	 * @param limit
	 */
	public Page<RoleInfo> all(int page,int limit){
		return dao.paginate(page,limit,"select *","from taxdb.taxdb_role_info where status = ?",0 );
	}
	/**
	 * 角色查询
	 * @param id
	 * @return
	 */
	public RoleInfo findById(String id){
		return dao.findById(id);
	}
	
	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id){
		return dao.deleteById(id);
	}
	
	/**
	 * 添加角色
	 * @param roleInfo
	 * @return
	 */
	public boolean saveRole(RoleInfo roleInfo) {
			
		String id = roleInfo.get("role_id");
		if(id == null || id.isEmpty()) {
			id = "role_"+System.currentTimeMillis()+StringUtil.getRandomStr(6);
		}
		return roleInfo.save();
	}
	
	/**
	 * 更新角色信息
	 * @param roleInfo
	 * @return
	 */
	public boolean updateRole(RoleInfo roleInfo) {
		String id = roleInfo.getRoleId();
		if (id != null) {
			return roleInfo.update();
		}
		return false;
	}
	
}
