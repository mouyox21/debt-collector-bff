package com.mercureit.DebtCollectorBFF.config;

import com.mercureit.DebtCollectorBFF.batch.DossierItemProcessor;
import com.mercureit.DebtCollectorBFF.batch.DossierItemReader;
import com.mercureit.DebtCollectorBFF.dto.DossierCreationDto;
import com.mercureit.DebtCollectorBFF.dto.DossierDto;
import com.mercureit.DebtCollectorBFF.dto.DossierDtoL;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Optional;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class DossierBatchConfig extends DefaultBatchConfiguration {

    @Autowired
    private ItemReader<DossierDto> itemReader;

    @Autowired
    private ItemProcessor<DossierDto, DossierDto> itemProcessor;

    @Autowired
    private ItemWriter<DossierDto> itemWriter;


    @Bean
    public Job job(JobRepository jobRepository, Step dossierStep) {
        return new JobBuilder("job", jobRepository)
                .preventRestart()
                .incrementer(new RunIdIncrementer())
                .start(dossierStep)
                .build();
    }

    @Bean
    public Step dossierStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("dossierStep", jobRepository)
                .<DossierDto, DossierDto>chunk(5, transactionManager)  // Adjust this if needed
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }

}

