package com.ecomiebatch.iebatch.imp.reader;

import com.ecomiebatch.iebatch.imp.dto.RegisterRequest;
import com.ecomiebatch.iebatch.imp.mapper.RegisterFieldSetMapper;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.batch.infrastructure.item.file.LineMapper;
import org.springframework.batch.infrastructure.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.infrastructure.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class ImportItemReader  {


    @Bean
    @StepScope
    public FlatFileItemReader<RegisterRequest> reader(LineMapper<RegisterRequest> lineMapper) {
        FlatFileItemReader<RegisterRequest> flatFileItemReader = new FlatFileItemReader<RegisterRequest>(lineMapper);
        flatFileItemReader.setName("import reader");
        flatFileItemReader.setResource(new FileSystemResource("User_register_request.csv"));
        return flatFileItemReader;
    }




    @Bean
    public LineMapper<RegisterRequest> lineMapper() {
        DefaultLineMapper<RegisterRequest> lineMapper = new DefaultLineMapper<RegisterRequest>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(";");
        lineTokenizer.setNames("nome","cognome","email","password","passwordConf","numero","indirizzo");
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(new RegisterFieldSetMapper());
        return lineMapper;
    }





}



