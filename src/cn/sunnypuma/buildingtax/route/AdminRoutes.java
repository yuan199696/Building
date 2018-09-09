package cn.sunnypuma.buildingtax.route;

import com.jfinal.config.Routes;

import cn.sunnypuma.buildingtax.application.admin.controller.AdminController;
import cn.sunnypuma.buildingtax.application.admin.controller.LoginController;
import cn.sunnypuma.buildingtax.application.building.interceptor.BuildingInterceptor;
import cn.sunnypuma.buildingtax.application.user.IndexController;
import cn.sunnypuma.buildingtax.application.user.controller.UserController;
import cn.sunnypuma.buildingtax.controller.CommonController;
/**
 * 后端 路由
 * @author pangPython
 *
 */
public class AdminRoutes extends Routes {

	@Override
	public void config() {
		addInterceptor(new BuildingInterceptor());
		add("/user", UserController.class);
		add("/admin", AdminController.class);	
		add("/logout", LoginController.class);
		add("/common", CommonController.class);
		//add("/staff", cn.sunnypuma.buildingtax.application.user.controller.UserController.class);
		add("/building", cn.sunnypuma.buildingtax.application.building.controller.IndexController.class);
		add("/enterprise", cn.sunnypuma.buildingtax.application.enterprise.controller.IndexController.class);
	}


}
