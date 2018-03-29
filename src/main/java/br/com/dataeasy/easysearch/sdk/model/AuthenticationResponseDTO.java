package br.com.dataeasy.easysearch.sdk.model;


import com.google.gson.annotations.SerializedName;

public class AuthenticationResponseDTO implements ResponseBody {

    @SerializedName("access_token")
    private String accessToken;

    public AuthenticationResponseDTO() {
    }

    public AuthenticationResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
