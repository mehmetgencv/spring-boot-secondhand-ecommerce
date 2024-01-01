package org.mehmetgenc.secondhand.advertisement.dto;

import org.junit.jupiter.api.Test;
import org.mehmetgenc.secondhand.advertisement.model.Advertisement;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AdvertisementDtoConverterTest {

    @Test
    public void testConvert() {
        Advertisement advertisement = new Advertisement(
                "id-1",
                "title",
                "description1",
                123.0,
                LocalDateTime.now(),
                LocalDateTime.now(),
                987L,
                Set.of("hashtag1", "hashtag2"));
        AdvertisementDto advertisementDto = AdvertisementDtoConverter.INSTANCE.convertDto(advertisement);
        assertEquals(advertisementDto.getId(), "id-1");
        assertEquals(advertisementDto.getTitle(), "title");
        assertEquals(advertisementDto.getDescription(), "description1");
        assertEquals(advertisementDto.getPrice(), 123.0);
        assertEquals(advertisementDto.getUserId(), 987L);
        assertEquals(advertisementDto.getHashtags(), Set.of("hashtag1", "hashtag2"));
    }
}