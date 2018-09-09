package cn.sunnypuma.buildingtax.application.user.controller;

import java.util.Date;
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
import cn.sunnypuma.buildingtax.application.common.model.base.EnterpriseInfo;
import cn.sunnypuma.buildingtax.application.common.model.base.PositionInfo;
import cn.sunnypuma.buildingtax.application.common.model.base.RoomInfo;
import cn.sunnypuma.buildingtax.application.common.model.base.UserEnterprise;
import cn.sunnypuma.buildingtax.application.common.model.base.UserInfo;
import cn.sunnypuma.buildingtax.application.common.model.base.UserInfoExtend;
import cn.sunnypuma.buildingtax.application.enterprise.service.EnterpriseService;
import cn.sunnypuma.buildingtax.application.enterprise.service.PositionService;
import cn.sunnypuma.buildingtax.application.user.service.UserEnterpriseService;
import cn.sunnypuma.buildingtax.application.user.service.UserExtendService;
import cn.sunnypuma.buildingtax.application.user.service.UserService;
import cn.sunnypuma.buildingtax.util.DateUtils;
import cn.sunnypuma.buildingtax.util.MD5;
import cn.sunnypuma.buildingtax.util.StringUtil;

@Before(AdminInterceptor.class)
public class UserController extends Controller{
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static UserService userservice = new UserService();
	private static EnterpriseService enterpriseservice = new EnterpriseService();
	private static UserExtendService userextendservice = new UserExtendService();
	private static UserEnterpriseService ueservice = new UserEnterpriseService();
	private static PositionService positionservice = new PositionService(); 
	
	public void index() {
		render("../admin/user/index.html");
	}
	
	public void all() {
		Map<String, Object> result = new HashMap<>();
		List<UserInfo> users = userservice.all();
		result.put("code", 0);
		result.put("count", users.size());
		result.put("msg", "");
		result.put("data", users);
		renderJson(result);
	}
	
	public void allExtendInfoField(){
		
		Map<String, Object> result = new HashMap<>();
		List<UserInfoExtend> userinfoextends = userextendservice.all();
		result.put("code", 0);
		result.put("msg", "");
		result.put("count", userinfoextends.size());
		result.put("data", userinfoextends);
		renderJson(result);
	}
	
	/**
	 * 带分页的 获取所有数据的接口
	 */
	public void allWithPage() {
		int page = getParaToInt("page")==null?1:getParaToInt("page");
		int limit = getParaToInt("limit")==null?10:getParaToInt("limit");
		logger.info("page:{},limit:{}",page,limit);
		
		Map<String, Object> result = new HashMap<>();
		Page<UserInfo> users = userservice.all(page,limit);
		List<UserInfo> userlist = users.getList();
		logger.debug("userlist:{}",userlist);
		
		int count = users.getTotalRow();
		result.put("code", 0);
		result.put("count", count);
		result.put("msg", "");
		result.put("data", userlist);
		renderJson(result);		
	}
	
	public void queryWithParam(){
		
		int page = getParaToInt("page")==null?1:getParaToInt("page");
		int limit = getParaToInt("limit")==null?10:getParaToInt("limit");
		String params = getPara("params");
		String condition = getPara("sf");
		condition = condition.substring(4);
		Map<String, Object> result = new HashMap<>();
		Page<UserInfo> users = null;
		if (params == null || params.equals("") ) {
			
			users = userservice.all(page,limit);
		} else if(params.length() > 0){
			if(!condition.equals("telephone")){
				condition = "user_" + condition;
			}
			users = userservice.queryWithParam(page, limit, params,condition);
		}
		List<UserInfo> userList = users.getList();
		logger.debug("buildingList:{}",userList);
		
		int count = users.getTotalRow();
		result.put("code", 0);
		result.put("count", count);
		result.put("msg", "");
		result.put("data", userList);
		renderJson(result);		
	}
	
	public void findByName(){
		//String search = getPara();
		
	}
	
