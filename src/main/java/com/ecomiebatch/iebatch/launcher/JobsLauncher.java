package com.ecomiebatch.iebatch.launcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JobsLauncher implements ApplicationRunner {
    private final JobOperator jobLauncher;
    private final Job exportOrdiniJob;
    private final Job importJob;
    private final String targetDate;
    private final JobRepository jobRepository;

    public JobsLauncher(JobOperator jobLauncher,
                        @Qualifier("exportOrdiniJob") Job exportOrdiniJob,
                        @Qualifier("importJob")Job importJob,
                        @Value("${targetDate:}")String targetDate, JobRepository jobRepository) {
        this.jobLauncher = jobLauncher;
        this.exportOrdiniJob = exportOrdiniJob;
        this.importJob = importJob;
        this.targetDate = targetDate;
        this.jobRepository = jobRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.info("JobRepository implementation: {}",
                jobRepository.getClass().getName());


        if (!args.containsOption("jobName")) {
            log.info("Nessun --jobName passato: nessun job avviato.");
            return;
        }

        String jobName = args.getOptionValues("jobName").get(0);
        long runId = System.currentTimeMillis();

        if ("exportOrdiniJob".equals(jobName)) {
            JobParameters params = new JobParametersBuilder()
                    .addString("targetDate", targetDate)
                    .addLong("run.id", runId)
                    .toJobParameters();

            jobLauncher.start(exportOrdiniJob, params);


        } else if ("importJob".equals(jobName)) {
            JobParameters params = new JobParametersBuilder()
                    .addLong("run.id", runId)
                    .toJobParameters();

            jobLauncher.start(importJob, params);

        } else {
            throw new IllegalArgumentException("Job non riconosciuto: " + jobName);
        }
    }

}

