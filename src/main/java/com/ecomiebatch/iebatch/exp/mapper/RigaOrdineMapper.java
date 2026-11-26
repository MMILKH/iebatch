package com.ecomiebatch.iebatch.exp.mapper;

import com.ecomiebatch.iebatch.entity.RigaOrdine;
import com.ecomiebatch.iebatch.exp.dto.RigaOrdineDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RigaOrdineMapper {

    List<RigaOrdineDto> toDtoList(List<RigaOrdine> entities);
}
