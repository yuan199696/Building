package cn.sunnypuma.buildingtax.application.enterprise.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;

import cn.sunnypuma.buildingtax.application.admin.interceptor.AdminInterceptor;
import cn.sunnypuma.buildingtax.application.common.model.base.BuildingInfo;
import cn.sunnypuma.buildingtax.application.common.model.base.EnterpriseInfo;
import cn.sunnypuma.buildingtax.application.common.model.base.EnterprisePosition;
import cn.sunnypuma.buildingtax.application.common.model.base.PositionInfo;
import cn.sunnypuma.buildingtax.application.common.model.base.RoleInfo;
import cn.sunnypuma.buildingtax.application.enterprise.service.EnterprisePositionService;
import cn.sunnypuma.buildingtax.application.enterprise.service.EnterpriseService;
import cn.sunnypuma.buildingtax.application.enterprise.service.PositionService;
import cn.sunnypuma.buildingtax.util.StringUtil;

/**
 * 
 * @author sunnypuma
 *
 */
@Before(AdminInterceptor.class)
public class IndexController extends Controller {
	public static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	public static EnterpriseService enterpriseservice = new EnterpriseService();
	public static PositionService positionservice = new PositionService();
	public static EnterprisePositionService enterpriseposservice= new EnterprisePositionService();

	public void all(){
		Map<String, Object> result = new HashMap<>();
		List<EnterpriseInfo> enterprises = enterpriseservice.all();
		result.put("code", 0);
		result.put("msg", "");
		result.put("data", enterprises);
		renderJson(result);
	}
	
	public void allWithPage(){
		int page = getParaToInt("page")==null?1:getParaToInt("page");
		int limit = getParaToInt("limit")==null?10:getParaToInt("limit");
		logger.info("page:{},limit:{}",page,limit);
		Map<String, Object> result = new HashMap<>();
		Page<EnterpriseInfo> enterprises = enterpriseservice.all(page, limit);
		List<EnterpriseInfo> enterpriseList = enterprises.getList();
		logger.debug("enterpriselist:{}",enterpriseList);
		
		int count = enterprises.getTotalRow();
		
		result.put("code", 0);
		result.put("msg", "");
		result.put("data", enterpriseList);
		renderJson(result);
	}
	
	/**
	 * 所有职位信息
	 */
	public void allPos(){
		Map<String, Object> result = new HashMap<>();
		List<PositionInfo> positions = positionservice.all();
		result.put("code", 0);
		result.put("msg", "");
		result.put("data", positions);
		renderJson(result);
	}
	
	public void save(){
		UploadFile upfile = getFile("enterpriseAvatar");
		String fp = "/upload/"+upfile.getFileName();
		String includedpos = getPara("includePosistion");
		logger.debug("includedpos: {}", includedpos);
		EnterpriseInfo enterpriseinfo = getBean(EnterpriseInfo.class,"");
		logger.info("enterpriseinfo:{}",enterpriseinfo);
		String enterpriseProperty = enterpriseinfo.getEnterpriseProperty();
		String enterpriseId = "enterprise_" + System.currentTimeMillis() + StringUtil.getRandomStr(6);
		
		enterpriseinfo.setEnterpriseId(enterpriseId);
		enterpriseinfo.setEnterpriseAvatar(fp);
		switch(enterpriseProperty) {
		case "e001":
			enterpriseinfo.setEnterpriseProperty("国企");
			break;
		case "e002":
			enterpriseinfo.setEnterpriseProperty("事业单位");
			break;
		case "e003":
			enterpriseinfo.setEnterpriseProperty("外企");
			break;
		case "e004":
			enterpriseinfo.setEnterpriseProperty("民营企业");
			break;
		case "e005":
			enterpriseinfo.setEnterpriseProperty("合资企业");
			break;
		default:
			enterpriseinfo.setEnterpriseProperty("其他");
			break;
		}
		boolean success = enterpriseservice.saveEnterprise(enterpriseinfo);
		if(success) {
			
			//保存企业-职位关系表
			
			renderText("添加成功！ <h1>:)</h1>");
		}else {
			renderText("添加失败！ <h1>:(</h1>");
		}
	}
	
	/**
	 * 
	 */
	public void savePos(){
		
		UploadFile upfile = getFile("enterpriseAvatar");
		PositionInfo positioninfo = getBean(PositionInfo.class,"");
		logger.info("positioninfo:{}:",positioninfo);
		String id = positioninfo.getPositionId();
		
		if ( id == null || id == "") {
			
			id = "layer_" + System.currentTimeMillis() + StringUtil.getRandomStr(6);
			positioninfo.setPositionId(id);
		}
		
		boolean success = positionservice.save(positioninfo);
		
		if(success) {
			
			renderText("添加成功！ <h1>:)</h1>");
		}else {
			renderText("添加失败！ <h1>:(</h1>");
		}
	}
	
	/**
	 * 
	 */
	public void allPosWithPage(){
		int page = getParaToInt("page")==null?1:getParaToInt("page");
		int limit = getParaToInt("limit")==null?10:getParaToInt("limit");
		logger.info("page:{},limit:{}",page,limit);
		Map<String, Object> result = new HashMap<>();
		Page<PositionInfo> positions = positionservice.all(page, limit);
		List<PositionInfo> positionList = positions.getList();
		logger.debug("enterpriselist:{}",positionList);
		
		int count = positions.getTotalRow();
		
		result.put("code", 0);
		result.put("msg", "");
		result.put("data", positionList);
		renderJson(result);
	}
	
