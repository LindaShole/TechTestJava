package za.co.anycompany.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.postgresql.ds.PGSimpleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
public class DBConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(DBConfiguration.class);

    @Value("${spring.datasource.liquibase.changeLog}")
    private String liquiBaseChangeLog;

    @Value("${spring.datasource.schemaName}")
    private String schemaName;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.port}")
    private int port;

    @Value("${spring.datasource.databaseName}")
    private String databaseName;

    @Value("${spring.datasource.host}")
    private String host;

    @Bean
    public SpringLiquibase liquibase(DataSource ds){

        LOG.info("loading liquibase config...");

        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDataSource(ds);
        springLiquibase.setChangeLog(liquiBaseChangeLog);
        springLiquibase.setDefaultSchema(schemaName);
        springLiquibase.setContexts("local");

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("investec.datasource.username=", username);

        springLiquibase.setChangeLogParameters(parameters);

        return springLiquibase;
    }

    @Bean
    public DataSource getDataSource() {
        LOG.info("loading datasource config...");

        int ports[]={port};
        String hosts[]={host};

        PGSimpleDataSource pgSimpleDataSource = new PGSimpleDataSource();
        pgSimpleDataSource.setUser(username);
        pgSimpleDataSource.setPassword(password);
        pgSimpleDataSource.setPortNumbers(ports);
        pgSimpleDataSource.setDatabaseName(databaseName);
        pgSimpleDataSource.setServerNames(hosts);
        pgSimpleDataSource.setSsl(false);
        //pgSimpleDataSource.setSslfactory("org.postgresql.ssl.NonValidatingFactory");

        HikariConfig config = new HikariConfig();
        config.setDataSource(pgSimpleDataSource);
        config.setMaximumPoolSize(5);
        HikariDataSource hikariDataSource = new HikariDataSource(config);

        return hikariDataSource;
    }
}
