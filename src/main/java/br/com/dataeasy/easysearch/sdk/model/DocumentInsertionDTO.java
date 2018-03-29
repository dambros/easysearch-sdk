package br.com.dataeasy.easysearch.sdk.model;


import java.util.HashMap;
import java.util.List;

public class DocumentInsertionDTO implements RequestBody {

    private SchemaDTO schema;
    private SettingsDTO	settings;
    private List<HashMap<String, Object>> documents;

    public DocumentInsertionDTO() {
    }

    public DocumentInsertionDTO(SchemaDTO schema, SettingsDTO settings, List<HashMap<String, Object>> documents) {
        this.schema = schema;
        this.settings = settings;
        this.documents = documents;
    }

    public SchemaDTO getSchema() {
        return schema;
    }

    public void setSchema(SchemaDTO schema) {
        this.schema = schema;
    }

    public SettingsDTO getSettings() {
        return settings;
    }

    public void setSettings(SettingsDTO settings) {
        this.settings = settings;
    }

    public List<HashMap<String, Object>> getDocuments() {
        return documents;
    }

    public void setDocuments(List<HashMap<String, Object>> documents) {
        this.documents = documents;
    }
}
