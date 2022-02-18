package ru.job4j.accident.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.List;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void saveAccident(Accident accident) {
        if (accident.getId() == 0) {
            createAccident(accident);
        } else {
            updateAccident(accident);
        }
    }

    public Accident findAccidentById(int id) {
        String sql = "SELECT a.id as id, a.name as name, a.text as text, a.address as address, "
                + "a.accident_type_id as accident_type_id, t.name as type_name "
                + "FROM accidents a JOIN accident_types as t ON a.accident_type_id = t.id "
                + "WHERE a.id = ?";
        Object[] args = {id};
        Accident accident = jdbc.queryForObject(sql, args, (rs, row) -> {
            Accident result = Accident.of(
                    rs.getString("name"),
                    rs.getString("text"),
                    rs.getString("address"),
                    AccidentType.of(
                            rs.getInt("accident_type_id"),
                            rs.getString("type_name")
                    )
            );
            result.setId(rs.getInt("id"));
            return result;
        });
        findAccidentRulesByAccidentId(id).forEach(accident::addRule);
        return accident;
    }

    public Collection<Accident> findAllAccidents() {
        String sql = "SELECT a.id as id, a.name as name, a.text as text, a.address as address, "
                + "a.accident_type_id as accident_type_id, t.name as type_name "
                + "FROM accidents a JOIN accident_types t ON a.accident_type_id = t.id "
                + "ORDER BY a.id";
        List<Accident> accidents = jdbc.query(sql, (rs, row) -> {
            Accident accident = Accident.of(
                    rs.getString("name"),
                    rs.getString("text"),
                    rs.getString("address"),
                    AccidentType.of(
                            rs.getInt("accident_type_id"),
                            rs.getString("type_name")
                    )
            );
            accident.setId(rs.getInt("id"));
            return accident;
        });
        accidents.forEach(accident -> findAccidentRulesByAccidentId(accident.getId())
                .forEach(accident::addRule));
        return accidents;
    }

    public Collection<AccidentType> findAllAccidentTypes() {
        String sql = "SELECT * FROM accident_types ORDER BY id";
        return jdbc.query(sql, new BeanPropertyRowMapper<>(AccidentType.class));
    }

    public Collection<Rule> findAllRules() {
        String sql = "SELECT * FROM rules ORDER BY id";
        return jdbc.query(sql, new BeanPropertyRowMapper<>(Rule.class));
    }

    public AccidentType findAccidentTypeById(int id) {
        String sql = "SELECT * FROM accident_types WHERE id = ?";
        Object[] args = {id};
        return jdbc.queryForObject(sql, args, (rs, row) ->
                AccidentType.of(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
    }

    public Rule findRuleById(int id) {
        String sql = "SELECT * FROM rules WHERE id = ?";
        Object[] args = {id};
        return jdbc.queryForObject(sql, args, (rs, row) ->
                Rule.of(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
    }

        private void createAccident(Accident accident) {
        String sql = "INSERT INTO accidents (name, text, address, accident_type_id) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        accident.setId((Integer) keyHolder.getKeys().get("id"));
        createAccidentsRules(accident);
    }

    private void updateAccident(Accident accident) {
        String sql = "UPDATE accidents SET name = ?, text = ?, address = ?, accident_type_id = ? WHERE id = ?";
        jdbc.update(sql, accident.getName(), accident.getText(),
                accident.getAddress(), accident.getType().getId(), accident.getId());
        updateAccidentsRules(accident);
    }

    private void createAccidentsRules(Accident accident) {
        String sql = "INSERT INTO accidents_rules(accident_id, rule_id) VALUES (?, ?)";
        accident.getRules().forEach(rule -> jdbc.update(sql, accident.getId(), rule.getId()));
    }

    private void deleteAccidentsRules(Accident accident) {
        String sql = "DELETE FROM accidents_rules WHERE accident_id = ?";
        jdbc.update(sql, accident.getId());
    }

    private void updateAccidentsRules(Accident accident) {
        deleteAccidentsRules(accident);
        createAccidentsRules(accident);
    }

    private Collection<Rule> findAccidentRulesByAccidentId(int id) {
        String sql = "SELECT r.id as id, r.name as name FROM rules r "
                + "JOIN accidents_rules ar ON r.id = ar.rule_id "
                + "WHERE ar.accident_id = ?";
        Object[] args = {id};
        return jdbc.query(sql, args,
                (rs, row) -> Rule.of(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
    }
}
