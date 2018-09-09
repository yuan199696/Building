package cn.sunnypuma.buildingtax.application.user.service;

import java.util.List;

import cn.sunnypuma.buildingtax.application.common.model.base.UserInfoExtend;
import cn.sunnypuma.buildingtax.util.StringUtil;

public class UserExtendService {
	public static final UserInfoExtend dao = new UserInfoExtend().dao();
	
	public boolean saveNewField(UserInfoExtend userinfoextend){
		String id = userinfoextend.getId();
		if (id == null || id.isEmpty()) {
			id = "extend_"+System.currentTimeMillis()+StringUtil.getRandomStr(6);
		}
		
		return userinfoextend.save();
	}
	
	public UserInfoExtend getByFieldName(String fname){
		return dao.findFirst("select * from taxdb.taxdb_user_info_extend where field_name = ? limit 1", fname);
	}
	
	public List<UserInfoExtend> all(){
		return dao.find("select * from taxdb.taxdb_user_info_extend where status = ?",0);
	}
	
	public boolean deleteById(String id) {
		return dao.deleteById(id);
	}
}
