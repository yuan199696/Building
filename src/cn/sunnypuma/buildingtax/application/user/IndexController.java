package cn.sunnypuma.buildingtax.application.user;

import com.jfinal.core.Controller;

public class IndexController extends Controller{
	public void index(){
		renderText("Hello User!");
	}
}