package br.com.dataeasy.easysearch.sdk.model;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;

public class MetadataDTO {

    private String name;
    private String label;
    private MetadataType type;

    @SerializedName(value = "flags")
    private Integer flag;
    private LinkedHashMap<String, Transformer> transformers;

    public MetadataDTO() {
    }

    public MetadataDTO(String name, String label, MetadataType type, Flag flag, LinkedHashMap<String, Transformer> transformers) {
        this.name = name;
        this.label = label;
        this.type = type;
        this.flag = flag.getValue();
        this.transformers = transformers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public MetadataType getType() {
        return type;
    }

    public void setType(MetadataType type) {
        this.type = type;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag.getValue();
    }

    public LinkedHashMap<String, Transformer> getTransformers() {
        return transformers;
    }

    public void setTransformers(LinkedHashMap<String, Transformer> transformers) {
        this.transformers = transformers;
    }
}