	/**
	 * 
	 */
	public void update(){
		
		UploadFile upfile = getFile("userAvatar");
		String fp;
		boolean success;
		UserInfo userinfo = getBean(UserInfo.class,"");

		if (upfile != null) {
			fp = "/upload/"+  upfile.getFileName();
			userinfo.setUserAvatar(fp);
		}
		
		logger.info("userinfo: {}",userinfo);
		String enterpriseid = getPara("userEnterprise");
		String userid = userinfo.getUserId();
		
		if (userid == null || userid == "") {
			
			renderText("无法保存！ <h1>:(</h1>");
		}
		String layerid = userinfo.getPosition();
		if (layerid != null && layerid != "") {
			PositionInfo positioninfo = positionservice.findById(layerid);
			if (positioninfo != null) {
				userinfo.setPosition(positioninfo.getPositionName());
			}
		}
		
		success = updateUserEnterprise(userid, enterpriseid);
		if (!success){
			renderText("保存失败！ <h1>:(</h1>");
		}
		
		userinfo.setUserId(userid);
		String create_time = DateUtils.getNowTime();
		String heslo = MD5.GetMD5Code(userinfo.getHeslo()+create_time);
		userinfo.setHeslo(heslo);
		
		success  = userservice.update(userinfo);
		if (success) {
			renderText("修改成功！ <h1>:)</h1>");
		}else {
			renderText("保存失败！ <h1>:(</h1>");
		}
	}
	
	public void save(){
		UploadFile upfile = getFile("userAvatar");
		String fp;
		boolean success;
		if (upfile != null) {
			fp = "/upload/"+  upfile.getFileName();
		} else {
			fp = "/upload/default.jpg";
		}
		UserInfo userinfo = getBean(UserInfo.class,"");
		userinfo.setUserAvatar(fp);
		logger.info("userinfo: {}",userinfo);
		String enterpriseid = getPara("userEnterprise");
		
		String userid = userinfo.getUserId();
		
		if (userid == null || userid == "") {
			
			userid = "user_"+ System.currentTimeMillis() + StringUtil.getRandomStr(6);
		}
		String layerid = userinfo.getPosition();
		if (layerid != null && layerid != "") {
			PositionInfo positioninfo = positionservice.findById(layerid);
			if (positioninfo != null) {
				userinfo.setPosition(positioninfo.getPositionName());
			}
		}
		
		success = saveUserEnterprise(userid, enterpriseid);
		if (!success){
			renderText("保存失败！ <h1>:(</h1>");
		}
		
		userinfo.setUserId(userid);
		String create_time = DateUtils.getNowTime();
		String heslo = MD5.GetMD5Code(userinfo.getHeslo()+create_time);
		userinfo.setHeslo(heslo);
		
		success  = userservice.save(userinfo);
		if (success) {
			renderText("添加成功！ <h1>:)</h1>");
		}else {
			renderText("添加失败！ <h1>:(</h1>");
		}
		
	}
	
	/**
	 * 添加员工
	 */
	public void saveUser(){
		UserInfo userInfo = getBean(UserInfo.class,"");
		logger.info("userInfo: {}", userInfo);
		String userId = "user_" + System.currentTimeMillis() + StringUtil.getRandomStr(6);
		userInfo.setUserId(userId);
		boolean success = userservice.saveUser(userInfo);
		if(success) {
			renderText("添加成功！ <h1>:)</h1>");
		}else {
			renderText("添加失败！ <h1>:(</h1>");
		}
	}
	
	/**
	 * 编辑员工信息
	 */
	public void updateUser() {
		String userId;
		UserInfo userInfo = getBean(UserInfo.class,"");
		logger.info("userInfo:{}",userInfo);
		userId = userInfo.getUserId();
		if (userId == "" || userId == null) {
			userId = "user_" + System.currentTimeMillis() + StringUtil.getRandomStr(6);
		}		
		userInfo.setUserId(userId);
		boolean success = userservice.updateUser(userInfo);
		if(success) {
			renderText("修改成功！ <h1>:)</h1>");
		}else {
			renderText("修改失败！ <h1>:(</h1>");
		}
	}
	
	/**
	 * 删除员工
	 */
	public void deleteUser(){
		String userId = getPara("bid");
		logger.info("userId:{}",userId);
		Map<String, Object> map = new HashMap<>();
		boolean success = userservice.deleteById(userId);
		logger.info("deleteUser result:{}",success);
		int res = -1;
		if (success) {
			res = 0;
		} 
		map.put("code", res);
		renderJson(map);
	}

