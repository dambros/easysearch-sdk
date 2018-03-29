package br.com.dataeasy.easysearch.sdk.mock;

import br.com.dataeasy.easysearch.sdk.http.HttpMethod;
import br.com.dataeasy.easysearch.sdk.http.RequestHandler;
import br.com.dataeasy.easysearch.sdk.model.AuthenticationResponseDTO;
import br.com.dataeasy.easysearch.sdk.model.CredentialsDTO;
import br.com.dataeasy.easysearch.sdk.model.DocumentInsertionResponseDTO;
import br.com.dataeasy.easysearch.sdk.model.RequestBody;
import br.com.dataeasy.easysearch.sdk.model.ResponseBody;

public class RequestMock implements RequestHandler {

    private String accessToken = "abcd12345";
    private String clientId;

    @Override
    public ResponseBody execute(String path, RequestBody body, HttpMethod method, Class<? extends ResponseBody> responseType) {

        ResponseBody response = null;
        if (responseType == AuthenticationResponseDTO.class) {
            CredentialsDTO credentials = (CredentialsDTO) body;
            setClientId(credentials.getClientId());
            response = new AuthenticationResponseDTO(accessToken);
        } else if (responseType == DocumentInsertionResponseDTO.class) {
            response = MockResponseBuilder.buildDocumentInsertResponse();
        }

        return response;
    }

    @Override
    public void setBaseUrl(String baseUrl) {
    }

    @Override
    public void setAuthorizationHeaders(CredentialsDTO credentials, String accessToken) {
    }

    @Override
    public void init() {
    }

    @Override
    public String getAccessToken() {
        return this.accessToken;
    }

    @Override
    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
