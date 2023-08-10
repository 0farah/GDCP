package com.orange.portail.util.impl;

import com.orange.portail.config.ApplicationConfigProperties;
import com.orange.portail.util.ServiceUrl;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
@Component
public class ServiceUrlImpl implements ServiceUrl {

    private final ApplicationConfigProperties applicationConfigProperties;

    public ServiceUrlImpl(ApplicationConfigProperties applicationConfigProperties) {
        this.applicationConfigProperties = applicationConfigProperties;
    }

    @Override
    public String getConnections() {
        String endPoint = applicationConfigProperties.getConnectionUrl();
        return UriComponentsBuilder
                .fromUriString(endPoint)
                .toUriString();
    }
}
