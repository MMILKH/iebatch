package com.ecomiebatch.iebatch.exp.processor;

import com.ecomiebatch.iebatch.exp.dto.ExportCsv;
import com.ecomiebatch.iebatch.exp.dto.OrdineDto;
import com.ecomiebatch.iebatch.exp.dto.RigaOrdineDto;
import com.ecomiebatch.iebatch.exp.mapper.ExportCsvMapper;
import com.ecomiebatch.iebatch.repository.RigaOrdineRepository;
import com.ecomiebatch.iebatch.exp.mapper.RigaOrdineMapper;

import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ExportProcessor implements ItemProcessor<OrdineDto, ExportCsv> {

    private final RigaOrdineRepository rigaOrdineRepository;
    private final RigaOrdineMapper rigaOrdineMapper;
    private final ExportCsvMapper exportCsvMapper;

    public ExportProcessor(RigaOrdineRepository rigaOrdineRepository, RigaOrdineMapper rigaOrdineMapper, ExportCsvMapper exportCsvMapper) {
        this.rigaOrdineRepository = rigaOrdineRepository;
        this.rigaOrdineMapper = rigaOrdineMapper;
        this.exportCsvMapper = exportCsvMapper;
    }

    @Override
    public  ExportCsv process(@NonNull OrdineDto item) throws Exception {

        List<RigaOrdineDto> righeDto = rigaOrdineMapper.toDtoList(rigaOrdineRepository.findByOrdineId(item.getId()));
        String righeDaExp = righeDto.stream().map(riga -> riga.getProdottoId()
        + "-"+ riga.getQuantita()).collect(Collectors.joining("|"));
        ExportCsv exportCsv = exportCsvMapper.toExportCsv(item);
        exportCsv.setRigheOrdine(righeDaExp);
        log.info("{}",exportCsv);
        return exportCsv;

    }
}
