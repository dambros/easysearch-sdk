package br.com.dataeasy.easysearch.sdk.client;

import br.com.dataeasy.easysearch.sdk.http.RequestHandler;
import br.com.dataeasy.easysearch.sdk.util.StringUtils;
import br.com.dataeasy.easysearch.sdk.http.Request;
import br.com.dataeasy.easysearch.sdk.model.CredentialsDTO;

public class EasySearchClientBuilder {

    private String baseUrl;
    private CredentialsDTO credentials;
    private RequestHandler requestHandler;

    public EasySearchClientBuilder() {
    }

    public EasySearchClientBuilder withBaseUrl(String url) {

        if (StringUtils.isNullOrEmpty(url)) {
            throw new IllegalArgumentException("baseUrl cannot be null");
        }

        setBaseUrl(url);
        return this;
    }

    public EasySearchClientBuilder withCredentials(CredentialsDTO credentials) {
        setCredentials(credentials);
        return this;
    }

    public EasySearchClientBuilder withRequestHandler(RequestHandler requestHandler) {
        setRequestHandler(requestHandler);
        return this;
    }

    public EasySearchClient build() {

        if (this.baseUrl == null) {
            throw new IllegalArgumentException("baseUrl cannot be null");
        }

        if (this.credentials == null) {
            throw new IllegalArgumentException("credentials cannot be null");
        }

        if (this.requestHandler == null) {
            this.requestHandler = new Request();
        }

        EasySearchClient client = new EasySearchClient(this.baseUrl, this.requestHandler);
        client.authenticate(this.credentials);

        return client;
    }

    protected void setBaseUrl(String url) {
        this.baseUrl = url;
    }

    protected void setCredentials(CredentialsDTO credentials) {
        this.credentials = credentials;
    }

    protected void setRequestHandler(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public CredentialsDTO getCredentials() {
        return credentials;
    }

    public RequestHandler getRequestHandler() {
        return requestHandler;
    }
}
