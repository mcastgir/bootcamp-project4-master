/**
 * Resumen.
 * Objeto                   : DocumentTypeController.java
 * Descripción              : Clase de controladora para invocar a métodos CRUD con rest api.
 * Fecha de Creación        : 04/08/2022.
 * Proyecto de Creación     : Bootcamp-01.
 * Autor                    : Marvin Castro.
 * ---------------------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                   Fecha             Nombre                  Descripción
 * ---------------------------------------------------------------------------------------------------------------------------
 * Bootcamp-01              05/08/2022        Oscar Candela           Realizar la creación de un método nuevo.
 */

package com.nttdata.bootcamp.master.controller;

import com.nttdata.bootcamp.master.model.document.DocumentType;
import com.nttdata.bootcamp.master.service.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.ws.rs.core.Response;

/**
 * Clase de controladora para invocar a métodos CRUD con rest api.
 */
@RestController
@RequestMapping("/api/documenttypes")
public class DocumentTypeController {

    /** Declaración de la clase service */
    @Autowired
    private DocumentTypeService documentTypeService;

    /**
     * Método que realiza la acción insertar datos del document
     * @return Mono retorna el DocumentType, tipo Mono
     */
    @PostMapping
    public Mono<ResponseEntity<DocumentType>> create(@RequestBody DocumentType documentType){
        return this.documentTypeService.insert(documentType)
                .map(d -> new ResponseEntity<>(d, HttpStatus.OK));
    }

    /**
     * Método que realiza la acción actualizar datos del document
     * @return Mono retorna el DocumentType, tipo Mono
     */
    @PutMapping
    public Mono<ResponseEntity<DocumentType>> update(@RequestBody DocumentType documentType){
        return this.documentTypeService.update(documentType)
                .map(d -> new ResponseEntity<>(d, HttpStatus.OK));
    }

    /**
     * Método que realiza la acción borrar datos del document
     * @return Mono retorna el Void, tipo Mono
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id){
        return this.documentTypeService.delete(id)
                .map(v -> new ResponseEntity<>(v, HttpStatus.OK));
    }

    /**
     * Método que realiza la acción buscar datos por id del document
     * @return Mono retorna el DocumentType, tipo String
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<DocumentType>> find(@PathVariable String id){
        return this.documentTypeService.find(id)
                .map(documentType -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(documentType));
    }

    /**
     * Método que realiza la acción buscar datos por código del document
     * @return Mono retorna el DocumentType, tipo String
     */
    @GetMapping("/findByCode/{code}")
    public Mono<ResponseEntity<DocumentType>> findByCode(@PathVariable String code){
        return this.documentTypeService.findByCode(code)
                .map(documentType -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(documentType));
    }

    /**
     * Método que realiza la acción buscar todos los datos del document
     * @return Flux retorna el DocumentType, tipo Flux
     */
    @GetMapping
    public Mono<ResponseEntity<Flux<DocumentType>>> findAll() {
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(this.documentTypeService.findAll())
        );
    }

}
