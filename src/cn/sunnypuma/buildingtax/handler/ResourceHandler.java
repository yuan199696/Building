package cn.sunnypuma.buildingtax.handler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jfinal.handler.Handler;
import com.jfinal.kit.HandlerKit;

import cn.sunnypuma.buildingtax.application.common.model.base.Admin;


public class ResourceHandler extends Handler{
	
	private HttpServletRequest request;
	
	@Override
	public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
		// TODO Auto-generated method stub
		this.request = request;
		if(isForbidden(target)){
			HandlerKit.renderError404(request, response, isHandled);
		}
		next.handle(target, request, response, isHandled);
	}
	
	public boolean isForbidden(String target){
		
		String suffixHtml = ".html";
		
		if(target.endsWith(suffixHtml) && !isLogin()) {
			
			return true;
		}
		return false;
	}
	
	public boolean isLogin(){
		Admin admin = getSessionAttr(getCookieString("current_admin"));
		if (admin != null){
			return true;
		}
		return false;
	}

	public String getCookieString(String name){
		Cookie[] cookies = request.getCookies();
		if (cookies != null)
			for (Cookie cookie : cookies)
				if (cookie.getName().equals(name))
					return cookie.getValue();
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getSessionAttr(String key) {
		HttpSession session = request.getSession(false);
		return session != null ? (T)session.getAttribute(key) : null;
	}
}
