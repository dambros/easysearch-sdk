package br.com.dataeasy.easysearch.sdk.exception;

public class BadRequestException extends SdkClientException {

    /**
     * Constructs a new BadRequestException with the specified error message.
     *
     * @param message
     *        Describes the error encountered.
     */
    public BadRequestException(String message) {
        super(message);
    }
}
