package com.facturacion.sanjorge.SanJorgeFacturacion.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
public class TimbradoJob implements Job {



    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //run job

        System.out.println("timbrando");
    }
}
