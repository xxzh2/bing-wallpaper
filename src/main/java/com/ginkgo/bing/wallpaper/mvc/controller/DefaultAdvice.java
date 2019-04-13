package com.ginkgo.bing.wallpaper.mvc.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;

import com.ginkgo.bing.wallpaper.exception.AuthException;
import com.ginkgo.bing.wallpaper.util.Response;

/**
 * 异常处理
 * 
 * @author Think
 *
 */
@ControllerAdvice
public class DefaultAdvice {

	/**
	 * 异常捕获：空文件上传异常捕获。
	 * 
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Response<String> handleException(Exception e) throws Exception {
		Response<String> er = new Response<String>().error();
		if (e instanceof MultipartException) {
			er.setData("");
			er.setMessage("文件错误");
		} else if (e instanceof AuthException) {
			er.setData("");
			er.setMessage("认证失败");
		} else {
			er.setData("");
		}
		e.printStackTrace();
		return er;
	}

	/**
	 * 异常捕获：空文件上传异常捕获。
	 * 
	 * @param e
	 * @return
	 * @throws AuthException
	 */
	@ExceptionHandler(AuthException.class)
	@ResponseBody
	public Response<String> handleAuthException(Exception e) throws Exception {
		Response<String> er = new Response<String>().error();
		if (e instanceof AuthException) {
			er.setData("");
			er.setMessage("认证失败");
		} else {
			er.setData("");
		}
		return er;
	}

}
