package config;

import dal.LiftDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class ApplicationConfig {

    private DriverManagerDataSource ds;

    public ApplicationConfig() {
        ds = new DriverManagerDataSource();
    }

    @Bean
    public LiftDao jdbcPersonDao(){
        return new LiftDao();
    }

    @Bean
    public DataSource dataSource() {
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/powerlifting_tracker");
        ds.setUsername("root");
        ds.setPassword("eIhuJk-dq2Jd");

        return ds;

    }
}
