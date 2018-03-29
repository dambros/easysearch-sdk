package br.com.dataeasy.easysearch.sdk.exception;

import br.com.dataeasy.easysearch.sdk.model.ErrorResponseDTO;

public class SdkClientException extends RuntimeException {

    public SdkClientException(String message) {
        super(message);
    }

    public SdkClientException(Throwable ex) {
        super(ex);
    }

    public SdkClientException(ErrorResponseDTO response) {
        this(response.getMessage());
    }

    public SdkClientException(String message, Throwable e) {
        super(message, e);
    }
}
