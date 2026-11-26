package com.ecomiebatch.iebatch.exp.mapper;


import com.ecomiebatch.iebatch.exp.dto.ExportCsv;
import com.ecomiebatch.iebatch.exp.dto.OrdineDto;
import jdk.jfr.MemoryAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExportCsvMapper {

    @Mapping(source="codice",target="uuidOrdine")
    @Mapping(source="dataCreazione",target="data")
    @Mapping(source="importo",target="importoTotale")
    @Mapping(source="utenteId",target="userUuid")
    @Mapping(target="righeOrdine",ignore = true)
    ExportCsv toExportCsv(OrdineDto ordineDto);

}
