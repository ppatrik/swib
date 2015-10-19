package sk.upjs.ics.swib.factory;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public enum DaoFactory {

    INSTANCE;

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate jdbcTemplate() {
        if (this.jdbcTemplate == null) {
            this.jdbcTemplate = new JdbcTemplate(dataSource());
        }
        return this.jdbcTemplate;
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
