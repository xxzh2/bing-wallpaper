package com.ginkgo.bing.wallpaper.mvc.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ginkgo.bing.wallpaper.mvc.model.FileStore;
@Mapper
@Repository
public interface StoreMapper {

	public List<FileStore> getExpiredStore(Date updateDate);

	/**
	 * 更新分享次数
	 *
	 * @return
	 */
	public int updateStore(@Param(value = "list") List<FileStore> list);

}
