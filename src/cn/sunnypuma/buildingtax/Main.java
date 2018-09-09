package cn.sunnypuma.buildingtax;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.core.JFinal;

public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	private static final int PORT = 8088;
	public static void main(String[] args) {
		// eclipse 启动方式
		
		//mars 版本
//		JFinal.start("WebRoot", PORT, "/", 5);
		
		//Eclipse Java EE IDE for Web Developers.
//		Version: Oxygen.3 Release (4.7.3)
//		Build id: 20180308-1800
//		OS: Windows 10, v.10.0, x86_64 / win32
		JFinal.start("WebRoot", PORT, "/");
		
		logger.debug("Building 项目启动成功,端口:{}",PORT);
		
		// IDEA 启动方式
		// JFinal.start("WebRoot", 80, "/");
	}

}
