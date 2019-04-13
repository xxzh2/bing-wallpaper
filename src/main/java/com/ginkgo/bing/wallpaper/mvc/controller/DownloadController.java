package com.ginkgo.bing.wallpaper.mvc.controller;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ginkgo.bing.wallpaper.mvc.model.FileEntity;
import com.ginkgo.bing.wallpaper.mvc.service.FileService;

import lombok.extern.slf4j.Slf4j;

/**
 * 文件下载
 * 
 * @author Think
 *
 */
@Slf4j
@Controller
@RequestMapping("/download")
public class DownloadController {

	/**
	 * 文件存储根目录
	 */
	@Value("${fms.home}")
	private String basePath;

	@Autowired
	private FileService service;

	/**
	 * 按文件名称下载
	 * 
	 * @param type
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	@Deprecated
	@RequestMapping("/{type}")
	public ResponseEntity<byte[]> download(@PathVariable String type, @RequestParam(name = "f") String fileName)
			throws IOException {

		String dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", dfileName);

		File file = new File(basePath, fileName);

		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
	}

	@RequestMapping({ "", "/" })
	public ResponseEntity<byte[]> downloadShared(@RequestParam(name = "s") String share) throws IOException {
		FileEntity entity = service.getFileByShare(share);

		if (entity != null) {
			String dfileName = new String(entity.getName().getBytes("gb2312"), "iso8859-1");

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("attachment", dfileName);

			File file = new File(basePath, entity.getPath());
			log.debug("file exist {}", file.exists());
			if (file.exists()) {
				service.updateCount(entity);
				return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
			} else {
				throw new IOException();
			}
		} else {
			throw new IOException();
		}
	}
}
