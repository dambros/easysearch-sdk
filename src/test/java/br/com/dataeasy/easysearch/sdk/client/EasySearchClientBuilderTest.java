package br.com.dataeasy.easysearch.sdk.client;


import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import br.com.dataeasy.easysearch.sdk.http.Request;
import br.com.dataeasy.easysearch.sdk.http.RequestHandler;
import br.com.dataeasy.easysearch.sdk.mock.RequestMock;
import br.com.dataeasy.easysearch.sdk.model.CredentialsDTO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EasySearchClientBuilderTest {

    private static final String BASE_URL = "http://www.dataeasy.com.br";
    private static final String CLIENT_ID = "clientId";
    private static final String CLIENT_KEY = "clientKey";

    @Test
    public void testWithBaseUrl() {
        EasySearchClientBuilder esBuilder = new EasySearchClientBuilder();
        esBuilder.withBaseUrl(BASE_URL);
        assertNotNull(esBuilder.getBaseUrl());
        assertEquals(BASE_URL, esBuilder.getBaseUrl());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithBaseUrlNullUrl() {
        EasySearchClientBuilder esBuilder = new EasySearchClientBuilder();
        esBuilder.withBaseUrl(null);
    }

    @Test
    public void testWithCredentials() {
        CredentialsDTO credentials = new CredentialsDTO(CLIENT_ID, CLIENT_KEY);
        EasySearchClientBuilder esBuilder = new EasySearchClientBuilder().withCredentials(credentials);
        assertNotNull(esBuilder.getCredentials());
        assertEquals(esBuilder.getCredentials().getClientId(), CLIENT_ID);
        assertEquals(esBuilder.getCredentials().getClientKey(), CLIENT_KEY);
    }

    @Test
    public void testWithRequestHandler() {
        RequestHandler rh = Mockito.mock(Request.class);
        EasySearchClientBuilder esBuilder = new EasySearchClientBuilder().withRequestHandler(rh);
        assertNotNull(esBuilder.getRequestHandler());
        assertEquals(esBuilder.getRequestHandler(), rh);
    }

    @Test
    public void testWithParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("maxTotal", "10");
        params.put("maxPerRoute", "10");

        EasySearchClientBuilder esBuilder = new EasySearchClientBuilder().withParams(params);
        assertNotNull(esBuilder.getParams());
        assertEquals(esBuilder.getParams(), params);
    }

    @Test
    public void testBuild() throws Exception {
        CredentialsDTO credentials = new CredentialsDTO(CLIENT_ID, CLIENT_KEY);

        EasySearchClient esClient = new EasySearchClientBuilder()
                .withBaseUrl(BASE_URL)
                .withCredentials(credentials)
                .withRequestHandler(new RequestMock())
                .build();

        assertNotNull(esClient);
        assertNotNull(esClient.getAccessToken());

    }
}
