package sk.upjs.ics.swib.factory;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.swib.dao.DatabazovyBonusDao;
import sk.upjs.ics.swib.dao.DatabazovyKlientDao;
import sk.upjs.ics.swib.dao.DatabazovyMultiplikatorDao;
import sk.upjs.ics.swib.dao.DatabazovyPohybDao;
import sk.upjs.ics.swib.dao.DatabazovyPorovnavacDao;
import sk.upjs.ics.swib.dao.DatabazovyUcetDao;
import sk.upjs.ics.swib.dao.DatabazovyUverDao;

public enum DaoFactory {

    INSTANCE;

    private JdbcTemplate jdbcTemplate;
    private DatabazovyBonusDao databazovyBonusDao;
    private DatabazovyKlientDao databazovyKlientDao;
    private DatabazovyUcetDao databazovyUcetDao;
    private DatabazovyMultiplikatorDao databazovyMultiplikatorDao;
    private DatabazovyPohybDao databazovyPohybDao;
    private DatabazovyPorovnavacDao databazovyPorovnavacDao;
    private DatabazovyUverDao databazovyUverDao;

    public JdbcTemplate jdbcTemplate() {
        if (this.jdbcTemplate == null) {
            this.jdbcTemplate = new JdbcTemplate(dataSource());
        }
        return this.jdbcTemplate;
    }
    
    public DatabazovyBonusDao databazovyBonusDao() {
        if (this.databazovyBonusDao == null) {
            this.databazovyBonusDao = new DatabazovyBonusDao(DaoFactory.INSTANCE.jdbcTemplate());
        }
        return this.databazovyBonusDao;
    }
    
    public DatabazovyKlientDao databazovyKlientDao() {
        if (this.databazovyKlientDao == null) {
            this.databazovyKlientDao = new DatabazovyKlientDao(DaoFactory.INSTANCE.jdbcTemplate());
        }
        return this.databazovyKlientDao;
    }
    
    public DatabazovyUcetDao databazovyUcetDao() {
        if (this.databazovyUcetDao == null) {
            this.databazovyUcetDao = new DatabazovyUcetDao(DaoFactory.INSTANCE.jdbcTemplate());
        }
        return this.databazovyUcetDao;
    }
    
    public DatabazovyMultiplikatorDao databazovyMultiplikatorDao() {
        if (this.databazovyMultiplikatorDao == null) {
            this.databazovyMultiplikatorDao = new DatabazovyMultiplikatorDao(DaoFactory.INSTANCE.jdbcTemplate());
        }
        return this.databazovyMultiplikatorDao;
    }
    
    public DatabazovyPohybDao databazovyPohybDao() {
        if (this.databazovyPohybDao == null) {
            this.databazovyPohybDao = new DatabazovyPohybDao(DaoFactory.INSTANCE.jdbcTemplate());
        }
        return this.databazovyPohybDao;
    }
    
    public DatabazovyPorovnavacDao databazovyPorovnavacDao() {
        if (this.databazovyPorovnavacDao == null) {
            this.databazovyPorovnavacDao = new DatabazovyPorovnavacDao(DaoFactory.INSTANCE.jdbcTemplate());
        }
        return this.databazovyPorovnavacDao;
    }
    
    public DatabazovyUverDao databazovyUverDao() {
        if (this.databazovyUverDao == null) {
            this.databazovyUverDao = new DatabazovyUverDao(DaoFactory.INSTANCE.jdbcTemplate());
        }
        return this.databazovyUverDao;
    }

    public DataSource dataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        if ("true".equals(System.getProperty("testovaciRezim"))) {
            dataSource.setURL("jdbc:mysql://46.229.230.241:3306/hq007701db");
            dataSource.setUser("hq007700");
            dataSource.setPassword("NaseTimoveHeslo2015");
        } else {
            dataSource.setURL("jdbc:mysql://46.229.230.241:3306/hq007700db");
            dataSource.setUser("hq007700");
            dataSource.setPassword("NaseTimoveHeslo2015");
        }

        return dataSource;
    }
}
