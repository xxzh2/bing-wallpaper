package com.ginkgo.bing.wallpaper.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestMonitorJob implements Job {

	@Override
	public void execute(JobExecutionContext jobExecutionContext) {
		try {
			log.debug("-------------->\t\t[Monitor job is running]");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
