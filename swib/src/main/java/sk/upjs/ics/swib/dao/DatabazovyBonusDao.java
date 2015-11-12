package sk.upjs.ics.swib.dao;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.swib.entity.Bonus;
import sk.upjs.ics.swib.entity.Uver;
import sk.upjs.ics.swib.mappers.BonusyRowMapper;

public class DatabazovyBonusDao implements BonusDao {

    private JdbcTemplate jdbcTemplate;
    private BonusyRowMapper mapovac;

    private static final String TABLE_NAME = "Bonusy";

    public DatabazovyBonusDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapovac = new BonusyRowMapper();
    }

    @Override
    public List<Bonus> dajVsetky(Uver uver) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE UveryId = ?";
        return jdbcTemplate.query(sql, mapovac, uver.getId());
    }

    @Override
    public void pridaj(Bonus bonus) {
        String sql = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        jdbcTemplate.update(sql, null, bonus.getUverId(), bonus.getNazov(), bonus.getJeVacsiAko(), bonus.getKolkoJeBonus(), bonus.getOrderNumber(), bonus.getMultiplikatorId(), bonus.getPorovnavacId());
    }

    @Override
    public void odstran(Bonus bonus) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE ID = ? ";
        jdbcTemplate.update(sql, bonus.getId());
    }

    @Override
    public void uprav(Bonus bonus) {
        String sql = "UPDATE " + TABLE_NAME + " SET UveryId = ?, Nazov = ?, Vacsi_ako = ?, Vyska_bonusu = ?, Poradie_bonusu = ?, MultiplikatorID = ?, CoPorovnavamID = ? WHERE ID = ?";
        jdbcTemplate.update(sql, bonus.getUverId(), bonus.getNazov(), bonus.getJeVacsiAko(), bonus.getKolkoJeBonus(), bonus.getOrderNumber(), bonus.getMultiplikatorId(), bonus.getPorovnavacId(), bonus.getId());
    }

}
