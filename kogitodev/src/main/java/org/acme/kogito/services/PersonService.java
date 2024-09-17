package org.acme.kogito.services;

import org.acme.kogito.model.Person;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonService {

    @Autowired
    private KieContainer kieContainer;

    @GetMapping("/persons")
    public Person handlePerson(@RequestParam int age) {
        Person person = new Person();
        person.setAge(age);

        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(person);
        kieSession.fireAllRules();
        kieSession.dispose();

        return person;
    }
}
