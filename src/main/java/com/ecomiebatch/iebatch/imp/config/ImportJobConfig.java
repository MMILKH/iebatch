package com.ecomiebatch.iebatch.imp.config;

import com.ecomiebatch.iebatch.imp.dto.RegisterRequest;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
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
import org.springframework.web.client.RestClientResponseException;

@EnableBatchProcessing
@Configuration
public class ImportJobConfig {

   @Bean
   public Job importJob(JobRepository jobRepository, Step importStep) {
      return new JobBuilder("importJob", jobRepository)
              .start(importStep)
              .build();


   }

   @Bean
   public Step importStep(JobRepository jobRepository,
                          PlatformTransactionManager transactionManager,
                          ItemReader<RegisterRequest> reader,
                          ItemProcessor<RegisterRequest, RegisterRequest> processor,
                          ItemWriter<RegisterRequest> writer){

            return new StepBuilder("importStep",jobRepository)
                    .<RegisterRequest,RegisterRequest>chunk(1)
                    .transactionManager(transactionManager)
                    .reader(reader)
                    .processor(processor)
                    .writer(writer)
                    .faultTolerant()
                    .skip(RestClientResponseException.class)
                    .skipLimit(100)
                    .build();
   }
}
