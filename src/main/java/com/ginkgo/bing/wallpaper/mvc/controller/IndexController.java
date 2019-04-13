package com.ginkgo.bing.wallpaper.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ginkgo.bing.wallpaper.exception.AuthException;
import com.ginkgo.bing.wallpaper.mvc.model.AuthEntity;
import com.ginkgo.bing.wallpaper.mvc.service.AuthService;
import com.ginkgo.bing.wallpaper.util.Response;

import lombok.extern.slf4j.Slf4j;

/**
 * Controller 示例
 * 
 * @author Think
 *
 */
@Slf4j
@Controller
public class IndexController {
	
	@Autowired
	private AuthService service;

	@RequestMapping(value = { "/", "/index*" })	
	@ResponseBody
	public String index() {
		return "FMS works! <br /> <br /><a href=\"./demo\">demo</a>";
	}
	
	@RequestMapping(value = { "/auth/{key}" })
	@ResponseBody
	public Response<?> auth(@PathVariable String key) throws AuthException {
		Response<String> r = null;
		log.debug(key);
		AuthEntity auth = service.getAuth(key);
		if (auth != null) {
			log.debug(auth.getAppId());
			r = new Response<String>().success();
			r.setData(auth.getAppId());
		} else {
			r = new Response<String>().error();
			throw new AuthException();
		}
		return r;
	}

	@RequestMapping(value = { "/auth" })
	@ResponseBody
	public Response<String> authGen(@RequestParam("user") String user, @RequestParam("passwd") String pwd) {
		Response<String> r = null;
		r = new Response<String>().success();
		r.setData(service.getAuthorize(user, pwd));
		return r;
	}

}