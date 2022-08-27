package com.nttdata.bootcamp.master.controller;

import com.nttdata.bootcamp.master.model.document.DocumentType;
import com.nttdata.bootcamp.master.model.document.Person;
import com.nttdata.bootcamp.master.service.DocumentTypeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@WebFluxTest(controllers = DocumentTypeController.class)
class DocumentTypeControllerTest {

    private static final String URI_DOCUMENT_TYPE = "/api/documenttypes";

    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private DocumentTypeService documentTypeService;

    @Test
    void create() {
        /* Builder Object */
        DocumentType documentType = DocumentType.builder()
                .id("62ef58f107695c058093a65f")
                .code("DNI")
                .name("DOCUMENTO NACIONAL DE IDENTIDAD")
                .description("DOCUMENTO NACIONAL DE IDENTIDAD")
                .state(true)
                .build();
        /* Mockito */
        Mockito.when(documentTypeService.insert(documentType))
                .thenReturn(Mono.just(documentType));
        /* WebTestClient */
        webTestClient.post()
                .uri(URI_DOCUMENT_TYPE)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(documentType), DocumentType.class)
                .exchange()
                .expectStatus().isOk();
        /* Mockito Verify */
        Mockito.verify(documentTypeService, Mockito.times(1)).insert(documentType);
    }

    @Test
    void update() {
        /* Builder Object */
        DocumentType documentType = DocumentType.builder()
                .id("62ef58f107695c058093a65f")
                .code("DNI")
                .name("DOCUMENTO NACIONAL DE IDENTIDAD")
                .description("DOCUMENTO NACIONAL DE IDENTIDAD")
                .state(true)
                .build();
        /* Mockito */
        Mockito.when(documentTypeService.update(documentType))
                .thenReturn(Mono.just(documentType));
        /* WebTestClient */
        webTestClient.put()
                .uri(URI_DOCUMENT_TYPE)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(documentType), DocumentType.class)
                .exchange()
                .expectStatus().isOk();
        /* Mockito Verify */
        Mockito.verify(documentTypeService, Mockito.times(1)).update(documentType);
    }

    @Test
    void delete() {
        /* Builder Object */
        DocumentType documentType = DocumentType.builder()
                .id("62ef58f107695c058093a65f")
                .build();
        /* Mockito */
        Mockito.when(documentTypeService.delete(documentType.getId()))
                .thenReturn(Mono.empty());
        /* WebTestClient */
        webTestClient.delete()
                .uri(URI_DOCUMENT_TYPE.concat("/").concat(documentType.getId()))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
        /* Mockito Verify */
        Mockito.verify(documentTypeService, Mockito.times(1)).delete(documentType.getId());
    }

    @Test
    void find() {
        /* Builder Object */
        DocumentType documentType = DocumentType.builder()
                .id("62ef58f107695c058093a65f")
                .code("DNI")
                .name("DOCUMENTO NACIONAL DE IDENTIDAD")
                .description("DOCUMENTO NACIONAL DE IDENTIDAD")
                .state(true)
                .build();
        /* Mockito */
        Mockito.when(documentTypeService.find(documentType.getId()))
                .thenReturn(Mono.just(documentType));
        /* WebTestClient */
        webTestClient.get()
                .uri(URI_DOCUMENT_TYPE.concat("/").concat(documentType.getId()))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
        /* Mockito Verify */
        Mockito.verify(documentTypeService, Mockito.times(1)).find(documentType.getId());
    }

    @Test
    void findByCode() {
        /* Builder Object */
        DocumentType documentType = DocumentType.builder()
                .id("62ef58f107695c058093a65f")
                .code("DNI")
                .name("DOCUMENTO NACIONAL DE IDENTIDAD")
                .description("DOCUMENTO NACIONAL DE IDENTIDAD")
                .state(true)
                .build();
        /* Mockito */
        Mockito.when(documentTypeService.findByCode(documentType.getCode()))
                .thenReturn(Mono.just(documentType));
        /* WebTestClient */
        webTestClient.get()
                .uri(URI_DOCUMENT_TYPE.concat("/findByCode/").concat(documentType.getCode()))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
        /* Mockito Verify */
        Mockito.verify(documentTypeService, Mockito.times(1)).findByCode(documentType.getCode());
    }

    @Test
    void findAll() {
        /* Builder Object */
        List<DocumentType> types = Arrays.asList(DocumentType.builder()
                .id("62ef58f107695c058093a65f")
                .code("DNI")
                .name("DOCUMENTO NACIONAL DE IDENTIDAD")
                .description("DOCUMENTO NACIONAL DE IDENTIDAD")
                .state(true)
                .build());
        /* Mockito */
        Mockito.when(documentTypeService.findAll())
                .thenReturn(Flux.fromIterable(types));
        /* WebTestClient */
        webTestClient.get()
                .uri(URI_DOCUMENT_TYPE)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
        /* Mockito Verify */
        Mockito.verify(documentTypeService, Mockito.times(1)).findAll();
    }

}