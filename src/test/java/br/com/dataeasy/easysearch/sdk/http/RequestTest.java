package br.com.dataeasy.easysearch.sdk.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.mashape.unirest.request.body.RequestBodyEntity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import br.com.dataeasy.easysearch.sdk.exception.BadRequestException;
import br.com.dataeasy.easysearch.sdk.exception.InternalServerErrorException;
import br.com.dataeasy.easysearch.sdk.exception.NotFoundException;
import br.com.dataeasy.easysearch.sdk.exception.SdkClientException;
import br.com.dataeasy.easysearch.sdk.exception.UnauthorizedException;
import br.com.dataeasy.easysearch.sdk.model.AuthenticationResponseDTO;
import br.com.dataeasy.easysearch.sdk.model.ResponseBody;
import br.com.dataeasy.easysearch.sdk.model.CredentialsDTO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Request.class, Unirest.class})
public class RequestTest {

    private static final String PATH = "/api/test";
    private static final String BASE_URL = "http://www.dataeasy.com.br";

    private Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    private CredentialsDTO credentials = new CredentialsDTO("clientId", "clientKey");
    private JsonNode json = new JsonNode("{\"access_token\":\"abcde12345\"}");
    private Request request = new Request();

    @Before
    public void setUp() throws Exception {
        Whitebox.setInternalState(this.request, "gson", gson);
    }

