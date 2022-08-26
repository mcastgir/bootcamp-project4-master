/**
 * Resumen.
 * Objeto                   : DocumentTypeServiceImpl.java
 * Descripción              : Clase para los métodos de la implementación de servicio de la cuenta.
 * Fecha de Creación        : 04/08/2022.
 * Proyecto de Creación     : Bootcamp-01.
 * Autor                    : Marvin Castro.
 * ---------------------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                   Fecha             Nombre                  Descripción
 * ---------------------------------------------------------------------------------------------------------------------------
 * Bootcamp-01              05/08/2022        Oscar Candela           Realizar la creación de un método nuevo.
 */

package com.nttdata.bootcamp.master.service.impl;

import com.nttdata.bootcamp.master.model.dao.DocumentTypeDao;
import com.nttdata.bootcamp.master.model.document.DocumentType;
import com.nttdata.bootcamp.master.service.DocumentTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Clase para los métodos de la implementación de servicio del tipo de documento.
 */
@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

    /** Declaración de la variable de log */
    private static final Logger log = LoggerFactory.getLogger(DocumentTypeServiceImpl.class);

    /** Declaración de la clase dao */
    @Autowired
    private DocumentTypeDao documentTypeDao;

    /**
     * Método que realiza la acción insertar datos del document
     * @return Mono retorna el DocumentType, tipo Mono
     */
    @Override
    public Mono<DocumentType> insert(DocumentType documentType) {
        return documentTypeDao.save(documentType)
                .doFirst(() -> log.info("Begin Insert DocumentType"))
                .doOnNext(d -> log.info(d.toString()))
                .doAfterTerminate(() -> log.info("Finish Insert DocumentType"));
    }

    /**
     * Método que realiza la acción actualizar datos del document
     * @return Mono retorna el DocumentType, tipo Mono
     */
    @Override
    public Mono<DocumentType> update(DocumentType documentType) {
        return documentTypeDao.findById(documentType.getId())
                .doFirst(() -> log.info("Begin Update DocumentType"))
                .map(t -> documentType)
                .flatMap(this.documentTypeDao::save)
                .doOnNext(d -> log.info(d.toString()))
                .doAfterTerminate(() -> log.info("Finish Update DocumentType"));
    }

    /**
     * Método que realiza la acción borrar datos del document
     * @return Mono retorna el Void, tipo Mono
     */
    @Override
    public Mono<Void> delete(String id) {
        return documentTypeDao.deleteById(id)
                .doFirst(() -> log.info("Begin Delete DocumentType"))
                .doOnNext(d -> log.info(d.toString()))
                .doAfterTerminate(() -> log.info("Finish Delete DocumentType"));
    }

    /**
     * Método que realiza la acción buscar datos por id del document
     * @return Mono retorna el DocumentType, tipo String
     */
    @Override
    public Mono<DocumentType> find(String id) {
        return documentTypeDao.findById(id)
                .doFirst(() -> log.info("Begin Find DocumentType"))
                .doOnNext(d -> log.info(d.toString()))
                .doAfterTerminate(() -> log.info("Finish Find DocumentType"));
    }

    /**
     * Método que realiza la acción buscar datos por código del document
     * @return Mono retorna el DocumentType, tipo String
     */
    @Override
    public Mono<DocumentType> findByCode(String code) {
        return documentTypeDao.findByCode(code)
                .doFirst(() -> log.info("Begin FindByCode DocumentType"))
                .doOnNext(d -> log.info(d.toString()))
                .doAfterTerminate(() -> log.info("Finish FindByCode DocumentType"));
    }

    /**
     * Método que realiza la acción buscar todos los datos del document
     * @return Mono retorna el DocumentType, tipo String
     */
    @Override
    public Flux<DocumentType> findAll() {
        return documentTypeDao.findAll()
                .doFirst(() -> log.info("Begin FindAll DocumentType"))
                .doOnNext(d -> log.info(d.toString()))
                .doAfterTerminate(() -> log.info("Finish FindAll DocumentType"));
    }

}
