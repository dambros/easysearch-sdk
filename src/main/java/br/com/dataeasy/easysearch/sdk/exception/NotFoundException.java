package br.com.dataeasy.easysearch.sdk.exception;

public class NotFoundException extends SdkClientException {

    /**
     * Constructs a new NotFoundException with the specified error message.
     *
     * @param message
     *        Describes the error encountered.
     */
    public NotFoundException(String message) {
        super(message);
    }
}
