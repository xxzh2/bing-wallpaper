package com.ginkgo.bing.wallpaper.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ginkgo.bing.wallpaper.exception.AuthException;
import com.ginkgo.bing.wallpaper.mvc.model.AuthEntity;
import com.ginkgo.bing.wallpaper.mvc.service.AuthService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

	@Autowired
	private AuthService service;

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		//
		String pAuth = request.getParameter("auth");
		String aAuth = (String) request.getAttribute("auth");
		AuthEntity au = null;
		if (pAuth != null) {
			au = service.getAuth(pAuth);
		} else if (aAuth != null) {
			au = service.getAuth(aAuth);
		} else {
			log.debug("auth faild");
			//postAuthException();
			return false;
		}

		if (au != null)
			log.debug("Auth: " + au.getAppId());
		else
			//postAuthException();
			return false;
		return true;
	}
	
	public void postAuthException() throws AuthException {
		throw new AuthException();
	}

}
