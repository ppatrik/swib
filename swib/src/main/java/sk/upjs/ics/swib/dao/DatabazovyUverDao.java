/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.swib.dao;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.swib.entity.Uver;

public class DatabazovyUverDao implements UverDao {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<Uver> mapovac
            = new BeanPropertyRowMapper<>(Uver.class);

    private static final String TABLE_NAME = "Uvery";

    public DatabazovyUverDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Uver> dajVsetky() {
        String sql = "SELECT * FROM " + TABLE_NAME;
        return jdbcTemplate.query(sql, mapovac);
    }

    @Override
    public void pridaj(Uver uver) {
        String sql = "INSERT INTO " + TABLE_NAME + " VALUES (?,?)";
        jdbcTemplate.update(sql, null, uver.getNazov());
    }

    @Override
    public void odstran(Uver uver) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE ID = ?";
        jdbcTemplate.update(sql, uver.getId());
    }

    @Override
    public void uprav(Uver uver) {
        String sql = "UPDATE " + TABLE_NAME + " SET Nazov = ? WHERE ID = ?";
        jdbcTemplate.update(sql, uver.getNazov(), uver.getId());
    }
}
