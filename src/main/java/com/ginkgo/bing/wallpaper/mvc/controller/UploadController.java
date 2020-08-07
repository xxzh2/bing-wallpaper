package com.ginkgo.bing.wallpaper.mvc.controller;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import com.ginkgo.bing.wallpaper.mvc.model.FileEntity;
import com.ginkgo.bing.wallpaper.mvc.service.FileService;
import com.ginkgo.bing.wallpaper.util.FileSafeCode;
import com.ginkgo.bing.wallpaper.util.Response;

import lombok.extern.slf4j.Slf4j;

/**
 * 文件下载
 *
 * @author Think
 *
 */
@Slf4j
@Controller
@RequestMapping("/upload")
public class UploadController {

	public static final String DEFAULT_DIR = System.getProperty("user.home");

	@Autowired
	private FileService service;

	/**
	 * 文件存储根目录
	 */
	@Value("${fms.home}")
	private String baseDir;

	@Value("${fms.tmp}")
	private String tmpDir;

	@RequestMapping("/")
	@ResponseBody
	public boolean index(HttpServletRequest request) {
		return true;
	}

	/***
	 * 保存文件
	 *
	 * @param mpfile
	 * @param path
	 * @return
	 */
	private FileEntity saveFile(MultipartFile mpfile, String path) {
		// 判断文件是否为空
		if (!mpfile.isEmpty()) {
			try {
				File fileBase = new File(path);
				if (!fileBase.exists()) {
					fileBase.mkdirs();
				}
				String subname = new SimpleDateFormat("yyyyMM").format(new Date());
				File arcd = new File(path, subname);
				arcd.mkdirs();
				// 随机文件名
				String uuid = UUID.randomUUID().toString().replace("-", "");
				// 临时文件
				File tmp = File.createTempFile("_fms-" + uuid.substring(0, 16), ".tmp", getTmpFileDir());
				// 目标文件
				File dist = new File(arcd, uuid);
				// 写入临时文件
				mpfile.transferTo(tmp);
				String sha1 = FileSafeCode.getSha1(tmp);
				//
				FileEntity entity = new FileEntity();
				entity.setSize(mpfile.getSize());
				entity.setSha1(sha1);
				entity.setName(mpfile.getOriginalFilename());
				entity.setType("A");
				entity.setOwner("default");
				entity.setPath(dist.toURI().toString().replace(fileBase.toURI().toString(), ""));
				entity.setShare(uuid.toLowerCase());
				entity.setCreateDate(new Date());
				entity.setUpdateDate(new Date());

				if (!service.existStore(entity)) {
					// 创建物理文件存储
					log.info(String.format("create store: %s -> %s ", tmp.getPath(), dist.getPath()));
					Files.copy(tmp.toPath(), dist.toPath());
				} else {
					tmp.deleteOnExit();
				}
				//
				return service.saveFile(entity);
			} catch (Exception e) {
				log.error("{}", e);
			}
		}
		return null;
	}

	@RequestMapping({ "/filesUpload", "files" })
	@ResponseBody
	public Response<List<FileEntity>> filesUpload(HttpServletRequest request) {
		MultipartFile[] files = null;
		try {
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart) {
				MultipartHttpServletRequest multipartRequest = WebUtils.getNativeRequest(request,
						MultipartHttpServletRequest.class);
				List<MultipartFile> fileList = multipartRequest.getFiles("files");
				if (fileList != null && fileList.size() > 0) {
					files = fileList.toArray(new MultipartFile[fileList.size()]);
				}
			}
		} catch (Exception e) {
			log.error("{}", e);
		}
		Response<List<FileEntity>> resp = null;
		List<FileEntity> fileEntityList = new ArrayList<FileEntity>();
		// 判断file数组不能为空并且长度大于0
		if (files != null && files.length > 0) {
			// 循环获取file数组中得文件
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				// 保存文件
				FileEntity entity = saveFile(file, getBaseDir());
				fileEntityList.add(entity);
			}
			resp = new Response<List<FileEntity>>().success();
		} else {
			resp = new Response<List<FileEntity>>().error();
		}
		resp.setData(fileEntityList);
		return resp;
	}

	private File getTmpFileDir() {
		File tmpFile = new File(getTmpDir());
		if (!tmpFile.exists())
			tmpFile.mkdirs();
		log.warn(tmpFile.getAbsolutePath());
		return tmpFile;

	}

	public String getBaseDir() {
		return baseDir == null ? DEFAULT_DIR : baseDir;
	}

	public String getTmpDir() {
		return tmpDir == null ? DEFAULT_DIR : tmpDir;
	}

}