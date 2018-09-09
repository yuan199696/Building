package cn.sunnypuma.buildingtax.application.user.service;

import cn.sunnypuma.buildingtax.application.common.model.base.UserEnterprise;
import cn.sunnypuma.buildingtax.util.StringUtil;

public class UserEnterpriseService {
	public static final UserEnterprise dao = new UserEnterprise().dao();
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public UserEnterprise findById(String id) {
		return dao.findById(id);
	}
	
	/**
	 * 
	 * @param userenterprise
	 * @return
	 */
	public boolean save(UserEnterprise userenterprise) {
		String id = userenterprise.get("enterprise_id");
		if(id == null || id.isEmpty()) {
			id = "enterprise_"+System.currentTimeMillis()+StringUtil.getRandomStr(6);
			userenterprise.set("enterprise_id", id);
		}
		return userenterprise.save();
	}
	
	/**
	 * 
	 * @param userenterprise
	 * @return
	 */
	public boolean update(UserEnterprise userenterprise) {
		String id = userenterprise.get("enterprise_id");
		if(id != null) {
			return userenterprise.update();
		}
		return false;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id){
		return dao.deleteById(id);
	}
}
