package org.mehmetgenc.secondhand.advertisement.repository;

import org.mehmetgenc.secondhand.advertisement.model.Advertisement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AdvertisementElasticSearchRepository extends ElasticsearchRepository<Advertisement, String> {

    Page<Advertisement> findByTitleLike(String title, Pageable pageable);

    @Query("{\"match\": {\"title\": {\"query\": \"?0\"}}}")
    Page<Advertisement> findByTitleFuzzy(String title, Pageable pageable);



}
