package br.com.dataeasy.easysearch.sdk.exception;

public class UnauthorizedException extends SdkClientException {

    /**
     * Constructs a new UnauthorizedException with the specified error message.
     *
     * @param message
     *        Describes the error encountered.
     */
    public UnauthorizedException(String message) {
        super(message);
    }
}
