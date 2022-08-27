package com.nttdata.bootcamp.master.controller;

import com.nttdata.bootcamp.master.model.document.Person;
import com.nttdata.bootcamp.master.service.PersonService;
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

@WebFluxTest(controllers = PersonController.class)
class PersonControllerTest {

    private static final String URI_PERSON = "/api/persons";

    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private PersonService personService;

    @Test
    void create() {
        /* Builder Object */
        Person person = Person.builder()
                .id("62eff17ff7606e785da5fb58")
                .code("PERS-0001")
                .name("Matheus")
                .lastName("Murdock")
                .codeDocumentType("DNI")
                .documentNumber("45236897")
                .phoneNumber("951234567")
                .email("mmurdock@gmail.com")
                .build();
        /* Mockito When */
        Mockito.when(personService.insert(person))
                .thenReturn(Mono.just(person));
        /* WebTestClient */
        webTestClient.post()
                .uri(URI_PERSON)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(person), Person.class)
                .exchange()
                .expectStatus().isOk();
        /* Mockito Verify */
        Mockito.verify(personService, Mockito.times(1)).insert(person);
    }

    @Test
    void update() {
        /* Builder Object */
        Person person = Person.builder()
                .id("62eff17ff7606e785da5fb58")
                .code("PERS-0001")
                .name("Matheus")
                .lastName("Murdock")
                .codeDocumentType("DNI")
                .documentNumber("45236897")
                .phoneNumber("951234567")
                .email("mmurdock@gmail.com")
                .build();
        /* Mockito */
        Mockito.when(personService.update(person))
                .thenReturn(Mono.just(person));
        /* WebTestClient */
        webTestClient.put()
                .uri(URI_PERSON)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(person), Person.class)
                .exchange()
                .expectStatus().isOk();
        /* Mockito Verify */
        Mockito.verify(personService, Mockito.times(1)).update(person);
    }

    @Test
    void delete() {
        /* Builder Object */
        Person person = Person.builder()
                .id("62eff17ff7606e785da5fb58")
                .build();
        /* Mockito */
        Mockito.when(personService.delete(person.getId()))
                .thenReturn(Mono.empty());
        /* WebTestClient */
        webTestClient.delete()
                .uri(URI_PERSON.concat("/").concat(person.getId()))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
        /* Mockito Verify */
        Mockito.verify(personService, Mockito.times(1)).delete(person.getId());
    }

    @Test
    void find() {
        /* Builder Object */
        Person person = Person.builder()
                .id("62eff17ff7606e785da5fb58")
                .code("PERS-0001")
                .name("Matheus")
                .lastName("Murdock")
                .codeDocumentType("DNI")
                .documentNumber("45236897")
                .phoneNumber("951234567")
                .email("mmurdock@gmail.com")
                .build();
        /* Mockito */
        Mockito.when(personService.find(person.getId()))
                .thenReturn(Mono.just(person));
        /* WebTestClient */
        webTestClient.get()
                .uri(URI_PERSON.concat("/").concat(person.getId()))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
        /* Mockito Verify */
        Mockito.verify(personService, Mockito.times(1)).find(person.getId());
    }

    @Test
    void findByCode() {
        /* Builder Object */
        Person person = Person.builder()
                .id("62eff17ff7606e785da5fb58")
                .code("PERS-0001")
                .name("Matheus")
                .lastName("Murdock")
                .codeDocumentType("DNI")
                .documentNumber("45236897")
                .phoneNumber("951234567")
                .email("mmurdock@gmail.com")
                .build();
        /* Mockito */
        Mockito.when(personService.findByCode(person.getCode()))
                .thenReturn(Mono.just(person));
        /* WebTestClient */
        webTestClient.get()
                .uri(URI_PERSON.concat("/findByCode/").concat(person.getCode()))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
        /* Mockito Verify */
        Mockito.verify(personService, Mockito.times(1)).findByCode(person.getCode());
    }

    @Test
    void findAll() {
        /* Builder Object */
        List<Person> persons = Arrays.asList(Person.builder()
                .id("62eff17ff7606e785da5fb58")
                .code("PERS-0001")
                .name("Matheus")
                .lastName("Murdock")
                .codeDocumentType("DNI")
                .documentNumber("45236897")
                .phoneNumber("951234567")
                .email("mmurdock@gmail.com")
                .build());
        /* Mockito */
        Mockito.when(personService.findAll())
                .thenReturn(Flux.fromIterable(persons));
        /* WebTestClient */
        webTestClient.get()
                .uri(URI_PERSON)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
        /* Mockito Verify */
        Mockito.verify(personService, Mockito.times(1)).findAll();
    }

}