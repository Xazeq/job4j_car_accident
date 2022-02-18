package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;

@Service
public class AccidentService {
    private final AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public Collection<Accident> findAllAccidents() {
        return accidentMem.findAllAccidents();
    }

    public void saveAccident(Accident accident) {
        accidentMem.saveAccident(accident);
    }

    public Accident findAccidentById(int id) {
        return accidentMem.findAccidentById(id);
    }

    public void saveAccidentType(AccidentType accidentType) {
        accidentMem.saveAccidentType(accidentType);
    }

    public AccidentType findAccidentTypeById(int id) {
        return accidentMem.findAccidentTypeById(id);
    }

    public Collection<AccidentType> findAllAccidentTypes() {
        return accidentMem.findAllAccidentTypes();
    }

    public void saveRule(Rule rule) {
        accidentMem.saveRule(rule);
    }

    public Rule findRuleById(int id) {
        return accidentMem.findRuleById(id);
    }

    public Collection<Rule> findAllRules() {
        return accidentMem.findAllRules();
    }
}
