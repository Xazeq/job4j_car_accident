package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentTypeRepository;
import ru.job4j.accident.repository.RuleRepository;

import java.util.Collection;

@Service
public class AccidentService {
    private final AccidentRepository accidentRepository;
    private final AccidentTypeRepository accidentTypeRepository;
    private final RuleRepository ruleRepository;

    public AccidentService(AccidentRepository accidentRepository, AccidentTypeRepository accidentTypeRepository, RuleRepository ruleRepository) {
        this.accidentRepository = accidentRepository;
        this.accidentTypeRepository = accidentTypeRepository;
        this.ruleRepository = ruleRepository;
    }

    public Collection<Accident> findAllAccidents() {
        return (Collection<Accident>) accidentRepository.findAll();
    }

    public void saveAccident(Accident accident) {
        accidentRepository.save(accident);
    }

    public Accident findAccidentById(int id) {
        return accidentRepository.findById(id).get();
    }

    public AccidentType findAccidentTypeById(int id) {
        return accidentTypeRepository.findById(id).get();
    }

    public Collection<AccidentType> findAllAccidentTypes() {
        return (Collection<AccidentType>) accidentTypeRepository.findAll();
    }

    public Rule findRuleById(int id) {
        return ruleRepository.findById(id).get();
    }

    public Collection<Rule> findAllRules() {
        return (Collection<Rule>) ruleRepository.findAll();
    }
}
