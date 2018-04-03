package br.com.dataeasy.easysearch.sdk.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;

import java.net.URL;
import java.util.Map;

import br.com.dataeasy.easysearch.sdk.config.GsonObjectMapper;
import br.com.dataeasy.easysearch.sdk.exception.BadRequestException;
import br.com.dataeasy.easysearch.sdk.exception.InternalServerErrorException;
import br.com.dataeasy.easysearch.sdk.exception.NotFoundException;
import br.com.dataeasy.easysearch.sdk.exception.SdkClientException;
import br.com.dataeasy.easysearch.sdk.exception.UnauthorizedException;
import br.com.dataeasy.easysearch.sdk.model.CredentialsDTO;
import br.com.dataeasy.easysearch.sdk.model.RequestBody;
import br.com.dataeasy.easysearch.sdk.model.ResponseBody;

public class Request implements RequestHandler {

    private Gson gson;
    private String baseUrl;
    private String accessToken;
    private String clientId;

    public void init(Map<String, String> params) {
        Unirest.setObjectMapper(new GsonObjectMapper());
        Unirest.setDefaultHeader("Content-Type", "application/json");

        if(params.containsKey("maxTotal") && params.containsKey("maxPerRoute")) {
            int maxTotal = Integer.parseInt(params.get("maxTotal"));
            int maxPerRoute = Integer.parseInt(params.get("maxPerRoute"));
            Unirest.setConcurrency(maxTotal, maxPerRoute);
        }

        gson = new GsonBuilder().disableHtmlEscaping().create();
    }

    public ResponseBody execute(String path, RequestBody body, HttpMethod method,
                            Class<? extends ResponseBody> responseType) {

        HttpResponse<JsonNode> response;

        try {
            String url = new URL(baseUrl + path).toString();
            HttpRequest request = null;

            if (method == HttpMethod.GET) {
                response = Unirest.get(url).asJson();
            } else if (method == HttpMethod.HEAD) {
                response = Unirest.head(url).asJson();
            } else {
                switch (method) {
                    case POST:
                        request = Unirest.post(url);
                        break;
                    case PUT:
                        request = Unirest.put(url);
                        break;
                    case DELETE:
                        request = Unirest.delete(url);
                        break;
                    case OPTIONS:
                        request = Unirest.options(url);
                        break;
                }

                response = ((HttpRequestWithBody) request).body(body).asJson();
            }

        } catch (Exception ex) {
            throw new SdkClientException(ex);
        }

        if (response.getStatus() != 200) {
            throwHttpException(response);
        }

        return gson.fromJson(response.getBody().toString(), responseType);
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setAuthorizationHeaders(CredentialsDTO credentials, String accessToken) {
        Unirest.setDefaultHeader("Client-Id", credentials.getClientId());
        Unirest.setDefaultHeader("Authorization", accessToken);
        this.setAccessToken(accessToken);
        this.setClientId(credentials.getClientId());
    }

    public void throwHttpException(HttpResponse response) {

        String exception = response.getBody().toString();
        switch (response.getStatus()) {
            case 400:
                throw new BadRequestException(exception);
            case 401:
                throw new UnauthorizedException(exception);
            case 404:
                throw new NotFoundException(exception);
            case 500:
                throw new InternalServerErrorException(exception);
            default:
                throw new SdkClientException(exception);
        }
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getClientId() {
        return clientId;
    }

    private void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    private void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
