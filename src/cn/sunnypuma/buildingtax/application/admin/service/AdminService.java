package cn.sunnypuma.buildingtax.application.admin.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;

import cn.sunnypuma.buildingtax.application.common.model.base.Admin;
import cn.sunnypuma.buildingtax.application.common.model.base.BuildingInfo;
import cn.sunnypuma.buildingtax.application.common.model.base.PositionInfo;
import cn.sunnypuma.buildingtax.application.common.model.base.RoleInfo;
import cn.sunnypuma.buildingtax.util.StringUtil;

/**
 * 
 * 所有 sql 与业务逻辑写在 Service 中，不要放在 Model 中，更不
 * 要放在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 * @author pangPython
 *
 */
/**
 * @author Administrator
 *
 */
public class AdminService {
	private static final Admin dao = new Admin().dao();
	/**
	 * 根据用户名和密码获取用户
	 * @param name
	 * @param password
	 * @return
	 */
	public Admin getByNameAndHeslo(String adminName,String heslo) {
		return dao.findFirst("select * from taxdb.taxdb_admin where admin_name = ? and heslo = ? limit 1", adminName,heslo);
	}
	
	
	/**
	 * 添加超级管理员
	 * @param admin
	 * @return
	 */
	public boolean save(Admin admin){
		String id = admin.get("admin_id");
		if(id == null || id.isEmpty()) {
			id = "admin_"+System.currentTimeMillis()+StringUtil.getRandomStr(6);
			admin.set("admin_id", id);
		}
		return admin.save();
	}
	
	/**
	 * 更新管理员信息
	 * @param buildingInfo
	 * @return
	 */
	public boolean updateAdmin(Admin adminInfo) {
		String id = adminInfo.getAdminId();
		if (id != null) {
			return adminInfo.update();
		}
		return false;
	}
	/**
	 * 
	 */
	public List<Admin> all() {
		return dao.find("select * from taxdb.taxdb_admin where status = ?",0);
	}
	
	/**
	 * 带分页
	 * @param page
	 * @param limit
	 */
	public Page<Admin> all(int page,int limit){
		return dao.paginate(page,limit,"select * ","from taxdb.taxdb_admin where status = ?",0);
	}
	
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public Admin findById(String id){
		return dao.findById(id);
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
	 * 添加管理员
	 * @param adminInfo
	 * @return
	 */
	public boolean saveAdmin(Admin adminInfo) {		
		String id = adminInfo.get("admin_id");
		if(id == null || id.isEmpty()) {
			id = "admin_"+System.currentTimeMillis()+StringUtil.getRandomStr(6);
		}
		return adminInfo.save();
	}
	
	

}
