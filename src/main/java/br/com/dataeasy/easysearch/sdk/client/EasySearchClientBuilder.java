package br.com.dataeasy.easysearch.sdk.client;

import java.util.HashMap;
import java.util.Map;

import br.com.dataeasy.easysearch.sdk.http.Request;
import br.com.dataeasy.easysearch.sdk.http.RequestHandler;
import br.com.dataeasy.easysearch.sdk.model.CredentialsDTO;
import br.com.dataeasy.easysearch.sdk.util.StringUtils;

public class EasySearchClientBuilder {

    private String baseUrl;
    private CredentialsDTO credentials;
    private RequestHandler requestHandler;
    private Map<String, String> params;

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

    public EasySearchClientBuilder withParams(Map<String, String> params) {
        setParams(params);
        return this;
    }

    /**
     * Build a client and authenticate it with the credentials previously specified. It requires
     * both credentials and baseUrl to be previously set.
     *
     * @return client which allows the use of the API
     */
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

        if (this.params == null) {
            this.params = new HashMap<String, String>();
        }

        EasySearchClient client = new EasySearchClient(this.baseUrl, this.requestHandler, this.params);
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

    protected void setParams(Map<String, String> params) {
        this.params = params;
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

    public Map<String, String> getParams() {
        return params;
    }
}
