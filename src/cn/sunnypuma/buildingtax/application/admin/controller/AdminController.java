package cn.sunnypuma.buildingtax.application.admin.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
/**
 * @author pangPython
 *
 */
import com.jfinal.core.Controller;
import com.jfinal.ext.kit.SessionIdKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;

import cn.sunnypuma.buildingtax.application.admin.interceptor.AdminInterceptor;
import cn.sunnypuma.buildingtax.application.admin.service.AdminService;
import cn.sunnypuma.buildingtax.application.admin.service.PermissionService;
import cn.sunnypuma.buildingtax.application.admin.service.RoleService;
import cn.sunnypuma.buildingtax.application.common.model.base.Admin;
import cn.sunnypuma.buildingtax.application.common.model.base.BuildingInfo;
import cn.sunnypuma.buildingtax.application.common.model.base.EnterpriseInfo;
import cn.sunnypuma.buildingtax.application.common.model.base.PermissionInfo;
import cn.sunnypuma.buildingtax.application.common.model.base.PositionInfo;
import cn.sunnypuma.buildingtax.application.common.model.base.RoleInfo;
import cn.sunnypuma.buildingtax.util.StringUtil;


@Before(AdminInterceptor.class)
public class AdminController extends Controller{
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	private static AdminService adminService = new AdminService();
	private static RoleService roleService = new RoleService();
	private static PermissionService perService = new PermissionService();


	private static final long MAXFILESIZE = 1024*1024*3;
	
	public void index() {
		render("index.html");
	}
	
//	/**
//	 * 管理员管理页面逻辑
//	 */
//	@Clear
//	public void admin() {
//		render("./admin/index.html");
//	}
//	
//	/**
//	 * 用户管理页面逻辑
//	 */
//	@Clear
//	public void user() {
//		render("../admin/user/index.html");
//	}
	
	@Clear
	public void addAdmin() {
//		Admin admin = new Admin();
//		admin.setAdminName("admin");
//		admin.setAdminPassword("buildingtax_admin123456");
//		admin.setAdminTel("13512344321");
//		admin.setAdminEmail("admin@buildingtax.com");
//		admin.save();
//		renderText("ok");
	}
	
	@Clear
	public void add(){
		Admin admin = getBean(Admin.class);
		adminService.save(admin);
		admin.save();
	}
	
	/**
	 * all
	 */
	@Clear
	public void all(){
		Map<String, Object> result = new HashMap<>();
		List<Admin> admin = adminService.all();
		result.put("code", 0);
		result.put("count", admin.size());
		result.put("msg", "");
		result.put("data", admin);
		renderJson(result);
	}
	
	/**
	 * 获取所有角色
	 */
	public void allRole() {
		Map<String, Object> result = new HashMap<>();
		List<RoleInfo> roles = roleService.all();
		result.put("code", 0);
		result.put("count", roles.size());
		result.put("msg", "");
		result.put("data", roles);
		renderJson(result);
	}
	
	/**
	 * 添加角色
	 */
	public void saveRole() {
		RoleInfo roleInfo = getBean(RoleInfo.class,"");
		logger.info("roleInfo:{}",roleInfo);
		String roleId = "role_" + System.currentTimeMillis() + StringUtil.getRandomStr(6);
		roleInfo.setRoleId(roleId);
		boolean success = roleService.saveRole(roleInfo);
		if(success) {
			renderText("添加成功！ <h1>:)</h1>");
		}else {
			renderText("添加失败！ <h1>:(</h1>");
		}
	}
	

	/**
	 * 添加管理员
	 */
	public void saveAdmin() {
		Admin adminInfo = getBean(Admin.class,"");
		logger.info("adminInfo:{}",adminInfo);
		String adminId = "role_" + System.currentTimeMillis() + StringUtil.getRandomStr(6);
		adminInfo.setAdminId(adminId);
		boolean success = adminService.saveAdmin(adminInfo);
		if(success) {
			renderText("添加成功！ <h1>:)</h1>");
		}else {
			renderText("添加失败！ <h1>:(</h1>");
		}
	}
	
	/**
	 * 登陆
	 */
	@Clear
	public void login() {
		// 如果请求参数cookie中获取当前登录用户
		String cadmin = getCookie("current_admin");
		Admin admin = getSessionAttr(cadmin);
		
		if (admin != null) {
			logger.debug("admin in session:{}",admin);
			redirect("/admin/index");
		}
		
		render("login.html");
	}
	