	public void getStaffById(){
		String userid = getPara("bid");
		UserInfo userinfo = userservice.findById(userid);
		String ue = "";
		String e_id = "";
		EnterpriseInfo enterpriseinfo = null;
		
		Map<String, Object> result = new HashMap<>();
		logger.debug("userinfo: {}", userinfo);
		
		UserEnterprise userenterprise = ueservice.findById(userid);
		if (userenterprise != null) {
			e_id = userenterprise.getEnterpriseId();
		}
		if (e_id != null) {
			enterpriseinfo = enterpriseservice.findById(e_id);
		}
		if (enterpriseinfo != null) {
			ue = enterpriseinfo.getEnterpriseName();
		}
		
		Date birthday = userinfo.getBirthday();
		if (birthday != null) {
			userinfo.put("birthday", birthday.toString().split(" ")[0]);
		}
		Date hiredata = userinfo.getHiredate();
		if (hiredata != null) {
			userinfo.put("hiredata", hiredata.toString().split(" ")[0]);
		}
		
		userinfo.put("user_enterprise",ue);
		result.put("code", 0);
		result.put("msg", "");
		result.put("data", userinfo);
		renderJson(result);
	}
	
	/**
	 * 
	 */
	public void deleteStaff(){
		String staffid = getPara("bid");
		logger.info("deletStaffid:{}",staffid);
		Map<String, Object> map = new HashMap<>();
		boolean success = userservice.deleteById(staffid);
		logger.info("deleteStaff result:{}",success);
		int res = -1;
		if (success) {
			if (ueservice.deleteById(staffid)) {
				
				res = 0;
			}
		} 
		map.put("code", res);
		renderJson(map);
	}
	
	/**
	 * 
	 */
	public void addNewField(){
		String field = getPara("field");
		Map<String, Object> result = new HashMap<>();
		
		UserInfoExtend extendGet = userextendservice.getByFieldName(field);
		if (extendGet != null) {
			result.put("code", 2);
			result.put("msg", "字段已经存在");
			renderJson(result);
			return ;
		}
		
		Map<String, Object> resDat = new HashMap<>();
		UserInfoExtend userinfoextend = getBean(UserInfoExtend.class,"");
		String id = "extend_" + System.currentTimeMillis() + StringUtil.getRandomStr(6);
		userinfoextend.setId(id);
		userinfoextend.setFieldName(field);
		
		boolean success = userextendservice.saveNewField(userinfoextend);
		if (success) {
			resDat.put("field_name", field);
			resDat.put("id", id);
			result.put("code", 0);
			result.put("msg", "");
			result.put("data", resDat);
		}else {
			
			result.put("code", 1);
			result.put("msg", "saved error!");
		}
		renderJson(result);
	}
	
	public void deleteExtendInfoField(){
		
		String fid = getPara("fieldId");
		Map<String, Object> result = new HashMap<>();
		
		boolean success = userextendservice.deleteById(fid);
		if (success) {
			
			result.put("code", 0);
			result.put("msg", "删除成功");
		} else {
			
			result.put("code", 1);
			result.put("msg", "删除失败");
		}
		renderJson(result);
	}
	
	public boolean updateUserEnterprise(String uid, String eid) {
		boolean res = false;
		if (null == enterpriseservice.findById(eid)) {
			return res;
		}
		if (!uid.isEmpty() &&  !eid.isEmpty()) {
			
			UserEnterprise userenterprise = getBean(UserEnterprise.class,"");
			userenterprise.setEnterpriseId(eid);
			userenterprise.setUserId(uid);
			if (ueservice.update(userenterprise) ) {
				res = true;
			}
		}
		return res;
	}
	
	public boolean saveUserEnterprise(String uid, String eid){
		boolean res = false;
		if (null == enterpriseservice.findById(eid)) {
			return res;
		}
		if (!uid.isEmpty() &&  !eid.isEmpty()) {
			
			UserEnterprise userenterprise = getBean(UserEnterprise.class,"");
			userenterprise.setEnterpriseId(eid);
			userenterprise.setUserId(uid);
			if (ueservice.save(userenterprise)) {
				res = true;
			}
		}
		return res;
	}
}
