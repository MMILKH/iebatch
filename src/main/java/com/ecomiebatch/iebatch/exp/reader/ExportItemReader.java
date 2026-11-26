package com.ecomiebatch.iebatch.exp.reader;

import com.ecomiebatch.iebatch.exp.dto.OrdineDto;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.infrastructure.item.database.JdbcPagingItemReader;
import org.springframework.batch.infrastructure.item.database.Order;
import org.springframework.batch.infrastructure.item.database.PagingQueryProvider;
import org.springframework.batch.infrastructure.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.infrastructure.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.infrastructure.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.batch.infrastructure.item.database.support.SqlitePagingQueryProvider;
import org.springframework.batch.infrastructure.support.DatabaseType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;


@Component
public class ExportItemReader {

    private final DataSource dataSource;

    public ExportItemReader(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Bean
    @StepScope
    public JdbcPagingItemReader<OrdineDto> toJdbcPagingItemReader(
            @Value("#{jobParameters['targetDate']}") String date,
            PagingQueryProvider queryProvider) throws Exception {

        LocalDate targetDate = LocalDate.parse(date);
        LocalDateTime startOfDay = targetDate.atStartOfDay();
        LocalDateTime endOfDay = targetDate.plusDays(1).atStartOfDay();

        return new JdbcPagingItemReaderBuilder<OrdineDto>()
                .name("exportPagingReader")
                .dataSource(dataSource)
                .queryProvider(queryProvider)
                .parameterValues(Map.of(
                        "startOfDay", startOfDay,
                        "endOfDay", endOfDay
                ))
                .rowMapper(new BeanPropertyRowMapper<>(OrdineDto.class))
                .pageSize(8)
                .build();
    }
    @Bean
    private SqlPagingQueryProviderFactoryBean queryProvider() {
        var provider = new SqlPagingQueryProviderFactoryBean();
        provider.setSelectClause("SELECT *");
        provider.setFromClause("FROM ORDINE");
        provider.setWhereClause("WHERE DATA_CREAZIONE >= :startOfDay AND DATA_CREAZIONE < :endOfDay");
        provider.setDataSource(dataSource);
        provider.setSortKeys(Map.of("ID", Order.ASCENDING));
        return provider;
    }
}
