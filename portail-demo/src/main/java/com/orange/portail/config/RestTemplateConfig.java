package com.orange.portail.config;


import com.orange.portail.handler.RestTemplateResponseErrorHandler;
import com.orange.portail.interceptor.RestTemplateInterceptor;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * The RestTemplateConfig class is used to do configuration for rest template
 *
 * @since 1.0
 */
@Configuration
public class RestTemplateConfig {

    /**
     * Creates rest template bean
     *
     * @return {@code org.springframework.web.client.RestTemplate}
     */
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restClient = new RestTemplate();
        restClient.setErrorHandler(new RestTemplateResponseErrorHandler());
        restClient.getInterceptors().add(new RestTemplateInterceptor());
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        restClient.setRequestFactory(requestFactory);
        return restClient;
    }
}