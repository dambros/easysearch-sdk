package br.com.dataeasy.easysearch.sdk.exception;

public class InternalServerErrorException extends SdkClientException {

    /**
     * Constructs a new InternalServerErrorException with the specified error message.
     *
     * @param message
     *        Describes the error encountered.
     */
    public InternalServerErrorException(String message) {
        super(message);
    }
}
