package br.com.dataeasy.easysearch.sdk.model;


public class Path2BinDTO implements Transformer {

    private String pathPrefix;
    private boolean cacheMode;
    private boolean linkCacheFiles;
    private ResolveMode resolveMode;

    public Path2BinDTO() {
    }

    public Path2BinDTO(String pathPrefix, boolean cacheMode, boolean linkCacheFiles, ResolveMode resolveMode) {
        this.pathPrefix = pathPrefix;
        this.cacheMode = cacheMode;
        this.linkCacheFiles = linkCacheFiles;
        this.resolveMode = resolveMode;
    }

    public String getPathPrefix() {
        return pathPrefix;
    }

    public void setPathPrefix(String pathPrefix) {
        this.pathPrefix = pathPrefix;
    }

    public boolean isCacheMode() {
        return cacheMode;
    }

    public void setCacheMode(boolean cacheMode) {
        this.cacheMode = cacheMode;
    }

    public boolean isLinkCacheFiles() {
        return linkCacheFiles;
    }

    public void setLinkCacheFiles(boolean linkCacheFiles) {
        this.linkCacheFiles = linkCacheFiles;
    }

    public ResolveMode getResolveMode() {
        return resolveMode;
    }

    public void setResolveMode(ResolveMode resolveMode) {
        this.resolveMode = resolveMode;
    }
}
