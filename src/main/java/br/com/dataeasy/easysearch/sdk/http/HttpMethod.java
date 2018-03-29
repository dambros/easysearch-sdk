package br.com.dataeasy.easysearch.sdk.http;


import br.com.dataeasy.easysearch.sdk.util.StringUtils;

public enum HttpMethod {

    GET,
    POST,
    PUT,
    DELETE,
    HEAD,
    OPTIONS,
    ;

    /**
     * @param value Raw string representing value of enum
     * @return HttpMethod enum or null if value is not present.
     * @throws IllegalArgumentException If value does not represent a known enum value.
     */
    public static HttpMethod fromValue(String value) {
        if (StringUtils.isNullOrEmpty(value)) {
            return null;
        }

        final String upperCaseValue = StringUtils.upperCase(value);
        for (HttpMethod method : values()) {
            if (method.name().equals(upperCaseValue)) {
                return method;
            }
        }
        throw new IllegalArgumentException("Unsupported HTTP method name " + value);
    }
}
