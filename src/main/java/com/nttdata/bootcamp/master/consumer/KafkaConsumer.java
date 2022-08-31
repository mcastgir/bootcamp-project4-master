package com.nttdata.bootcamp.master.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.bootcamp.master.model.document.Person;
import com.nttdata.bootcamp.master.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PersonService personService;

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "group_id")
    public void consume(final String json) {
        try {
            Person person = objectMapper.readValue(json, Person.class);
            personService.insert(person)
                    .subscribe();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}