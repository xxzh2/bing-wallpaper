package com.ginkgo.bing.wallpaper.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ginkgo.crawl.dual.bing.BingPicture;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BingJob implements Job {

	/**
	 * 检测文件store是否存在
	 */
	public void runTask() {
		log.debug(String.format(">> Start BingPicture "));
		BingPicture pic = new BingPicture();
		// 设置URL
		pic.setUrl("https://cn.bing.com");
		// 搜索并下载
		pic.download();
		log.debug(String.format("<< Check is complete."));
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		//
		runTask();
	}
}
