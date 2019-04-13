package com.ginkgo.bing.wallpaper.mvc.model;

import lombok.Data;

@Data
public class AuthEntity {

	private Integer id;
	private String appId;
	private String appKey;
	private String appInfo;

	private String md5;

}
