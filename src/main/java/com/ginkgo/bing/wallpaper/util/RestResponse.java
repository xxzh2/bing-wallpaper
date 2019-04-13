package com.ginkgo.bing.wallpaper.util;

import java.util.List;

public class RestResponse {

	private String code;
	private String message;
	private List<?> data;

	public RestResponse() {

	}

	public RestResponse(String code, String message, List<?> data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public static RestResponse error() {
		return new RestResponse("1", "操作失败", null);
	}

	public static RestResponse warn() {
		return new RestResponse("2", "操作异常", null);
	}

	public static RestResponse success() {
		return new RestResponse("0", "操作成功", null);
	}
}
