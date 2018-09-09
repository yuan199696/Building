package cn.sunnypuma.buildingtax.application.admin.controller;

import com.jfinal.core.Controller;


public class LoginController extends Controller {
	
	/**
	 * @param admin
	 */
	public void index() {
		
		removeSessionAttr(getCookie("current_admin"));
		redirect("admin/login");
	}
}
