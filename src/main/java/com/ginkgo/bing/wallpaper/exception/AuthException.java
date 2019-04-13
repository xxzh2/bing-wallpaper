package com.ginkgo.bing.wallpaper.exception;

public class AuthException extends Exception {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Auth faild";
	}
}
