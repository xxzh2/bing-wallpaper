package com.ginkgo.bing.wallpaper.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ginkgo.bing.wallpaper.mvc.model.FileEntity;
@Mapper
@Repository
public interface FileMapper {
	
	public FileEntity getFileById(int id);

	public FileEntity getFileByShare(String share);
	
	public List<FileEntity> existStore(String sha1);

	/**
	 * 保存文件数据
	 * @param entity
	 * @return count
	 */
	public int saveStore(FileEntity entity);
	
	/**
	 * 保存文件信息
	 * @param entity
	 * @return count
	 */
	public int saveLink(FileEntity entity);
	
	/**
	 * 更新分享次数
	 * @return
	 */
	public int updateCount(FileEntity entity);
	
	
}
