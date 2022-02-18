package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final Map<Integer, AccidentType> accidentTypes = new HashMap<>();
    private final Map<Integer, Rule> rules = new HashMap<>();
    private final AtomicInteger accidentId = new AtomicInteger(0);
    private final AtomicInteger accidentTypeId = new AtomicInteger(0);
    private final AtomicInteger ruleId = new AtomicInteger(0);

    public AccidentMem() {
        init();
    }

    private void init() {
        this.saveAccidentType(AccidentType.of(1, "Две машины"));
        this.saveAccidentType(AccidentType.of(2, "Машина и человек"));
        this.saveAccidentType(AccidentType.of(3, "Машина и велосипед"));
        this.saveRule(Rule.of(1, "Статья. 1"));
        this.saveRule(Rule.of(2, "Статья. 2"));
        this.saveRule(Rule.of(3, "Статья. 3"));
        Accident accident1 = Accident.of(
                "Столкнулись два автомобиля",
                "В 12:35 на перекрестке произошло ДТП",
                "Площадь Пушкина",
                findAccidentTypeById(1)
        );
        accident1.addRule(findRuleById(1));
        accident1.addRule(findRuleById(3));
        this.saveAccident(accident1);
        Accident accident2 = Accident.of(
                "Автомобиль сбил пешехода",
                "Водитель Lada Vesta не заметил пешехода на зебре",
                "улица Гагарина, около дома №12",
                findAccidentTypeById(2)
        );
        accident2.addRule(findRuleById(2));
        this.saveAccident(accident2);
        Accident accident3 = Accident.of(
                "ДТП с выездом на встречную полосу",
                "Водитель не справился с управление, допустил выезд па полосу встречного движения "
                        + "и врезался в автомобиль Renault Logan",
                "проспект Победы, возле дома №45",
                findAccidentTypeById(1)
        );
        accident3.addRule(findRuleById(1));
        accident3.addRule(findRuleById(2));
        this.saveAccident(accident3);
        System.out.println(accident1);
        System.out.println(accident2);
        System.out.println(accident3);
    }

    public void saveAccident(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(accidentId.incrementAndGet());
        }
        accident.setType(findAccidentTypeById(accident.getType().getId()));
        accidents.put(accident.getId(), accident);
    }

    public void saveAccidentType(AccidentType accidentType) {
        if (accidentType.getId() == 0) {
            accidentType.setId(accidentTypeId.incrementAndGet());
        }
        accidentTypes.put(accidentType.getId(), accidentType);
    }

    public void saveRule(Rule rule) {
        if (rule.getId() == 0) {
            rule.setId(ruleId.incrementAndGet());
        }
        rules.put(rule.getId(), rule);
    }

    public Collection<Accident> findAllAccidents() {
        return accidents.values();
    }

    public Collection<AccidentType> findAllAccidentTypes() {
        return accidentTypes.values();
    }

    public Collection<Rule> findAllRules() {
        return rules.values();
    }

    public Accident findAccidentById(int id) {
        return accidents.get(id);
    }

    public AccidentType findAccidentTypeById(int id) {
        return accidentTypes.get(id);
    }

    public Rule findRuleById(int id) {
        return rules.get(id);
    }
}
