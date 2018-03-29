package br.com.dataeasy.easysearch.sdk.client;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.com.dataeasy.easysearch.sdk.mock.MockResponseBuilder;
import br.com.dataeasy.easysearch.sdk.mock.RequestMock;
import br.com.dataeasy.easysearch.sdk.model.CredentialsDTO;
import br.com.dataeasy.easysearch.sdk.model.DocumentInsertionDTO;
import br.com.dataeasy.easysearch.sdk.model.DocumentInsertionResponseDTO;
import br.com.dataeasy.easysearch.sdk.model.Flag;
import br.com.dataeasy.easysearch.sdk.model.MetadataDTO;
import br.com.dataeasy.easysearch.sdk.model.MetadataType;
import br.com.dataeasy.easysearch.sdk.model.Path2BinDTO;
import br.com.dataeasy.easysearch.sdk.model.ResolveMode;
import br.com.dataeasy.easysearch.sdk.model.SchemaDTO;
import br.com.dataeasy.easysearch.sdk.model.SettingsDTO;
import br.com.dataeasy.easysearch.sdk.model.Transformer;
import br.com.dataeasy.easysearch.sdk.util.ProcessorUtils;

import static org.junit.Assert.assertEquals;

public class DocumentClientTest {

    private static final String BASE_URL = "http://www.dataeasy.com.br";
    private static final String CLIENT_ID = "clientId";
    private static final String CLIENT_KEY = "clientKey";
    private static final String PATH_PREFIX = "/opt/";

    private EasySearchClient client;
    private CredentialsDTO credentials = new CredentialsDTO(CLIENT_ID, CLIENT_KEY);


    @Before
    public void setUp() {
        this.client = new EasySearchClientBuilder()
                .withBaseUrl(BASE_URL)
                .withCredentials(credentials)
                .withRequestHandler(new RequestMock())
                .build();
    }

    @Test
    public void testInsert() {
        DocumentInsertionResponseDTO expectedResponse = MockResponseBuilder.buildDocumentInsertResponse();

        DocumentClient docClient = this.client.getDocumentClient();
        DocumentInsertionResponseDTO responseNullCollection = docClient.insert(null, createDocList());
        assertEquals(responseNullCollection.getBatchId(), expectedResponse.getBatchId());
        assertEquals(responseNullCollection.getFailed(), expectedResponse.getFailed());
        assertEquals(responseNullCollection.getProcessedDocuments(), expectedResponse.getProcessedDocuments());
        assertEquals(responseNullCollection.getProcessTime(), expectedResponse.getProcessTime());
        assertEquals(responseNullCollection.getSuccessful(), expectedResponse.getSuccessful());
        assertEquals(responseNullCollection.getPath(), "/api/default/document");

        DocumentInsertionResponseDTO response = docClient.insert("user", createDocList());
        assertEquals(response.getBatchId(), expectedResponse.getBatchId());
        assertEquals(response.getFailed(), expectedResponse.getFailed());
        assertEquals(response.getProcessedDocuments(), expectedResponse.getProcessedDocuments());
        assertEquals(response.getProcessTime(), expectedResponse.getProcessTime());
        assertEquals(response.getSuccessful(), expectedResponse.getSuccessful());
        assertEquals(response.getPath(), "/api/user/document");

    }

    private DocumentInsertionDTO createDocList() {
        Path2BinDTO path2BinDTO = new Path2BinDTO();
        path2BinDTO.setCacheMode(false);
        path2BinDTO.setLinkCacheFiles(false);
        path2BinDTO.setResolveMode(ResolveMode.EXTENSION);
        path2BinDTO.setPathPrefix(PATH_PREFIX);

        LinkedHashMap transformers = new LinkedHashMap<String, Transformer>();
        transformers.put("path2bin", path2BinDTO);

        MetadataDTO metadataDTO = new MetadataDTO();
        metadataDTO.setFlag(Flag.INDEXED.getValue() + Flag.STORED.getValue());
        metadataDTO.setLabel("Descrição");
        metadataDTO.setName("description");
        metadataDTO.setType(MetadataType.TEXT);
        metadataDTO.setTransformers(transformers);

        MetadataDTO dateMeta = new MetadataDTO();
        dateMeta.setName("dtCriacao");
        dateMeta.setLabel("Data Criação");
        dateMeta.setType(MetadataType.DATETIME);
        dateMeta.setFlag(Flag.INDEXED);

        MetadataDTO nameMeta = new MetadataDTO();
        nameMeta.setName("name");
        nameMeta.setLabel("Nome");
        nameMeta.setType(MetadataType.TEXT);

        MetadataDTO linkMeta = new MetadataDTO();
        linkMeta.setName("link");
        linkMeta.setLabel("Link");
        linkMeta.setType(MetadataType.TEXT);

        List metadatas = new ArrayList<MetadataDTO>();
        metadatas.add(metadataDTO);
        metadatas.add(dateMeta);
        metadatas.add(nameMeta);
        metadatas.add(linkMeta);

        SchemaDTO schemaDTO = new SchemaDTO(1.0f, new ArrayList<>(), metadatas);

        SettingsDTO settingsDTO = new SettingsDTO();
        settingsDTO.setStopOnFail(false);
        settingsDTO.setGenerateBatchId(true);
        settingsDTO.setHardCommit(false);
        settingsDTO.setReturnIdsOnSuccess(true);
        settingsDTO.setSoftLink(true);

        Map doc1 = new HashMap<String, Object>();
        doc1.put("description", "doc.pdf");
        doc1.put("_id", 1);
        doc1.put("dtCriacao", ProcessorUtils.getDateProcessor("dd/MM/yyyy HH:mm:ss", new Date()));
        doc1.put("name", "NameTest");
        doc1.put("link", BASE_URL);

        List documents = new ArrayList<HashMap<String, Object>>();
        documents.add(doc1);

        DocumentInsertionDTO insertDTO = new DocumentInsertionDTO(schemaDTO, settingsDTO, documents);

        return insertDTO;


    }
}
