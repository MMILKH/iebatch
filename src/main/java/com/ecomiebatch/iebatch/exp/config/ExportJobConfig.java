package com.ecomiebatch.iebatch.exp.config;

import com.ecomiebatch.iebatch.exp.dto.ExportCsv;
import com.ecomiebatch.iebatch.exp.dto.OrdineDto;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.EnableJdbcJobRepository;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;




@Configuration
@EnableBatchProcessing
@EnableJdbcJobRepository
public class ExportJobConfig {
    @Bean
    public Job exportOrdiniJob(JobRepository jobRepository, Step exportOrdiniStep) {
        return new JobBuilder("exportOrdiniJob", jobRepository)
                .start(exportOrdiniStep)
                .build();
    }

    @Bean
    public Step exportOrdiniStep(JobRepository jobRepository,
                                 PlatformTransactionManager transactionManager,
                                 ItemReader<OrdineDto> reader,
                                 ItemProcessor<OrdineDto, ExportCsv> processor,
                                 ItemWriter<ExportCsv> writer) {


        return new StepBuilder("exportOrdiniStep", jobRepository)
                .<OrdineDto, ExportCsv>chunk(4)
                .transactionManager(transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();



    }
}
