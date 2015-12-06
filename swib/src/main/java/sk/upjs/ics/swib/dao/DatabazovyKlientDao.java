package sk.upjs.ics.swib.dao;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.swib.entity.Klient;
import sk.upjs.ics.swib.mappers.KlientRowMapper;

public class DatabazovyKlientDao implements KlientDao {

    private JdbcTemplate jdbcTemplate;
    private KlientRowMapper mapovac;

    private static final String TABLE_NAME = "Klient";

    public DatabazovyKlientDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapovac = new KlientRowMapper();

    }

    @Override
    public List<Klient> dajVsetkych() {
        String sql = "SELECT * FROM " + TABLE_NAME;
        return jdbcTemplate.query(sql, mapovac);
    }

    @Override
    public void pridaj(Klient klient) {
        String sql = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, klient.getId(), klient.getMeno(), klient.getPriezvisko(), klient.getCisloKarty(), klient.getDatumNarodenia(), klient.getRodneCislo(), klient.getCisloPreukazu());
    }

    @Override
    public void odstran(Klient klient) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE ID = ?";
        jdbcTemplate.update(sql, klient.getId());
    }

    @Override
    public void uprav(Klient klient) {
        String sql = "UPDATE " + TABLE_NAME + " SET Meno = ?, Priezvisko = ?, C_Karty = ?, Dat_nar = ?, Rodne_cislo = ?, C_preukazu = ? WHERE ID = ?";
        jdbcTemplate.update(sql, klient.getMeno(), klient.getPriezvisko(), klient.getCisloKarty(), klient.getDatumNarodenia(), klient.getRodneCislo(), klient.getCisloPreukazu(), klient.getId());
    }

    @Override
    public BigDecimal mozeNaMesiacMaximalneSplacat(Klient klient) {
        String sql = "select AVG(items.Sumy) from ("
                + "select SUM(p.suma) AS Sumy from Ucet as uk inner join Pohyby as p on p.UcetID=uk.ID "
                + "where uk.KlientID = ? group by month(p.datum), year(p.datum) )items";
        return (BigDecimal) jdbcTemplate.queryForObject(sql, BigDecimal.class, klient.getId());
    }

    @Override
    public BigDecimal priemernyMesacnyPrijem(Klient klient) {
        String sql = "select AVG(items.Sumy) from ("
                + "select SUM(p.suma) AS Sumy from Ucet as uk inner join Pohyby as p on p.UcetID=uk.ID "
                + "where uk.KlientID = ? AND p.Suma > 0 group by month(p.datum), year(p.datum) )items";
        return (BigDecimal) jdbcTemplate.queryForObject(sql, BigDecimal.class, klient.getId());
    }

    @Override
    public BigDecimal priemernyMesacnyZostatok(Klient klient) {
        String sql = "select avg(q2.cum) from (select t1.date, t1.sumy, sum(t2.Sumy) cum "
                + "from (select SUM(p.suma) AS sumy , CONVERT(CONCAT(year(p.datum), IF (month(p.datum) > 9, month(p.datum),CONCAT(0,month(p.datum)))) ,UNSIGNED INTEGER) as date "
                + "from Ucet as uk inner join Pohyby as p on p.UcetID=uk.ID "
                + "where uk.KlientID = ? group by date ORDER BY date) as t1, "
                + "(select SUM(p.suma) AS sumy , CONVERT(CONCAT(year(p.datum), IF (month(p.datum) > 9, month(p.datum),CONCAT(0,month(p.datum)))) ,UNSIGNED INTEGER) as date "
                + "from Ucet as uk inner join Pohyby as p on p.UcetID=uk.ID "
                + "where uk.KlientID = ? group by date ORDER BY date) as t2 WHERE t1.date>=t2.date GROUP BY t1.date ) as q2";
        return (BigDecimal) jdbcTemplate.queryForObject(sql, BigDecimal.class, klient.getId(), klient.getId());
    }

}
