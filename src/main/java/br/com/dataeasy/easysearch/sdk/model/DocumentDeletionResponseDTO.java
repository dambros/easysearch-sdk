package br.com.dataeasy.easysearch.sdk.model;


public class DocumentDeletionResponseDTO implements ResponseBody {

    private int successful;
    private int failed;
    private int documentsLeft;
    private transient String path;

    public DocumentDeletionResponseDTO() {
    }

    public DocumentDeletionResponseDTO(int successful, int failed, int documentsLeft) {
        this.successful = successful;
        this.failed = failed;
        this.documentsLeft = documentsLeft;
    }

    public int getSuccessful() {
        return successful;
    }

    public void setSuccessful(int successful) {
        this.successful = successful;
    }

    public int getFailed() {
        return failed;
    }

    public void setFailed(int failed) {
        this.failed = failed;
    }

    public int getDocumentsLeft() {
        return documentsLeft;
    }

    public void setDocumentsLeft(int documentsLeft) {
        this.documentsLeft = documentsLeft;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
