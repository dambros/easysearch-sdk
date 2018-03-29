package br.com.dataeasy.easysearch.sdk.client;

import br.com.dataeasy.easysearch.sdk.http.HttpMethod;
import br.com.dataeasy.easysearch.sdk.http.RequestHandler;
import br.com.dataeasy.easysearch.sdk.model.DocumentDeletionDTO;
import br.com.dataeasy.easysearch.sdk.model.DocumentDeletionResponseDTO;
import br.com.dataeasy.easysearch.sdk.model.DocumentInsertionDTO;
import br.com.dataeasy.easysearch.sdk.model.DocumentInsertionResponseDTO;
import br.com.dataeasy.easysearch.sdk.util.StringUtils;

public class DocumentClient {

    public static final String DEFAULT_COLLECTION = "default";
    private static final String INSERT_DOCUMENT_PATH = "/api/%s/document";
    private static final String REMOVE_DOCUMENT_PATH = "/api/%s/document";
    private static final String REMOVE_SINGLE_DOCUMENT_PATH = "/api/%s/document/%s";

    private RequestHandler requestHandler;

    DocumentClient(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    /**
     * Allows the insertion of one or multiple documents into a given collection
     *
     * @param collection        name of the collection where the documents should be inserted. This
     *                          value can be null, the 'default' collection will be used instead
     * @param documentInsertion list of parameters required to insert a document
     * @return object containing the result of the given request. Within its body it is possible to
     * see the list of documents that were successfully inserted and failed
     */
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

    /**
     * Delete a list of documents from the given collection
     *
     * @param collection       name of the collection from where the documents should be deleted.
     *                         This value can be null, the 'default' collection will be used
     *                         instead
     * @param documentDeletion list of parameters required to delete a list of documents
     * @return object with the number of successfully, failed and remaining documents on the
     * collection
     */
    public DocumentDeletionResponseDTO delete(String collection, DocumentDeletionDTO documentDeletion) {
        collection = StringUtils.isNullOrEmpty(collection) ? DEFAULT_COLLECTION : collection;
        String path = String.format(REMOVE_DOCUMENT_PATH, collection);

        DocumentDeletionResponseDTO response = (DocumentDeletionResponseDTO)
                this.requestHandler.execute(
                        path,
                        documentDeletion,
                        HttpMethod.DELETE,
                        DocumentDeletionResponseDTO.class
                );

        response.setPath(path);
        return response;
    }

    /**
     * Delete a specific document from the given collection
     *
     * @param collection name of the collection from where the documents should be deleted. This
     *                   value can be null, the 'default' collection will be used instead
     * @param id         identifier of the document being removed. Cannot be null
     * @return object with the number of successfully, failed and remaining documents on the
     * collection
     */
    public DocumentDeletionResponseDTO delete(String collection, String id) {

        if (StringUtils.isNullOrEmpty(id)) {
            throw new IllegalArgumentException("id cannot be null");
        }

        collection = StringUtils.isNullOrEmpty(collection) ? DEFAULT_COLLECTION : collection;
        String path = String.format(REMOVE_SINGLE_DOCUMENT_PATH, collection, id);

        DocumentDeletionResponseDTO response = (DocumentDeletionResponseDTO)
                this.requestHandler.execute(
                        path,
                        null,
                        HttpMethod.DELETE,
                        DocumentDeletionResponseDTO.class
                );

        response.setPath(path);
        return response;
    }

}
