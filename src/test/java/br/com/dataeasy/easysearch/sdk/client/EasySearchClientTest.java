package br.com.dataeasy.easysearch.sdk.client;

import org.junit.Test;

import br.com.dataeasy.easysearch.sdk.mock.RequestMock;
import br.com.dataeasy.easysearch.sdk.model.CredentialsDTO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EasySearchClientTest {

    private static final String BASE_URL = "http://www.dataeasy.com.br";
    private static final String CLIENT_ID = "clientId";
    private static final String CLIENT_KEY = "clientKey";

    @Test
    public void testAuthenticate() throws Exception {

        CredentialsDTO credentials = new CredentialsDTO(CLIENT_ID, CLIENT_KEY);

        EasySearchClient client = new EasySearchClientBuilder()
                .withBaseUrl(BASE_URL)
                .withCredentials(credentials)
                .withRequestHandler(new RequestMock())
                .build();

        //client.authenticate() is called during build() call
        assertNotNull(client.getAccessToken());
        assertEquals(client.getClientId(), CLIENT_ID);

    }

    @Test(expected = IllegalArgumentException.class)
     public void testAuthenticateNullBaseURL() {
        new EasySearchClientBuilder().build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAuthenticateNullCredentials() {
        new EasySearchClientBuilder()
                .withBaseUrl(BASE_URL)
                .build();
    }
}
