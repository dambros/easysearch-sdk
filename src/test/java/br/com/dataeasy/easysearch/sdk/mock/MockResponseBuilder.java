package br.com.dataeasy.easysearch.sdk.mock;


import java.util.HashMap;
import java.util.Map;

import br.com.dataeasy.easysearch.sdk.model.DocumentDeletionResponseDTO;
import br.com.dataeasy.easysearch.sdk.model.DocumentInsertionResponseDTO;

public class MockResponseBuilder {

    public static DocumentInsertionResponseDTO buildDocumentInsertResponse() {
        DocumentInsertionResponseDTO responseDTO = new DocumentInsertionResponseDTO();
        responseDTO.setBatchId("-53edffff.1522350949704.10");
        responseDTO.setFailed(null);
        responseDTO.setProcessedDocuments(1);
        responseDTO.setProcessTime(277L);
        responseDTO.setSuccessfullyCommitted(true);

        Map map = new HashMap<Long, String>();
        map.put("1", 1);
        responseDTO.setSuccessful(map);

        return responseDTO;
    }

    public static DocumentDeletionResponseDTO buildDocumentDeletionResponse() {
        DocumentDeletionResponseDTO dto = new DocumentDeletionResponseDTO();
        dto.setDocumentsLeft(0);
        dto.setFailed(0);
        dto.setSuccessful(1);
        return dto;
    }

}


