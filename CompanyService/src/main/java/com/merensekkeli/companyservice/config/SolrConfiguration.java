package com.merensekkeli.companyservice.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@Configuration
@EnableSolrRepositories(
    basePackages = "com.merensekkeli.companyservice"
    , namedQueriesLocation = "classpath:solr-named-queries.properties"
)
@ComponentScan
public class SolrConfiguration {

    @Bean
    public SolrClient solrClient(){
        return new HttpSolrClient.Builder("http://solr:8983/solr").build();
    }

    @Bean
    public SolrTemplate solrTemplate(SolrClient solrClient){
        return new SolrTemplate(solrClient);
    }
}
