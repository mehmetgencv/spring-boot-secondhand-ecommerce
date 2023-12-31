package org.mehmetgenc.secondhand.advertisement.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.mehmetgenc.secondhand.advertisement.model.Advertisement;

@Mapper
public interface AdvertisementDtoConverter {

    AdvertisementDtoConverter INSTANCE = Mappers.getMapper(AdvertisementDtoConverter.class);

    AdvertisementDto convertDto(Advertisement advertisement);
}
