package com.ginkgo.bing.wallpaper.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ginkgo.bing.wallpaper.mvc.dao.FileMapper;
import com.ginkgo.bing.wallpaper.mvc.model.FileEntity;

import lombok.extern.slf4j.Slf4j;

/**
 * 文件操作服务
 *
 * @author Think
 *
 */
@Slf4j
@Service
public class FileService {

	@Autowired
	private FileMapper dao;

	/**
	 * 忽略已经存在的文件
	 *
	 * @param entity
	 * @return
	 */
	public FileEntity ingnoreOrSaveFile(FileEntity entity) {
		if (entity == null || entity.getSha1() == null) {
			return null;
		}
		List<FileEntity> existEntity = dao.existStore(entity.getSha1());
		if (existEntity == null) {
			dao.saveStore(entity);
			int id = dao.saveLink(entity);
			FileEntity savedEntity = dao.getFileById(id);
			return savedEntity;
		} else {
			return null;
		}
	}

	public boolean existStore(FileEntity entity) {
		if (entity == null || entity.getSha1() == null) {
			return false;
		}
		List<FileEntity> exists = dao.existStore(entity.getSha1());
		if (exists == null || exists.size() < 1) {
			return false;
		}
		return true;
	}

	/**
	 * 保存文件，若文件已存在，则记录文件信息，并指向相同文件
	 *
	 * @param entity
	 * @return
	 */
	public FileEntity saveFile(FileEntity entity) {
		if (entity == null || entity.getSha1() == null) {
			return null;
		}
		List<FileEntity> exists = dao.existStore(entity.getSha1());
		if (exists == null || exists.size() < 1) {
			dao.saveStore(entity);
		}
		dao.saveLink(entity);

		log.info("file-list: " + entity.getId());
		FileEntity savedEntity = dao.getFileById(entity.getId());
		return savedEntity;
	}

	/**
	 * 根据ID查找文件
	 *
	 * @param id
	 * @return
	 */
	public FileEntity getFile(Integer id) {
		log.debug("file-list: " + id);
		FileEntity savedEntity = dao.getFileById(id);
		return savedEntity;
	}

	public FileEntity getFileByShare(String share) {
		if (share == null) {
			return null;
		}
		FileEntity entity = dao.getFileByShare(share);
		return entity;
	}

	public void updateCount(FileEntity entity) {
		if (entity != null) {
			dao.updateCount(entity);
		}
	}
}
