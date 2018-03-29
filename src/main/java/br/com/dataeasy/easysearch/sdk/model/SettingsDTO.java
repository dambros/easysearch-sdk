package br.com.dataeasy.easysearch.sdk.model;

public class SettingsDTO {

    private boolean stopOnFail;
    private boolean generateBatchId;
    private boolean returnIdsOnSuccess;
    private boolean hardCommit;
    private boolean softLink;

    public SettingsDTO() {
    }

    public SettingsDTO(boolean stopOnFail, boolean generateBatchId, boolean returnIdsOnSuccess, boolean hardCommit, boolean softLink) {
        this.stopOnFail = stopOnFail;
        this.generateBatchId = generateBatchId;
        this.returnIdsOnSuccess = returnIdsOnSuccess;
        this.hardCommit = hardCommit;
        this.softLink = softLink;
    }

    public boolean isStopOnFail() {
        return stopOnFail;
    }

    public void setStopOnFail(boolean stopOnFail) {
        this.stopOnFail = stopOnFail;
    }

    public boolean isGenerateBatchId() {
        return generateBatchId;
    }

    public void setGenerateBatchId(boolean generateBatchId) {
        this.generateBatchId = generateBatchId;
    }

    public boolean isReturnIdsOnSuccess() {
        return returnIdsOnSuccess;
    }

    public void setReturnIdsOnSuccess(boolean returnIdsOnSuccess) {
        this.returnIdsOnSuccess = returnIdsOnSuccess;
    }

    public boolean isHardCommit() {
        return hardCommit;
    }

    public void setHardCommit(boolean hardCommit) {
        this.hardCommit = hardCommit;
    }

    public boolean isSoftLink() {
        return softLink;
    }

    public void setSoftLink(boolean softLink) {
        this.softLink = softLink;
    }
}
