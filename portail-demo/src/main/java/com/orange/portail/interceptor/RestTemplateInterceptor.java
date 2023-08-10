package com.orange.portail.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Interceptor to log incoming requests and outgoing response.
 *
 * @since 1.1
 */
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

    /**
     * Intercept.
     *
     * @param request   the request
     * @param body      the body
     * @param execution the execution
     * @return the client http response
     * @throws IOException Signals that an I/O exception has occurred.
     */
    /*
     * @see
     * org.springframework.http.client.ClientHttpRequestInterceptor#intercept(org.
     * springframework.http.HttpRequest, byte[],
     * org.springframework.http.client.ClientHttpRequestExecution)
     */
    @Override
    public ClientHttpResponse intercept(final HttpRequest request, final byte[] body,
                                        final ClientHttpRequestExecution execution) throws IOException {
        logRequestDetails(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        logResponseDetails(response);
        return response;
    }

    /**
     * asynchronously logs HTTP request
     *
     * @param request incoming request
     * @param body    request body
     */
    @Async
    private void logRequestDetails(final HttpRequest request, final byte[] body) {
        final StringBuilder logBuilder = new StringBuilder();
        logBuilder.append(request.getMethod()).append(": ").append(request.getURI())
                .append(System.lineSeparator()).append("Headers: ").append(request.getHeaders())
                .append(System.lineSeparator()).append("Payload: ============================>")
                .append(System.lineSeparator()).append(new String(body, StandardCharsets.UTF_8));
    }

    /**
     * asynchronously logs HTTP response
     *
     * @param response incoming response
     * @throws IOException signal for IOException
     */
    @Async
    private void logResponseDetails(final ClientHttpResponse response) throws IOException {
        final StringBuilder logBuilder = new StringBuilder();
        logBuilder.append(response.getStatusCode()).append(": ").append(response.getStatusText())
                .append(System.lineSeparator()).append("Headers: ").append(response.getHeaders());
    }
}
