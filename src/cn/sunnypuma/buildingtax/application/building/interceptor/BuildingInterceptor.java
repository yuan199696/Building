package cn.sunnypuma.buildingtax.application.building.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
/**
 * 拦截器
 * @author pangPython
 *
 */
public class BuildingInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		System.out.print("before");
		inv.invoke();
		System.out.print("after");
		
	}

}
