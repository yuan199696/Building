package cn.sunnypuma.buildingtax.application.building.service;

import java.util.List;

import cn.sunnypuma.buildingtax.application.common.model.base.BuildingInfoExtend;

public class BuildingExtendService {
	private static final BuildingInfoExtend dao = new BuildingInfoExtend().dao();
	
	public boolean save(BuildingInfoExtend buildingInfoExtend) {
		
		return buildingInfoExtend.save();
	}

	public BuildingInfoExtend getByFieldName(String fname){
		return dao.findFirst("select * from taxdb.taxdb_building_info_extend where field_name = ? limit 1", fname);
	}
	
	public List<BuildingInfoExtend> all(){
		return dao.find("select * from taxdb.taxdb_building_info_extend where status = ?",0);
	}
	
	public boolean deleteById(String id) {
		return dao.deleteById(id);
	}
	
}
