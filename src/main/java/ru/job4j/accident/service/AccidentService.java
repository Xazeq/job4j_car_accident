package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.*;
import ru.job4j.accident.repository.*;

import java.util.Collection;

@Service
public class AccidentService {
    private final AccidentRepository accidentRepository;
    private final AccidentTypeRepository accidentTypeRepository;
    private final RuleRepository ruleRepository;
    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;

    public AccidentService(AccidentRepository accidentRepository, AccidentTypeRepository accidentTypeRepository,
                           RuleRepository ruleRepository, AuthorityRepository authorityRepository,
                           UserRepository userRepository) {
        this.accidentRepository = accidentRepository;
        this.accidentTypeRepository = accidentTypeRepository;
        this.ruleRepository = ruleRepository;
        this.authorityRepository = authorityRepository;
        this.userRepository = userRepository;
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

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public Authority findByAuthority(String authority) {
        return authorityRepository.findByAuthority(authority);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
