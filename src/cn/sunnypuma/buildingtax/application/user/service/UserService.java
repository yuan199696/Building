package cn.sunnypuma.buildingtax.application.user.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;

import cn.sunnypuma.buildingtax.application.common.model.base.RoomInfo;
import cn.sunnypuma.buildingtax.application.common.model.base.UserInfo;
import cn.sunnypuma.buildingtax.util.StringUtil;

public class UserService {
	private static final UserInfo dao = new UserInfo().dao();
	
	public UserInfo getByNameAndHeslo(String userName,String heslo) {
		return dao.findFirst("select * from taxdb.taxdb_user_info where user_name = ? and heslo = ? limit 1", userName,heslo);
	}
	
	public List<UserInfo> all(){
		return dao.find("select * from taxdb.taxdb_user_info where status = ?",0);
	}
	
	/**
	 * 带分页 带参数
	 * @param page
	 * @param limit
	 * @param params 
	 * @return
	 */
	public Page<UserInfo> queryWithParam(int page,int limit, String params, String con) {
		String sql = "from taxdb.taxdb_user_info where "+ con +" like '%" +params+ "%'";
		return dao.paginate(page,limit,"select * ",sql);
	}
	
	public UserInfo findById(String id){
		return dao.findById(id);
	}
	
	/**
	 * 带分页
	 * @param page
	 * @param limit
	 * @return
	 */
	public Page<UserInfo> all(int page,int limit) {
		return dao.paginate(page,limit,"select * ","from taxdb.taxdb_user_info where status = ?",0);
	}
	
	/**
	 * @param userinfo
	 * @return
	 */
	public boolean save(UserInfo userinfo) {
		String id = userinfo.getUserId();
		if (id == null || id.isEmpty()) {
			id = "user_"+System.currentTimeMillis()+StringUtil.getRandomStr(6);
		}
		
		return userinfo.save();
	}
	
	/**
	 * @param userinfo
	 * @return
	 */
	public boolean update(UserInfo userinfo) {
		String id = userinfo.getUserId();
		if (id != null) {
			return userinfo.update();
		}
		return false;
	}
	
	/**
	 * 删除员工
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id){
		return dao.deleteById(id);
	}
	
	/**
	 * 添加员工
	 * @param roomInfo
	 * @return
	 */
	public boolean saveUser(UserInfo userInfo) {			
		String id = userInfo.get("user_id");
		if(id == null || id.isEmpty()) {
			id = "user_"+System.currentTimeMillis()+StringUtil.getRandomStr(6);
		}
		return userInfo.save();
	}
	
	/**
	 * 编辑员工信息
	 */
	public boolean updateUser(UserInfo userInfo) {
		String id = userInfo.getUserId();
		if (id != null) {
			return userInfo.update();
		}
		return false;
	}
}
