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
public class FileEntity {
	/**
	 * 文件ID, 自增序列
	 */
	private Integer id;

	/**
	 * 文件名
	 */
	private String name;

	/**
	 * 类型
	 */
	private String type;

	/**
	 * SHA-1
	 */
	private String sha1;

	/**
	 * 文件字节数
	 */
	private Long size;

	/**
	 * 文件所有者，默认为default
	 */
	private String owner;

	/**
	 * 存储地址
	 */
	private String path;

	/**
	 * 提取码
	 */
	String share;

	/**
	 * 分享次数
	 */
	private Integer count;

	private Boolean exist;

	/**
	 * 备注
	 */
	private String desc;

	private Date createDate;

	private Date updateDate;

}
