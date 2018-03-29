package br.com.dataeasy.easysearch.sdk.model;

import java.util.List;

public class DocumentDeletionDTO implements RequestBody {

    private String query;
    private List<String> ids;
    private String batchId;
    private boolean deleteAll;
    private boolean hardCommit;

    public DocumentDeletionDTO() {
    }

    public DocumentDeletionDTO(String query, List<String> ids, String batchId, boolean deleteAll, boolean hardCommit) {
        this.query = query;
        this.ids = ids;
        this.batchId = batchId;
        this.deleteAll = deleteAll;
        this.hardCommit = hardCommit;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public boolean isDeleteAll() {
        return deleteAll;
    }

    public void setDeleteAll(boolean deleteAll) {
        this.deleteAll = deleteAll;
    }

    public boolean isHardCommit() {
        return hardCommit;
    }

    public void setHardCommit(boolean hardCommit) {
        this.hardCommit = hardCommit;
    }
}
