package org.mehmetgenc.secondhand.advertisement.repository;

import org.mehmetgenc.secondhand.advertisement.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertisementRepository extends JpaRepository<Advertisement, String> {
}
