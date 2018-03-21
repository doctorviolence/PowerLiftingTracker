package powerlifting.config;

import powerlifting.controller.LiftController;
import powerlifting.dal.LiftDao;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import powerlifting.dal.UserDao;
import powerlifting.model.Bench;
import powerlifting.model.WilksCalculator;
import powerlifting.service.LiftService;

import javax.sql.DataSource;

@Configuration
public class ApplicationConfig {

    private DriverManagerDataSource ds;

    public ApplicationConfig() {
        ds = new DriverManagerDataSource();
    }

    @Bean
    public LiftDao jdbcLiftDao() {
        return new LiftDao();
    }

    @Bean
    public UserDao jdbcUserDao() {
        return new UserDao();
    }

    @Bean
    public WilksCalculator wilksCalculator() {
        return new WilksCalculator();
    }

    @Bean
    public DataSource dataSource() {
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/powerlifting_tracker");
        ds.setUsername("root");
        ds.setPassword("eIhuJk-dq2Jd");

        return ds;

    }
}
