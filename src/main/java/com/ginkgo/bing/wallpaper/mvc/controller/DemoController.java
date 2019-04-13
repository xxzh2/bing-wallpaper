package com.ginkgo.bing.wallpaper.mvc.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller 示例
 * 
 * @author Think
 *
 */
@RequestMapping(value = { "/demo" })
@Controller
public class DemoController {
	static final Log log = LogFactory.getLog(DemoController.class);

	@RequestMapping(value = { "/upload" })
	public String upload() {
		return "upload";
	}

	@RequestMapping(value = { "/download" })
	public String download() {
		return "download";
	}

	@RequestMapping(value = { "", "/" })
	public String index() {
		return "index";
	}

	@RequestMapping(value = { "/multi-upload" })
	public String multiUpload() {
		return "multi-upload";
	}

	@RequestMapping(value = { "/list" })
	public String list() {
		return "list";
	}

}