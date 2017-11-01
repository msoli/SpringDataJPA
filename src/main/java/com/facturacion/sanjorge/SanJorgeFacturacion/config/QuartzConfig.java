package com.facturacion.sanjorge.SanJorgeFacturacion.config;


import com.facturacion.sanjorge.SanJorgeFacturacion.job.TimbradoJob;
import org.quartz.SimpleTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
public class QuartzConfig {

    @Value("${org.quartz.scheduler.instanceName}")
    private String instanceName;

    @Value("${org.quartz.scheduler.instanceId}")
    private String instanceId;

    @Value("${org.quartz.threadPool.threadCount}")
    private String threadCount;

    @Value("${job.startDelay}")
    private Long startDelay;

    @Value("${job.repeatInterval}")
    private Long repeatInterval;

    @Value("${job.cronExpression}")
    private String cronExpression;

    @Value("${job.description}")
    private String description;

    @Value("${job.key}")
    private String key;


    @Autowired
    private Environment environment;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    @Qualifier("h2DataSource")
    private DataSource h2DataSource;



    @Bean
    public org.quartz.spi.JobFactory jobFactory(ApplicationContext applicationContext) {
        QuartzJobFactory sampleJobFactory = new QuartzJobFactory();
        sampleJobFactory.setApplicationContext(applicationContext);
        return sampleJobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(ApplicationContext applicationContext) {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();

        factory.setOverwriteExistingJobs(true);
        factory.setJobFactory(jobFactory(applicationContext));

        Properties quartzProperties = new Properties();
        quartzProperties.setProperty("org.quartz.scheduler.instanceName", instanceName);
        quartzProperties.setProperty("org.quartz.scheduler.instanceId", instanceId);
        quartzProperties.setProperty("org.quartz.threadPool.threadCount", threadCount);

        factory.setDataSource(h2DataSource);

        factory.setQuartzProperties(quartzProperties);
        factory.setTriggers(updateCronTrigger().getObject()); //updateCronTrigger().getObject()

        return factory;
    }

    @Bean(name = "updateCronTrigger")
    public CronTriggerFactoryBean updateCronTrigger() {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(updateJobDetail().getObject());
        factoryBean.setCronExpression(environment.getProperty("job.cronExpression"));
        factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
        return factoryBean;
    }


    @Bean(name = "updateJob")
    public JobDetailFactoryBean updateJobDetail() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(TimbradoJob.class);
        jobDetailFactoryBean.setDescription(description);
        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setName(key);
        return jobDetailFactoryBean;
    }
}
