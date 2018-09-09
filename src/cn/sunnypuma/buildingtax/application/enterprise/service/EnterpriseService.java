package cn.sunnypuma.buildingtax.application.enterprise.service;
/**
 * 
 * @author pangPython
 *
 */

import java.util.List;

import com.jfinal.plugin.activerecord.Page;

import cn.sunnypuma.buildingtax.application.common.model.base.BuildingInfo;
import cn.sunnypuma.buildingtax.application.common.model.base.EnterpriseInfo;
import cn.sunnypuma.buildingtax.util.StringUtil;


public class EnterpriseService {
	private static final EnterpriseInfo dao = new EnterpriseInfo().dao();
	/**
	 * 不带分页
	 * @return
	 */
	public List<EnterpriseInfo> all() {
		return dao.find("select * from taxdb.taxdb_enterprise_info where status = ?",0);
	}
	/**
	 * 带分页
	 * @param page
	 * @param limit
	 * @return
	 */
	public Page<EnterpriseInfo> all(int page,int limit) {
		return dao.paginate(page,limit,"select * ","from taxdb.taxdb_enterprise_info where status = ?",0);
	}
	
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public EnterpriseInfo findById(String id) {
		return dao.findById(id);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id) {
		return dao.deleteById(id);
	}
	
	/**
	 * 添加企业信息
	 * @param enterpriseInfo
	 * @return
	 */
	public boolean saveEnterprise(EnterpriseInfo enterpriseinfo) {
		//这里可对 对象进行处理
		//例如主键生成
			String id = enterpriseinfo.get("enterprise_id");
			if(id == null || id.isEmpty()) {
				id = "enterprise_"+System.currentTimeMillis()+StringUtil.getRandomStr(6);
			}
			return enterpriseinfo.save();
	}

	
	/**
	 * 更新企业信息
	 * @param enterpriseInfo
	 * @return
	 */
	public boolean update(EnterpriseInfo enterpriseInfo) {
		String id = enterpriseInfo.getEnterpriseId();
		if (id != null) {
			return enterpriseInfo.update();
		}
		return false;
	}
}
