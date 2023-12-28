package org.mehmetgenc.secondhand.advertisement.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
@Configuration
@EnableElasticsearchRepositories(basePackages = "org.mehmetgenc.secondhand.advertisement.repository")
@ComponentScan(basePackages = "org.mehmetgenc.secondhand.advertisement")
public class ElasticSearchRestClientConfig extends ElasticsearchConfiguration {

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                        .connectedTo("localhost:9200")
                .withBasicAuth("elastic", "")
                .build();
    }
}

