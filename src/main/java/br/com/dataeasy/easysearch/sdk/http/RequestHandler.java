package br.com.dataeasy.easysearch.sdk.http;

import br.com.dataeasy.easysearch.sdk.model.CredentialsDTO;
import br.com.dataeasy.easysearch.sdk.model.RequestBody;
import br.com.dataeasy.easysearch.sdk.model.ResponseBody;

public interface RequestHandler {

    ResponseBody execute(String path, RequestBody body, HttpMethod method, Class<? extends ResponseBody> responseType);
    void setBaseUrl(String baseUrl);
    void setAuthorizationHeaders(CredentialsDTO credentials, String accessToken);
    void init();
    String getAccessToken();
    String getClientId();
}