	@Clear 
	public void uploadImage(){
		
		UploadFile upfile = getFile("file");
		logger.debug("upfile:{}", upfile);
		Map<String, Object> result = new HashMap<>();
		Map<String, Object> data = new HashMap<>();
		String fp = "/upload/"+ upfile.getFileName();
		logger.debug("fp:{}", fp);
		data.put("src", fp);
		data.put("title", upfile.getOriginalFileName());
		result.put("code", 0);
		result.put("msg","successfully");
		
		result.put("data", data);
		renderJson(result);
	}
//	public String uploadImage(File file) throws FileNotFoundException{
//		
//		if (file == null) {
//			return "图片文件为空";
//		}
//		if (file.length() > MAXFILESIZE) {
//			return "文件过大";
//		}
//		
//		InputStream is = null;
//		OutputStream op = null;
//		try {
//			
//			String ext;
//			is = new FileInputStream(file);
//			
//		} catch (FileNotFoundException e) {
//			
//		}
//		
//	}
	/**
	 * 带分页的 获取所有数据的接口
	 */
	public void allWithPage() {
		int page = getParaToInt("page")==null?1:getParaToInt("page");
		int limit = getParaToInt("limit")==null?10:getParaToInt("limit");
		logger.info("page:{},limit:{}",page,limit);
		
		Map<String, Object> result = new HashMap<>();
		Page<Admin> admin = adminService.all(page,limit);
		List<Admin> adminlist = admin.getList();
		logger.debug("adminlist:{}",adminlist);
		
		int count = admin.getTotalRow();
		result.put("code", 0);
		result.put("count", count);
		result.put("msg", "");
		result.put("data", adminlist);
		renderJson(result);		
	}
	
	/**
	 * 管理员查询
	 */
	public void getAdminById(){
		String adminId = getPara("bid");
		Admin adminInfo = adminService.findById(adminId);
		
		Map<String, Object> result = new HashMap<>();
		logger.debug("adminInfo:{}",adminInfo);
		
		result.put("code", 0);
		result.put("msg", "");
		result.put("data", adminInfo);
		renderJson(result);	
	}
	
	/**
	 * 角色查询
	 */
	public void getRoleById(){
		String roleId = getPara("bid");
		RoleInfo roleInfo = roleService.findById(roleId);
		
		Map<String, Object> result = new HashMap<>();
		logger.debug("roleInfo:{}",roleInfo);
		
		result.put("code", 0);
		result.put("msg", "");
		result.put("data", roleInfo);
		renderJson(result);	
	}
	
	/**
	 * 权限查询
	 */
	public void getPermissionById(){
		String permissionId = getPara("bid");
		PermissionInfo permissionInfo = perService.findById(permissionId);
		
		Map<String, Object> result = new HashMap<>();
		logger.debug("permissionInfo:{}",permissionInfo);
		
		result.put("code", 0);
		result.put("msg", "");
		result.put("data", permissionInfo);
		renderJson(result);	
	}
	
	
	/**
	 * 更新管理员信息
	 */
	public void updateAdmin() {
		String adminId;
		Admin adminInfo = getBean(Admin.class,"");
		logger.info("adminInfo:{}",adminInfo);
		adminId = adminInfo.getAdminId();
		if (adminId == "" || adminId == null) {
			adminId = "admin_" + System.currentTimeMillis() + StringUtil.getRandomStr(6);
		}
		adminInfo.setAdminId(adminId);
		boolean success = adminService.updateAdmin(adminInfo);
		if(success) {
			renderText("修改成功！ <h1>:)</h1>");
		}else {
			renderText("修改失败！ <h1>:(</h1>");
		}
	}
	
	/**
	 * 更新权限信息
	 */
	public void updatePermission() {
		String permissionId;
		PermissionInfo permissionInfo = getBean(PermissionInfo.class,"");
		logger.info("buildingInfo:{}",permissionInfo);
		permissionId = permissionInfo.getPermissionId();
		if (permissionId == "" || permissionId == null) {
			permissionId = "building_" + System.currentTimeMillis() + StringUtil.getRandomStr(6);
		}
		
		permissionInfo.setPermissionId(permissionId);
		boolean success = perService.updatePermission(permissionInfo);
		if(success) {
			renderText("修改成功！ <h1>:)</h1>");
		}else {
			renderText("修改失败！ <h1>:(</h1>");
		}
	}
	
	/**
	 * 更新角色信息
	 */
	public void updateRole() {
		String roleId;
		RoleInfo roleInfo = getBean(RoleInfo.class,"");
		logger.info("roleInfo:{}",roleInfo);
		roleId = roleInfo.getRoleId();
		if (roleId == "" || roleId == null) {
			roleId = "role_" + System.currentTimeMillis() + StringUtil.getRandomStr(6);
		}
		
		roleInfo.setRoleId(roleId);
		boolean success = roleService.updateRole(roleInfo);
		if(success) {
			renderText("修改成功！ <h1>:)</h1>");
		}else {
			renderText("修改失败！ <h1>:(</h1>");
		}
	}
	
	
	
