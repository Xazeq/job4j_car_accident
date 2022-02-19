package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;

import java.util.Collection;

@Service
public class AccidentService {
    private final AccidentHibernate accidentStore;

    public AccidentService(AccidentHibernate accidentStore) {
        this.accidentStore = accidentStore;
    }

    public Collection<Accident> findAllAccidents() {
        return accidentStore.findAllAccidents();
    }

    public void saveAccident(Accident accident) {
        accidentStore.saveAccident(accident);
    }

    public Accident findAccidentById(int id) {
        return accidentStore.findAccidentById(id);
    }

    public AccidentType findAccidentTypeById(int id) {
        return accidentStore.findAccidentTypeById(id);
    }

    public Collection<AccidentType> findAllAccidentTypes() {
        return accidentStore.findAllAccidentTypes();
    }

    public Rule findRuleById(int id) {
        return accidentStore.findRuleById(id);
    }

    public Collection<Rule> findAllRules() {
        return accidentStore.findAllRules();
    }
}
