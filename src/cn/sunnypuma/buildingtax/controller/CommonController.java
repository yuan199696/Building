package cn.sunnypuma.buildingtax.controller;

import com.jfinal.captcha.CaptchaRender;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

public class CommonController extends Controller{

	@ActionKey("/verifycode")
	public void verifycode(){
		render(new CaptchaRender());
	}
	
	
}
