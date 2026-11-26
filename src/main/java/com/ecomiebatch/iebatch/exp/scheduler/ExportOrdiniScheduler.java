//package com.ecomiebatch.iebatch.exp.scheduler;
//
//import org.springframework.batch.core.job.Job;
//import org.springframework.batch.core.job.parameters.InvalidJobParametersException;
//import org.springframework.batch.core.job.parameters.JobParameters;
//import org.springframework.batch.core.job.parameters.JobParametersBuilder;
//import org.springframework.batch.core.launch.JobExecutionAlreadyRunningException;
//import org.springframework.batch.core.launch.JobInstanceAlreadyCompleteException;
//import org.springframework.batch.core.launch.JobOperator;
//import org.springframework.batch.core.launch.JobRestartException;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ExportOrdiniScheduler {
//
//    private final JobOperator jobLauncher;
//    private final Job exportOrdiniJob;
//    private final String targetDate;
//
//    public ExportOrdiniScheduler(JobOperator jobLauncher, Job exportOrdiniJob,
//                                 @Value("${targetDate:}")String targetDate) {
//        this.jobLauncher = jobLauncher;
//        this.exportOrdiniJob = exportOrdiniJob;
//        this.targetDate = targetDate;
//    }
//
//    @Scheduled(cron = "1 * * * * *")
//    public void trigger() throws JobInstanceAlreadyCompleteException, InvalidJobParametersException, JobExecutionAlreadyRunningException, JobRestartException {
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addString("targetDate",targetDate)
//                .addLong("run.id", System.currentTimeMillis())
//                .toJobParameters();
//
//        this.jobLauncher.start(exportOrdiniJob, jobParameters);
//
//    }
//}
