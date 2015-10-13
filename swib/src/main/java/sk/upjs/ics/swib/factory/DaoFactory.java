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
            dataSource.setURL("jdbc:mysql://www.db4free.net:3306/swib_test");
            dataSource.setUser("swib_test");
            dataSource.setPassword("NaseTimoveHeslo2015");
        } else {
            dataSource.setURL("jdbc:mysql://www.db4free.net:3306/swib");
            dataSource.setUser("swib");
            dataSource.setPassword("NaseTimoveHeslo2015");
        }

        return dataSource;
    }
}
