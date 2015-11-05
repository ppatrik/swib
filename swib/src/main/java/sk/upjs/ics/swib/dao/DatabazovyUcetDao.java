<<<<<<< HEAD

package sk.upjs.ics.swib.dao;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import sk.upjs.ics.swib.entity.Ucet;


public class DatabazovyUcetDao implements UcetDao{
private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<Ucet> mapovac
            = new BeanPropertyRowMapper<>(Ucet.class);
    
     public DatabazovyUcetDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
     }
    @Override
    public List<Ucet> dajVsetky() {
        String sql = "SELECT *FROM Ucet";
        return jdbcTemplate.query(sql, mapovac);
         }

    @Override
    public void pridaj(Ucet ucet) {
        String sql = "INSERT INTO Ucet VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, null, ucet.getKlientId(), ucet.getName(), ucet.getZostatok(), ucet.getCisloUctu(), ucet.isSpor());
          }
=======
package sk.upjs.ics.swib.dao;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.swib.entity.Ucet;
import sk.upjs.ics.swib.mappers.UcetRowMapper;

public class DatabazovyUcetDao implements UcetDao {

    private JdbcTemplate jdbcTemplate;
    private UcetRowMapper mapovac;

    public DatabazovyUcetDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapovac = new UcetRowMapper();

    }

    @Override
    public List<Ucet> dajVsetky() {
        String sql = "SELECT * FROM Ucet";
        return jdbcTemplate.query(sql, mapovac);
    }

    @Override
    public void pridaj(Ucet ucet) {
        String sql = "INSERT INTO Ucet VALUES (? ,?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, null , ucet.getKlientId(), ucet.getName(), ucet.getZostatok(), ucet.getCisloUctu(), ucet.isSpor() ? 1 : 0);
    }
>>>>>>> origin/master

    @Override
    public void odstran(Ucet ucet) {
        String sql = "DELETE FROM Ucet WHERE ID = ?";
        jdbcTemplate.update(sql, ucet.getId());
<<<<<<< HEAD
          }

    @Override
    public void uprav(Ucet ucet) {
         String sql = "UPDATE Ucet SET KlientID = ?, Nazov = ?, Zostatok = ?, C_uctu = ?, Spor = ? WHERE ID = ?";
         jdbcTemplate.update(sql,  ucet.getKlientId(),ucet.getName(), ucet.getZostatok(), ucet.getCisloUctu(), ucet.isSpor());
       }
    
=======
    }

    @Override
    public void uprav(Ucet ucet) {
        String sql = "UPDATE Ucet SET KlientID = ?, Nazov = ?, Zostatok = ?, C_uctu = ?, Spor = ? WHERE ID = ?";
        jdbcTemplate.update(sql, ucet.getKlientId(), ucet.getName(), ucet.getZostatok(), ucet.getCisloUctu(), ucet.isSpor() ? 1 : 0, ucet.getId());
    }

>>>>>>> origin/master
}
