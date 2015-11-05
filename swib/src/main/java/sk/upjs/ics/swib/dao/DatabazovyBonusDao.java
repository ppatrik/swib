package sk.upjs.ics.swib.dao;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.swib.entity.Bonus;

public class DatabazovyBonusDao implements BonusDao {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<Bonus> mapovac
            = new BeanPropertyRowMapper<>(Bonus.class);

    public DatabazovyBonusDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Bonus> dajVsetky(Bonus bonus) {
        String sql = "SELECT * FROM Bonusy";
        return jdbcTemplate.query(sql, mapovac);
    }

    @Override
    public void pridaj(Bonus bonus) {
        String sql = "INSERT INTO Bonusy VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, null, bonus.getUverId(), bonus.getNazov(), bonus.getJeVacsiAko(), bonus.getKolkoJeBonus(), bonus.getMultiplikatorId(), bonus.getPorovnavacId());
    }

    @Override
    public void odstran(Bonus bonus) {
    String sql ="DELETE * FROM Bonusy WHERE ID = ? ";  
    jdbcTemplate.update(sql, bonus.getId());
    }

    @Override
    public void uprav(Bonus bonus) {
    String sql = "UPDATE Bonusy SET UveryId = ?, Nazov = ?, Vacsi_ako = ?, Vyska_bonusu = ?, Poradie_bonusu = ?, MultiplikatorID = ?, CoPorovnavamID = ? WHERE ID = ?";   
    jdbcTemplate.update(sql, bonus.getId());
    }

}
