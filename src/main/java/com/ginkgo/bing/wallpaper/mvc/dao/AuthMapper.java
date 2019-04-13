package com.ginkgo.bing.wallpaper.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ginkgo.bing.wallpaper.mvc.model.AuthEntity;

@Mapper
@Repository
public interface AuthMapper {

	public List<AuthEntity> getAuthAll();

	/**
	 * 保存文件数据
	 * 
	 * @param entity
	 * @return count
	 */
	public int saveAuth(AuthEntity entity);

	/**
	 * 保存文件信息
	 * 
	 * @param entity
	 * @return count
	 */
	public int deleteAuth(AuthEntity entity);

	/**
	 * 更新分享次数
	 * 
	 * @return
	 */
	public int updateAuth(AuthEntity entity);

}
