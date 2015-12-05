package sk.upjs.ics.swib.factory;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.swib.dao.BonusDao;
import sk.upjs.ics.swib.dao.DatabazovyBonusDao;
import sk.upjs.ics.swib.dao.DatabazovyKlientDao;
import sk.upjs.ics.swib.dao.DatabazovyMultiplikatorDao;
import sk.upjs.ics.swib.dao.DatabazovyPohybDao;
import sk.upjs.ics.swib.dao.DatabazovyPorovnavacDao;
import sk.upjs.ics.swib.dao.DatabazovyUcetDao;
import sk.upjs.ics.swib.dao.DatabazovyUverDao;
import sk.upjs.ics.swib.dao.KlientDao;
import sk.upjs.ics.swib.dao.MultiplikatorDao;
import sk.upjs.ics.swib.dao.PohybDao;
import sk.upjs.ics.swib.dao.PorovnavacDao;
import sk.upjs.ics.swib.dao.UcetDao;
import sk.upjs.ics.swib.dao.UverDao;

public enum DaoFactory {

    INSTANCE;

    private JdbcTemplate jdbcTemplate;
    private BonusDao bonusDao;
    private KlientDao klientDao;
    private UcetDao ucetDao;
    private MultiplikatorDao multiplikatorDao;
    private PohybDao pohybDao;
    private PorovnavacDao porovnavacDao;
    private UverDao uverDao;

    public JdbcTemplate jdbcTemplate() {
        if (this.jdbcTemplate == null) {
            this.jdbcTemplate = new JdbcTemplate(dataSource());
        }
        return this.jdbcTemplate;
    }

    public BonusDao bonusDao() {
        if (this.bonusDao == null) {
            this.bonusDao = new DatabazovyBonusDao(jdbcTemplate());
        }
        return this.bonusDao;
    }

    public KlientDao klientDao() {
        if (this.klientDao == null) {
            this.klientDao = new DatabazovyKlientDao(jdbcTemplate());
        }
        return this.klientDao;
    }

    public UcetDao ucetDao() {
        if (this.ucetDao == null) {
            this.ucetDao = new DatabazovyUcetDao(jdbcTemplate());
        }
        return this.ucetDao;
    }

    public MultiplikatorDao multiplikatorDao() {
        if (this.multiplikatorDao == null) {
            this.multiplikatorDao = new DatabazovyMultiplikatorDao(jdbcTemplate());
        }
        return this.multiplikatorDao;
    }

    public PohybDao pohybDao() {
        if (this.pohybDao == null) {
            this.pohybDao = new DatabazovyPohybDao(jdbcTemplate());
        }
        return this.pohybDao;
    }

    public PorovnavacDao porovnavacDao() {
        if (this.porovnavacDao == null) {
            this.porovnavacDao = new DatabazovyPorovnavacDao(jdbcTemplate());
        }
        return this.porovnavacDao;
    }

    public UverDao uverDao() {
        if (this.uverDao == null) {
            this.uverDao = new DatabazovyUverDao(jdbcTemplate());
        }
        return this.uverDao;
    }

    public DataSource dataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        if ("true".equals(System.getProperty("testovaciRezim"))) {
            dataSource.setURL("jdbc:mysql://46.229.230.241:3306/hq007701db");
            dataSource.setUser("hq007700");
            dataSource.setPassword("2015PatrikSaZabava");
        } else {
            dataSource.setURL("jdbc:mysql://46.229.230.241:3306/hq007700db");
            dataSource.setUser("hq007700");
            dataSource.setPassword("2015PatrikSaZabava");
        }

        return dataSource;
    }
}
