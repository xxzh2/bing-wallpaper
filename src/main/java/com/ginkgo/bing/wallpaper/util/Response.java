package com.ginkgo.bing.wallpaper.util;

public final class Response<T> {

	private String code;
	private String message;
	private T data;

	public Response() {

	}

	public Response(String code, String message, T data) {
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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Response<T> error() {
		return new Response<T>("1", "操作失败", null);
	}

	public Response<T> warn() {
		return new Response<T>("2", "操作异常", null);
	}

	public Response<T> success() {
		return new Response<T>("0", "操作成功", null);
	}
}
