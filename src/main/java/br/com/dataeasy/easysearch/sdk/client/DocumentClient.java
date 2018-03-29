package br.com.dataeasy.easysearch.sdk.client;

import br.com.dataeasy.easysearch.sdk.http.HttpMethod;
import br.com.dataeasy.easysearch.sdk.http.RequestHandler;
import br.com.dataeasy.easysearch.sdk.model.DocumentInsertionDTO;
import br.com.dataeasy.easysearch.sdk.model.DocumentInsertionResponseDTO;
import br.com.dataeasy.easysearch.sdk.util.StringUtils;

public class DocumentClient {

    public static final String DEFAULT_COLLECTION = "default";
    private static final String INSERT_DOCUMENT_PATH = "/api/%s/document";

    private RequestHandler requestHandler;

    DocumentClient(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    public DocumentInsertionResponseDTO insert(String collection, DocumentInsertionDTO documentInsertion) {
        collection = StringUtils.isNullOrEmpty(collection) ? DEFAULT_COLLECTION : collection;
        String path = String.format(INSERT_DOCUMENT_PATH, collection);

        DocumentInsertionResponseDTO response = (DocumentInsertionResponseDTO)
                this.requestHandler.execute(
                        path,
                        documentInsertion,
                        HttpMethod.POST,
                        DocumentInsertionResponseDTO.class
                );

        response.setPath(path);
        return response;
    }
}
