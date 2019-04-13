package com.ginkgo.bing.wallpaper.mvc.model;

import java.util.Date;

import lombok.Data;

/**
 * 文件实体信息
 *
 * @author Think
 *
 */
@Data
public class FileStore {

	/**
	 * SHA-1
	 */
	private String sha1;

	/**
	 * 存储地址
	 */
	private String path;

	private Boolean exist;

	private Date createDate;

	private Date updateDate;

}