	/**
	 * 添加权限
	 */
	public void save() {
		PermissionInfo permissionInfo = getBean(PermissionInfo.class,"");
		logger.info("permissionInfo:{}",permissionInfo);
		String PermissionId = "permission_" + System.currentTimeMillis() + StringUtil.getRandomStr(6);
		permissionInfo.setPermissionId(PermissionId);
		boolean success = perService.save(permissionInfo);
		if(success) {
			renderText("添加成功！ <h1>:)</h1>");
		}else {
			renderText("添加失败！ <h1>:(</h1>");
		}
	}
	
	
	/**
	 * 角色管理
	 * 带分页的 获取所有数据的接口
	 */
	@Clear
	public void allRoleWithPage() {
		System.out.println("1111222333");
		int page = getParaToInt("page")==null?1:getParaToInt("page");
		int limit = getParaToInt("limit")==null?10:getParaToInt("limit");
		logger.info("page:{},limit:{}",page,limit);
		
		Map<String, Object> result = new HashMap<>();
		Page<RoleInfo> roles = roleService.all(page,limit);
		List<RoleInfo> rolelist = roles.getList();
		logger.debug("rolelist:{}",rolelist);
		
		int count = roles.getTotalRow();
		result.put("code", 0);
		result.put("count", count);
		result.put("msg", "");
		result.put("data", rolelist);
		renderJson(result);		
	}
	
	/**
	 * 权限管理
	 * 带分页的 获取所有数据的接口
	 */
	@Clear
	public void allPermissionWithPage() {
		int page = getParaToInt("page")==null?1:getParaToInt("page");
		int limit = getParaToInt("limit")==null?10:getParaToInt("limit");
		logger.info("page:{},limit:{}",page,limit);
		
		Map<String, Object> result = new HashMap<>();
		Page<PermissionInfo> permission = perService.all(page,limit);
		List<PermissionInfo> perlist = permission.getList();
		logger.debug("perlist:{}",perlist);
		
		int count = permission.getTotalRow();
		result.put("code", 0);
		result.put("count", count);
		result.put("msg", "");
		result.put("data", perlist);
		renderJson(result);		
	}
	
	
	public void dologin() {
		Admin admin = getModel(Admin.class);
		String adminName = getPara("admin.admin_name");
		String heslo = getPara("admin.heslo");
		admin.setAdminName(adminName);
		admin.setHeslo(heslo);
		
		logger.debug("admin:{}",admin);
		
		Admin adminGet = adminService.getByNameAndHeslo(adminName, heslo);
		logger.debug("admin:{}",adminGet);
		
		if(adminGet != null) {
			
			// 生成唯一标识
			String sessionId = SessionIdKit.me().generate(getRequest());
			// 设置服务器端session
			setSessionAttr(sessionId, adminGet);
			// 设置用户端cookie
			setCookie("current_admin", sessionId, 5000);
			
			this.redirect("/admin/index");
			
		}else {
			//setAttr("ErrMsg","用户名或密码错误");
			//render("login.html");
			this.redirect("/error/error.html");
			
		}
		
	}
	
	/**
	 * 删除管理员
	 */
	public void deleteAdmin(){
		String adminId = getPara("bid");
		logger.info("adminId:{}",adminId);
		Map<String, Object> map = new HashMap<>();
		boolean success = adminService.deleteById(adminId);
		logger.info("deleteAdmin result:{}",success);
		int res = -1;
		if (success) {
			res = 0;
		} 
		map.put("code", res);
		renderJson(map);
	}
	
	/**
	 * 删除角色
	 */
	public void deleteRole(){
		String roleId = getPara("bid");
		logger.info("roleId:{}",roleId);
		Map<String, Object> map = new HashMap<>();
		boolean success = roleService.deleteById(roleId);
		logger.info("deleteRole result:{}",success);
		int res = -1;
		if (success) {
			res = 0;
		} 
		map.put("code", res);
		renderJson(map);
	}
	
	/**
	 * 删除权限
	 */
	public void deletePermission(){
		String permissionId = getPara("bid");
		logger.info("permissionId:{}",permissionId);
		Map<String, Object> map = new HashMap<>();
		boolean success = perService.deleteById(permissionId);
		logger.info("deletePermission result:{}",success);
		int res = -1;
		if (success) {
			res = 0;
		} 
		map.put("code", res);
		renderJson(map);
	}

}