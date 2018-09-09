package cn.sunnypuma.buildingtax.application.admin.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

import cn.sunnypuma.buildingtax.application.common.model.base.Admin;

public class AdminInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		
		Controller controller = inv.getController();
		String methodname = inv.getMethodName();
		
		Admin admin = controller.getSessionAttr(controller.getCookie("current_admin"));
		if (methodname.equals("login") || methodname.equals("dologin") || admin != null) {
			
			inv.invoke();
		}else{

			controller.redirect("/admin/login");
		}

	}

}
