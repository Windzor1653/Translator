package apptranslator.repository.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"apptranslator.repository"},
        entityManagerFactoryRef = "entityManagerFactoryH2",
        transactionManagerRef = "transactionManagerH2")
public class H2Config {

    @Bean(name = "dataSourceH2")
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:h2:mem:testdb")
                .driverClassName("org.h2.Driver")
                .build();
    }

    @Bean(name = "entityManagerFactoryH2")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("dataSourceH2") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .persistenceUnit("h2")
                .packages("apptranslator.repository")
                .properties(getHibernateProperties())
                .build();
    }

    @Bean(name = "entityManagerH2")
    @Primary
    public EntityManager entityManager(
            @Qualifier("entityManagerFactoryH2") EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }

    @Bean(name = "transactionManagerH2")
    @Primary
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactoryH2") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    private Map<String, String> getHibernateProperties() {
        Map<String, String> map = new HashMap<>();
        map.put("hibernate.hbm2ddl.auto", "update");
        map.put("hibernate.format_sql", "true");
        map.put("hibernate.show_sql", "true");
        map.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        return map;
    }
}