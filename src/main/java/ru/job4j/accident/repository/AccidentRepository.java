package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.accident.model.Accident;

import java.util.Optional;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {
    @Override
    @Query("select distinct a from Accident a "
            + "join fetch a.type t "
            + "join fetch a.rules r "
            + "order by a.id asc")
    Iterable<Accident> findAll();

    @Override
    @Query("select a from Accident a "
            + "join fetch a.type t "
            + "join fetch a.rules r "
            + "where a.id = :id")
    Optional<Accident> findById(@Param("id") Integer integer);
}
