package cn.sunnypuma.buildingtax.application.user.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

import cn.sunnypuma.buildingtax.application.common.model.base.UserInfo;

public class AdminLoginInterceptor implements Interceptor {
	private static final Logger logger = LoggerFactory.getLogger(AdminLoginInterceptor.class);
	
	@Override
	public void intercept(Invocation inv) {
//		Controller controller = inv.getController();
//		
//		UserInfo userInfo = controller.getSessionAttr(controller.getCookie("current_admin"));
//		logger.info("current_admin:{}",controller.getCookie("current_admin"));
//		logger.info("userInfo:{}",userInfo);
//		
//		if (userInfo == null ) {
//			
//			controller.redirect("/admin/login");
//			
//		}else{
//			
//			inv.invoke();
//			
//		}
		
	}

}