    @Test(expected = BadRequestException.class)
    public void testThrowHttpException400() throws Exception {
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatus()).thenReturn(400);
        when(response.getBody()).thenReturn("exception");
        this.request.throwHttpException(response);
    }

    @Test(expected = UnauthorizedException.class)
    public void testThrowHttpException401() throws Exception {
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatus()).thenReturn(401);
        when(response.getBody()).thenReturn("exception");
        this.request.throwHttpException(response);
    }

    @Test(expected = NotFoundException.class)
    public void testThrowHttpException404() throws Exception {
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatus()).thenReturn(404);
        when(response.getBody()).thenReturn("exception");
        this.request.throwHttpException(response);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testThrowHttpException500() throws Exception {
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatus()).thenReturn(500);
        when(response.getBody()).thenReturn("exception");
        this.request.throwHttpException(response);
    }

    @Test(expected = SdkClientException.class)
    public void testThrowHttpException() throws Exception {
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatus()).thenReturn(666);
        when(response.getBody()).thenReturn("exception");
        this.request.throwHttpException(response);
    }

    @Test
    public void testExecute200() throws Exception {
        testSuccessRequestWithoutBody(HttpMethod.GET);
        testSuccessRequestWithoutBody(HttpMethod.HEAD);
        testSuccessRequestWithBody200(HttpMethod.POST);
        testSuccessRequestWithBody200(HttpMethod.PUT);
        testSuccessRequestWithBody200(HttpMethod.DELETE);
        testSuccessRequestWithBody200(HttpMethod.OPTIONS);
    }

    @Test
    public void testExecuteDifferentThen200() throws Exception {
        testRequestWithBodyNot200(HttpMethod.POST, 400, BadRequestException.class);
        testRequestWithBodyNot200(HttpMethod.POST, 401, UnauthorizedException.class);
        testRequestWithBodyNot200(HttpMethod.POST, 404, NotFoundException.class);
        testRequestWithBodyNot200(HttpMethod.POST, 500, InternalServerErrorException.class);
        testRequestWithBodyNot200(HttpMethod.POST, 666, SdkClientException.class);

        testRequestWithBodyNot200(HttpMethod.PUT, 400, BadRequestException.class);
        testRequestWithBodyNot200(HttpMethod.PUT, 401, UnauthorizedException.class);
        testRequestWithBodyNot200(HttpMethod.PUT, 404, NotFoundException.class);
        testRequestWithBodyNot200(HttpMethod.PUT, 500, InternalServerErrorException.class);
        testRequestWithBodyNot200(HttpMethod.PUT, 666, SdkClientException.class);

        testRequestWithBodyNot200(HttpMethod.DELETE, 400, BadRequestException.class);
        testRequestWithBodyNot200(HttpMethod.DELETE, 401, UnauthorizedException.class);
        testRequestWithBodyNot200(HttpMethod.DELETE, 404, NotFoundException.class);
        testRequestWithBodyNot200(HttpMethod.DELETE, 500, InternalServerErrorException.class);
        testRequestWithBodyNot200(HttpMethod.DELETE, 666, SdkClientException.class);

        testRequestWithBodyNot200(HttpMethod.OPTIONS, 400, BadRequestException.class);
        testRequestWithBodyNot200(HttpMethod.OPTIONS, 401, UnauthorizedException.class);
        testRequestWithBodyNot200(HttpMethod.OPTIONS, 404, NotFoundException.class);
        testRequestWithBodyNot200(HttpMethod.OPTIONS, 500, InternalServerErrorException.class);
        testRequestWithBodyNot200(HttpMethod.OPTIONS, 666, SdkClientException.class);

        testRequestWithoutBodyNot200(HttpMethod.GET, 400, BadRequestException.class);
        testRequestWithoutBodyNot200(HttpMethod.GET, 401, UnauthorizedException.class);
        testRequestWithoutBodyNot200(HttpMethod.GET, 404, NotFoundException.class);
        testRequestWithoutBodyNot200(HttpMethod.GET, 500, InternalServerErrorException.class);
        testRequestWithoutBodyNot200(HttpMethod.GET, 666, SdkClientException.class);

        testRequestWithoutBodyNot200(HttpMethod.HEAD, 400, BadRequestException.class);
        testRequestWithoutBodyNot200(HttpMethod.HEAD, 401, UnauthorizedException.class);
        testRequestWithoutBodyNot200(HttpMethod.HEAD, 404, NotFoundException.class);
        testRequestWithoutBodyNot200(HttpMethod.HEAD, 500, InternalServerErrorException.class);
        testRequestWithoutBodyNot200(HttpMethod.HEAD, 666, SdkClientException.class);
    }

    private <T extends SdkClientException> void testRequestWithBodyNot200(
            HttpMethod method, int status,
            Class<T> expectedException) throws Exception {

        HttpResponse mockResponse = mock(HttpResponse.class);
        when(mockResponse.getStatus()).thenReturn(status);
        when(mockResponse.getBody()).thenReturn(json);

        HttpRequestWithBody bodyRequest = mock(HttpRequestWithBody.class);
        PowerMockito.whenNew(HttpRequestWithBody.class).withAnyArguments().thenReturn(bodyRequest);

        RequestBodyEntity entityMock = mock(RequestBodyEntity.class);
        when(bodyRequest.body(any(Object.class))).thenReturn(entityMock);
        when(entityMock.asJson()).thenReturn(mockResponse);

        this.request.setBaseUrl(BASE_URL);

        try {
            this.request.execute(PATH, credentials, method, ResponseBody.class);
        } catch (Exception ex) {
            if (!expectedException.isInstance(ex)) {
                fail();
            }
        }
    }

    private <T extends SdkClientException> void testRequestWithoutBodyNot200(
            HttpMethod method, int status,
            Class<T> expectedException) throws Exception {

        HttpResponse mockResponse = mock(HttpResponse.class);
        when(mockResponse.getStatus()).thenReturn(status);
        when(mockResponse.getBody()).thenReturn(json);

        GetRequest getRequest = mock(GetRequest.class);
        PowerMockito.whenNew(GetRequest.class).withAnyArguments().thenReturn(getRequest);
        when(getRequest.asJson()).thenReturn(mockResponse);

        this.request.setBaseUrl(BASE_URL);

        try {
            this.request.execute(PATH, null, method, ResponseBody.class);
        } catch (Exception ex) {
            if (!expectedException.isInstance(ex)) {
                fail();
            }
        }
    }

    private void testSuccessRequestWithoutBody(HttpMethod method) throws Exception {
        HttpResponse mockResponse = mock(HttpResponse.class);
        when(mockResponse.getStatus()).thenReturn(200);
        when(mockResponse.getBody()).thenReturn(json);

        GetRequest getRequest = mock(GetRequest.class);
        PowerMockito.whenNew(GetRequest.class).withAnyArguments().thenReturn(getRequest);
        when(getRequest.asJson()).thenReturn(mockResponse);

        this.request.setBaseUrl(BASE_URL);
        AuthenticationResponseDTO response = (AuthenticationResponseDTO) this.request
                .execute(PATH, null, method, AuthenticationResponseDTO.class);

        assertEquals(response.getAccessToken(), json.getObject().getString("access_token"));
    }

    private void testSuccessRequestWithBody200(HttpMethod method) throws Exception {
        HttpResponse mockResponse = mock(HttpResponse.class);
        when(mockResponse.getStatus()).thenReturn(200);
        when(mockResponse.getBody()).thenReturn(json);

        HttpRequestWithBody bodyRequest = mock(HttpRequestWithBody.class);
        PowerMockito.whenNew(HttpRequestWithBody.class).withAnyArguments().thenReturn(bodyRequest);

        RequestBodyEntity entityMock = mock(RequestBodyEntity.class);
        when(bodyRequest.body(any(Object.class))).thenReturn(entityMock);
        when(entityMock.asJson()).thenReturn(mockResponse);

        this.request.setBaseUrl(BASE_URL);
        AuthenticationResponseDTO response = (AuthenticationResponseDTO) this.request
                .execute(PATH, credentials, method, AuthenticationResponseDTO.class);

        assertEquals(response.getAccessToken(), json.getObject().getString("access_token"));
    }

}
