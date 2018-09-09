package cn.sunnypuma.buildingtax.application.admin.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;

import cn.sunnypuma.buildingtax.application.common.model.base.BuildingInfo;
import cn.sunnypuma.buildingtax.application.common.model.base.PermissionInfo;
import cn.sunnypuma.buildingtax.application.common.model.base.RoleInfo;
import cn.sunnypuma.buildingtax.util.StringUtil;

/**
*@author 凡
*@version 2018年8月19日 上午10:21:37
*/
public class PermissionService {
	private static final PermissionInfo dao = new PermissionInfo().dao();
	
	/**
	 * 查询所有权限管理
	 * @return
	 */
	public List<PermissionInfo> all(){
		return dao.find("select * from taxdb.taxdb_permission_info where status = ?",0);
	}
	
	/**
	 * 查询所有权限管理 带分页
	 * @param page
	 * @param limit
	 * @return
	 */
	public Page<PermissionInfo> all(int page,int limit){
		return dao.paginate(page, limit,"select *","from taxdb.taxdb_permission_info where status = ?",0 );
	}
	
	/**
	 * 删除权限
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id){
		return dao.deleteById(id);
	}
	
	/**
	 * 权限查询
	 * @param id
	 * @return
	 */
	public PermissionInfo findById(String id){
		return dao.findById(id);
	}
	
	/**
	 * 更新权限信息
	 * @param permissionInfo
	 * @return
	 */
	public boolean updatePermission(PermissionInfo permissionInfo) {
		String id = permissionInfo.getPermissionId();
		if (id != null) {
			return permissionInfo.update();
		}
		return false;
	}
	
	/**
	 * 添加
	 * @param permissionInfo
	 * @return
	 */
	public boolean save(PermissionInfo permissionInfo) {
			
		String id = permissionInfo.get("permission_id");
		if(id == null || id.isEmpty()) {
			id = "permission_"+System.currentTimeMillis()+StringUtil.getRandomStr(6);
		}
		return permissionInfo.save();
	}
}
