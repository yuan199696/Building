package cn.sunnypuma.buildingtax.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;

import cn.sunnypuma.buildingtax.application.common.model.base._MappingKit;
import cn.sunnypuma.buildingtax.handler.ResourceHandler;
import cn.sunnypuma.buildingtax.route.AdminRoutes;
import cn.sunnypuma.buildingtax.route.FrontRoutes;


public class MyConfig extends JFinalConfig {

	public void configConstant(Constants me) {
		// 	ֵ
		PropKit.use("myconfig.txt");
		me.setDevMode(PropKit.getBoolean("devMode", false));
		// set default error page
		me.setError404View("error/404.html");
		me.setError500View("error/500.html");
		me.setBaseUploadPath("upload/");
		System.out.print("webroot >>>>" + PathKit.getWebRootPath());
	}

	/**
	 * 
	 */
	public void configRoute(Routes me) {
		me.add(new FrontRoutes());
		me.add(new AdminRoutes());
	}

	public void configEngine(Engine me) {
//		me.addSharedFunction("/common/_layout.html");
//		me.addSharedFunction("/common/_paginate.html");
	}

	public static DruidPlugin createDruidPlugin() {
		return new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
	}
	
	public void configPlugin(Plugins me) {
		
		DruidPlugin druidPlugin = createDruidPlugin();
		me.add(druidPlugin);
		
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		_MappingKit.mapping(arp);
		me.add(arp);

	}

	public void configInterceptor(Interceptors me) {
	}

	public void configHandler(Handlers me) {
		
		me.add(new ResourceHandler());
	}

}
