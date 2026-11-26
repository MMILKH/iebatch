package com.ecomiebatch.iebatch.exp.writer;

import com.ecomiebatch.iebatch.exp.dto.ExportCsv;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.infrastructure.item.file.FlatFileFooterCallback;
import org.springframework.batch.infrastructure.item.file.FlatFileHeaderCallback;
import org.springframework.batch.infrastructure.item.file.FlatFileItemWriter;
import org.springframework.batch.infrastructure.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.infrastructure.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class ExportItemWriter {

    @Bean
    @StepScope
    public FlatFileItemWriter<ExportCsv> toFlatFileItemWriter(
            @Value("#{jobParameters['targetDate']}") String date) {

        BeanWrapperFieldExtractor<ExportCsv> fieldExtractor = new BeanWrapperFieldExtractor<>();

        fieldExtractor.setNames(new String[]{
                "uuidOrdine",
                "userUuid",
                "indirizzoSpedizione",
                "indirizzoFatturazione",
                "importoTotale",
                "data",
                "righeOrdine"
        });


        DelimitedLineAggregator<ExportCsv> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(";");
        lineAggregator.setFieldExtractor(fieldExtractor);
        FlatFileHeaderCallback headerCallback = writer -> writer.write("Ordini del giorno  " + date + "\n");
        FlatFileFooterCallback  footerCallback = writer -> writer.write("\n");


        FlatFileItemWriter<ExportCsv> writer = new FlatFileItemWriter<>(lineAggregator);
        writer.setName("export ordini writer");
        writer.setResource(new FileSystemResource("export " + date + ".csv"));
        writer.setAppendAllowed(true);
        writer.setHeaderCallback(headerCallback);
        writer.setFooterCallback(footerCallback);

        return writer;
    }
}
