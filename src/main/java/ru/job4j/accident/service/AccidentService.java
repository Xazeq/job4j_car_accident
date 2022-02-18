package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.Collection;

@Service
public class AccidentService {
    private final AccidentJdbcTemplate accidentJdbcTemplate;

    public AccidentService(AccidentJdbcTemplate accidentJdbcTemplate) {
        this.accidentJdbcTemplate = accidentJdbcTemplate;
    }

    public Collection<Accident> findAllAccidents() {
        return accidentJdbcTemplate.findAllAccidents();
    }

    public void saveAccident(Accident accident) {
        accidentJdbcTemplate.saveAccident(accident);
    }

    public Accident findAccidentById(int id) {
        return accidentJdbcTemplate.findAccidentById(id);
    }

    public AccidentType findAccidentTypeById(int id) {
        return accidentJdbcTemplate.findAccidentTypeById(id);
    }

    public Collection<AccidentType> findAllAccidentTypes() {
        return accidentJdbcTemplate.findAllAccidentTypes();
    }

    public Rule findRuleById(int id) {
        return accidentJdbcTemplate.findRuleById(id);
    }

    public Collection<Rule> findAllRules() {
        return accidentJdbcTemplate.findAllRules();
    }
}
