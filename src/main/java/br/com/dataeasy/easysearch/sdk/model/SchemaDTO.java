package br.com.dataeasy.easysearch.sdk.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SchemaDTO {

    @SerializedName("_boost")
    private Float boost;

    @SerializedName("_taxonomy")
    private List<List<String>> taxonomies;

    @SerializedName("_metadata")
    private List<MetadataDTO> metadata;

    public SchemaDTO() {
    }

    public SchemaDTO(Float boost, List<List<String>> taxonomies, List<MetadataDTO> metadata) {
        this.boost = boost;
        this.taxonomies = taxonomies;
        this.metadata = metadata;
    }

    public Float getBoost() {
        return boost;
    }

    public void setBoost(Float boost) {
        this.boost = boost;
    }

    public List<List<String>> getTaxonomies() {
        return taxonomies;
    }

    public void setTaxonomies(List<List<String>> taxonomies) {
        this.taxonomies = taxonomies;
    }

    public List<MetadataDTO> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<MetadataDTO> metadata) {
        this.metadata = metadata;
    }
}
