package com.orange.portail.handler;


import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        HttpStatus res = httpResponse.getStatusCode();
        if (HttpStatus.UNAUTHORIZED == res) {
            //throw new AuthenticationException("Missing credentials");
        }
    }

    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        HttpStatus.Series error = httpResponse.getStatusCode().series();
        return error == HttpStatus.Series.CLIENT_ERROR || error == HttpStatus.Series.SERVER_ERROR;
    }
}
