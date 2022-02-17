package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final AtomicInteger id = new AtomicInteger(0);

    public AccidentMem() {
        init();
    }

    private void init() {
        this.save(Accident.of(
                "Столкнулись два автомобиля",
                "В 12:35 на перекрестке произошло ДТП",
                "Площадь Пушкина"
        ));
        this.save(Accident.of(
                "Автомобиль сбил пешехода",
                "Водитель Lada Vesta не заметил пешехода на зебре",
                "улица Гагарина, около дома №12"
        ));
        this.save(Accident.of(
                "ДТП с выездом на встречную полосу",
                "Водитель не справился с управление, допустил выезд па полосу встречного движения "
                        + "и врезался в автомобиль Renault Logan",
                "проспект Победы, возле дома №45"
        ));
    }

    public void save(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(id.incrementAndGet());
        }
        accidents.put(accident.getId(), accident);
    }

    public Collection<Accident> findAllAccidents() {
        return accidents.values();
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }
}
