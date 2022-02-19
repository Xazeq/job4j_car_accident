package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.function.Function;

@Repository
public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public void saveAccident(Accident accident) {
        tx(session -> {
            session.saveOrUpdate(accident);
            return accident;
        });
    }

    public Accident findAccidentById(int id) {
        return this.tx(session -> session.createQuery(
                "select a from Accident a "
                        + "join fetch a.type t "
                        + "join fetch a.rules r "
                        + "where a.id = :id", Accident.class)
                .setParameter("id", id)
                .uniqueResult());
    }

    public Collection<Accident> findAllAccidents() {
        return this.tx(session -> session.createQuery(
                "select distinct a FROM Accident a "
                        + "join fetch a.type t "
                        + "join fetch a.rules r "
                        + "order by a.id asc"
        ).list());
    }

    public Collection<AccidentType> findAllAccidentTypes() {
        return this.tx(session -> session.createQuery("select t from AccidentType t").list());
    }

    public Collection<Rule> findAllRules() {
        return this.tx(session -> session.createQuery("select r from Rule r").list());
    }

    public AccidentType findAccidentTypeById(int id) {
        return this.tx(session -> session.get(AccidentType.class, id));
    }

    public Rule findRuleById(int id) {
        return this.tx(session -> session.get(Rule.class, id));
    }
}
