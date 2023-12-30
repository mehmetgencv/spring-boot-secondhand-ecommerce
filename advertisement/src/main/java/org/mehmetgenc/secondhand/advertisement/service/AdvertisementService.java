package org.mehmetgenc.secondhand.advertisement.service;

import org.mehmetgenc.secondhand.advertisement.dto.AdvertisementDto;
import org.mehmetgenc.secondhand.advertisement.dto.AdvertisementDtoConverter;
import org.mehmetgenc.secondhand.advertisement.dto.CreateAdvertisementRequest;
import org.mehmetgenc.secondhand.advertisement.model.Advertisement;
import org.mehmetgenc.secondhand.advertisement.repository.AdvertisementRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AdvertisementService {
    private final AdvertisementRepository advertisementRepository;
    private final RestTemplate restTemplate;


    public AdvertisementService(AdvertisementRepository advertisementRepository, RestTemplate restTemplate) {
        this.advertisementRepository = advertisementRepository;
        this.restTemplate = restTemplate;
    }

    public AdvertisementDto createAdvertisement(CreateAdvertisementRequest request){
        Advertisement advertisement = new Advertisement(
                request.getTitle(),
                request.getDescription(),
                request.getPrice(),
                request.getUserId(),
                request.getHashtag()
        );
        advertisementRepository.save(advertisement);
        return AdvertisementDtoConverter.INSTANCE.convertDto(advertisement);
    }
}
