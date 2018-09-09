package cn.sunnypuma.buildingtax.application.enterprise.service;

import java.util.List;

import cn.sunnypuma.buildingtax.application.common.model.base.EnterprisePosition;

public class EnterprisePositionService {
	public static final EnterprisePosition dao = new EnterprisePosition().dao();
	
	public List<EnterprisePosition> getByEID(String id){
		return dao.find("select * from taxdb.taxdb_enterprise_position where enterprise_id= ? and status = ?",id,0);
	}
	
}
