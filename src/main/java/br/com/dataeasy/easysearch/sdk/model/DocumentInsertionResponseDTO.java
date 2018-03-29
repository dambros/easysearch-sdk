package br.com.dataeasy.easysearch.sdk.model;

import java.util.List;
import java.util.Map;

public class DocumentInsertionResponseDTO implements ResponseBody {

    private Long processTime;
    private Integer processedDocuments;
    private String batchId;
    private Map<Long, String> successful;
    private List<Long> failed;
    private boolean successfullyCommitted;
    private transient String path;

    public DocumentInsertionResponseDTO() {
    }

    public DocumentInsertionResponseDTO(Long processTime, Integer processedDocuments, String batchId, Map<Long, String> successful, List<Long> failed, boolean successfullyCommitted) {
        this.processTime = processTime;
        this.processedDocuments = processedDocuments;
        this.batchId = batchId;
        this.successful = successful;
        this.failed = failed;
        this.successfullyCommitted = successfullyCommitted;
    }

    public Long getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Long processTime) {
        this.processTime = processTime;
    }

    public Integer getProcessedDocuments() {
        return processedDocuments;
    }

    public void setProcessedDocuments(Integer processedDocuments) {
        this.processedDocuments = processedDocuments;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public Map<Long, String> getSuccessful() {
        return successful;
    }

    public void setSuccessful(Map<Long, String> successful) {
        this.successful = successful;
    }

    public List<Long> getFailed() {
        return failed;
    }

    public void setFailed(List<Long> failed) {
        this.failed = failed;
    }

    public boolean isSuccessfullyCommitted() {
        return successfullyCommitted;
    }

    public void setSuccessfullyCommitted(boolean successfullyCommitted) {
        this.successfullyCommitted = successfullyCommitted;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
