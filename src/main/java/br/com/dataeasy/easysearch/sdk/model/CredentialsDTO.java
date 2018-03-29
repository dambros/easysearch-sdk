package br.com.dataeasy.easysearch.sdk.model;

import br.com.dataeasy.easysearch.sdk.util.StringUtils;

public class CredentialsDTO implements RequestBody {

    private String clientId;
    private String clientKey;

    public CredentialsDTO(String clientId, String clientKey) {

        if (StringUtils.isNullOrEmpty(clientId)) {
            throw new IllegalArgumentException("clientId cannot be null.");
        }
        if (StringUtils.isNullOrEmpty(clientKey)) {
            throw new IllegalArgumentException("clientKey cannot be null.");
        }

        this.clientId = clientId;
        this.clientKey = clientKey;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientKey() {
        return clientKey;
    }

}
