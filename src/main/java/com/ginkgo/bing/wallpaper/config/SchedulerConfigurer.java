package com.ginkgo.bing.wallpaper.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import com.ginkgo.bing.wallpaper.job.AutowiringJobFactory;
import com.ginkgo.bing.wallpaper.job.BingJob;
import com.ginkgo.bing.wallpaper.job.TestMonitorJob;

/**
 *
 */
@Configuration
@ComponentScan("com.ahorioc.fms.job")
@PropertySource("classpath:scheduler.properties")
//@ConditionalOnProperty(name = "quartz.enabled")
public class SchedulerConfigurer {

	@Bean
	public JobFactory jobFactory(ApplicationContext applicationContext
	// injecting SpringLiquibase to ensure liquibase is already
	// initialized and created the quartz tables:
	// SpringLiquibase springLiquibase
	) {
		AutowiringJobFactory jobFactory = new AutowiringJobFactory();
		jobFactory.setApplicationContext(applicationContext);
		return jobFactory;
	}

	@Bean
	public Properties quartzProperties() throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
		propertiesFactoryBean.afterPropertiesSet();
		return propertiesFactoryBean.getObject();
	}

	static JobDetailFactoryBean createJobDetail(Class<?> jobClass) {
		JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
		factoryBean.setJobClass(jobClass);
		// job has to be durable to be stored in DB:
		factoryBean.setDurability(true);
		factoryBean.setName(jobClass.getSimpleName());
		return factoryBean;
	}

	/**
	 * createTrigger
	 * 
	 * @param jobDetail
	 * @param pollFrequencyMs - 循环周期
	 * @return
	 */
	static SimpleTriggerFactoryBean createTrigger(JobDetail jobDetail, long pollFrequencyMs) {
		SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
		factoryBean.setJobDetail(jobDetail);
		factoryBean.setStartDelay(0L);
		factoryBean.setRepeatInterval(pollFrequencyMs);
		factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
		// in case of misfire, ignore all missed triggers and continue :
		factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT);
		return factoryBean;
	}

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(//
			DataSource dataSource, //
			JobFactory jobFactory, //
			@Qualifier("testJobTrigger") Trigger testJobTrigger, //
			@Qualifier("checkJobTrigger") Trigger checkJobTrigger//
	) throws IOException {
		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		// this allows to update triggers in DB when updating settings in config file:
		// factory.setOverwriteExistingJobs(true);
		// factory.setDataSource(dataSource);
		factory.setJobFactory(jobFactory);
		// factory.setQuartzProperties(quartzProperties());
		factory.setTriggers(testJobTrigger, checkJobTrigger);
		return factory;
	}

	@Bean(name = "testJobTrigger")
	protected SimpleTriggerFactoryBean JobTrigger_testMonitor(//
			@Qualifier("testJobDetail") JobDetail jobDetail, //
			@Value("${job.frequency.test}") long frequency) {
		return createTrigger(jobDetail, frequency);
	}

	@Bean(name = "checkJobTrigger")
	protected SimpleTriggerFactoryBean JobTrigger_check(//
			@Qualifier("checkJobDetail") JobDetail jobDetail, //
			@Value("${job.frequency.check}") long frequency) {
		return createTrigger(jobDetail, frequency);
	}

	@Bean(name = "testJobDetail")
	protected JobDetailFactoryBean jobDetail_test() {
		return createJobDetail(TestMonitorJob.class);
	}

	@Bean(name = "checkJobDetail")
	protected JobDetailFactoryBean jobDetail_check() {
		return createJobDetail(BingJob.class);
	}
}
