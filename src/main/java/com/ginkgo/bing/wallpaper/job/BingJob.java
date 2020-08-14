package com.ginkgo.bing.wallpaper.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ginkgo.bing.BingPicture;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BingJob implements Job {

	/**
	 * 检测文件store是否存在
	 */
	public void runTask() {
		log.debug("{}", ">> Start BingPicture ");
		BingPicture pic = new BingPicture();
		// 设置URL
		pic.setUrl("https://cn.bing.com");
//		pic.setUrl("http://192.168.88.138");
		
		// 搜索并下载
		pic.download();
		log.debug("{}", "<< Check is complete.");
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		//
		runTask();
	}
}
