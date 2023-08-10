package com.orange.portail.service.impl;

import com.orange.portail.dto.ConnectionDTO;
import com.orange.portail.service.ConnectionService;
import com.orange.portail.util.ServiceUrl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ConnectionServiceImpl implements ConnectionService {
    private final ServiceUrl serviceUrl;
    private final RestTemplate restTemplate;

    public ConnectionServiceImpl(ServiceUrl serviceUrl, RestTemplate restTemplate) {
        this.serviceUrl = serviceUrl;
        this.restTemplate = restTemplate;
    }

    @Override
    public ConnectionDTO getConnections() {
        return retrieveConnections();
    }

    private ConnectionDTO retrieveConnections() {
        String connectionUri = serviceUrl.getConnections();
        ResponseEntity<ConnectionDTO> productConfigurationResponseEntity = restTemplate.getForEntity(connectionUri, ConnectionDTO.class);
        return productConfigurationResponseEntity.getBody();
    }
}