	/**
	 * 
	 */
	public void getPosByEID(){
		String id = getPara("id");
		Map<String, Object> result = new HashMap<>();
		List<EnterprisePosition> enterpriseposs = enterpriseposservice.getByEID(id);
		logger.debug("enterpriseposs: {}", enterpriseposs);;
		
		result.put("code", 0);
		result.put("msg", "");
		result.put("data", enterpriseposs);
		renderJson(result);
	}
	
	/**
	 * 查询职位
	 */
	public void getPosById(){
		String positionId = getPara("bid");
		PositionInfo positionInfo = positionservice.findById(positionId);
		
		Map<String, Object> result = new HashMap<>();
		logger.debug("positionInfo:{}",positionInfo);
		
		result.put("code", 0);
		result.put("msg", "");
		result.put("data", positionInfo);
		renderJson(result);	
	}
	
	/**
	 * 查询企业
	 */
	public void getEnterpriseById(){
		String enterpriseId = getPara("bid");
		EnterpriseInfo enterpriseInfo = enterpriseservice.findById(enterpriseId);
		
		Map<String, Object> result = new HashMap<>();
		logger.debug("enterpriseInfo:{}",enterpriseInfo);
		
		result.put("code", 0);
		result.put("msg", "");
		result.put("data", enterpriseInfo);
		renderJson(result);	
	}
	
	/**
	 * 更新企业信息
	 */
	public void update() {
		String enterpriseId;
		EnterpriseInfo enterpriseInfo = getBean(EnterpriseInfo.class,"");
		logger.info("enterpriseInfo:{}",enterpriseInfo);
		enterpriseId = enterpriseInfo.getEnterpriseId();
		if (enterpriseId == "" || enterpriseId == null) {
			enterpriseId = "enterprise_" + System.currentTimeMillis() + StringUtil.getRandomStr(6);
		}
		enterpriseInfo.setEnterpriseId(enterpriseId);
		boolean success = enterpriseservice.update(enterpriseInfo);
		if(success) {
			renderText("修改成功！ <h1>:)</h1>");
		}else {
			renderText("修改失败！ <h1>:(</h1>");
		}
	}
	
	/**
	 * 添加企业
	 */
	public void saveEnterprise(){
		EnterpriseInfo enterpriseInfo = getBean(EnterpriseInfo.class,"");
		logger.info("enterpriseInfo:{}",enterpriseInfo);
		String enterpriseId = "enterprise_" + System.currentTimeMillis() + StringUtil.getRandomStr(6);
		enterpriseInfo.setEnterpriseId(enterpriseId);
		boolean success = enterpriseservice.saveEnterprise(enterpriseInfo);
		if(success) {
			renderText("添加成功！ <h1>:)</h1>");
		}else {
			renderText("添加失败！ <h1>:(</h1>");
		}
	}
	
	/**
	 * 添加职位
	 */
	public void savePosition(){
		PositionInfo positionInfo = getBean(PositionInfo.class,"");
		logger.info("positionInfo:{}",positionInfo);
		String positionId = "position_" + System.currentTimeMillis() + StringUtil.getRandomStr(6);
		positionInfo.setPositionId(positionId);
		boolean success = positionservice.savePosition(positionInfo);
		if(success) {
			renderText("添加成功！ <h1>:)</h1>");
		}else {
			renderText("添加失败！ <h1>:(</h1>");
		}
	}
	
	/**
	 * 更新职位
	 */
	public void updatePosition() {
		String positionId;
		PositionInfo positionInfo = getBean(PositionInfo.class,"");
		logger.info("positionInfo:{}",positionInfo);
		positionId = positionInfo.getPositionId();
		if (positionId == "" || positionId == null) {
			positionId = "position_" + System.currentTimeMillis() + StringUtil.getRandomStr(6);
		}
		positionInfo.setPositionId(positionId);
		boolean success = positionservice.updatePosition(positionInfo);
		if(success) {
			renderText("修改成功！ <h1>:)</h1>");
		}else {
			renderText("修改失败！ <h1>:(</h1>");
		}
	}
	
	public void deleteEnterp(){
		String enterpriseid = getPara("bid");
		logger.info("enterpriseid:{}",enterpriseid);
		Map<String, Object> map = new HashMap<>();
		boolean success = enterpriseservice.deleteById(enterpriseid);
		logger.info("deletePos result:{}",success);
		int res = -1;
		if (success) {
			res = 0;
		} 
		map.put("code", res);
		renderJson(map);
	}
	
	/**
	 * 删除职位
	 */
	public void deletePos(){
		String posid = getPara("bid");
		logger.info("posId:{}",posid);
		Map<String, Object> map = new HashMap<>();
		boolean success = positionservice.deleteById(posid);
		logger.info("deletePos result:{}",success);
		int res = -1;
		if (success) {
			res = 0;
		} 
		map.put("code", res);
		renderJson(map);
	}
}
